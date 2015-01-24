package edu.udo.cs.ls14.jf.bpmn.app;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class SubProcessExtraction {

	private ProcessEngine processEngine;

	public void init() {
		// Start the engine
		MyProcessEngineConfiguration config = (MyProcessEngineConfiguration) MyProcessEngineConfiguration
				.createProcessEngineConfigurationFromResourceDefault();
		processEngine = config.buildProcessEngine();
		// deploy processes
		String[] processes = { "subprocessextraction", "processanalysis",
				"processmatching", "processtransformation" };
		for (Deployment d : processEngine.getRepositoryService()
				.createDeploymentQuery().list()) {
			processEngine.getRepositoryService().deleteDeployment(d.getId());
		}
		for (String process : processes) {
			processEngine.getRepositoryService().createDeployment()
					.enableDuplicateFiltering()
					.addClasspathResource(process + ".bpmn").name(process)
					.deploy();
		}
	}

	/**
	 * (definitions1, definitions2) -> extraction
	 * 
	 * @param definitions1
	 * @param definitions2
	 * @return
	 * @throws Exception
	 */
	public ProcessTransformation runSubProcessExtraction(Definitions definitions1,
			Definitions definitions2) throws Exception {
		// Start process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("definitions1", definitions1);
		variables.put("definitions2", definitions2);
		ProcessInstance instance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("subprocessextraction", variables);
		// Return Result
		ProcessTransformation extraction = (ProcessTransformation) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("extraction")
				.singleResult().getValue();
		return extraction;
	}

	/**
	 * definitions -> matching
	 * 
	 * @param definitions
	 * @return
	 */
	public ProcessAnalysis runProcessAnalysis(Definitions definitions) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("definitions", definitions);
		ProcessInstance instance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("processanalysis", variables);
		// Return Result
		ProcessAnalysis analysis = (ProcessAnalysis) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("analysis")
				.singleResult().getValue();
		return analysis;
	}

	/*
	 * (analysis1, analysis2) -> matching
	 */
	public ProcessMatching runProcessMatching(ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) {
		// Start process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("analysis1", analysis1);
		variables.put("analysis2", analysis2);
		ProcessInstance instance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("processmatching", variables);
		// Return Result
		ProcessMatching matching = (ProcessMatching) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("matching")
				.singleResult().getValue();
		return matching;
	}

	/**
	 * matching -> extraction
	 * 
	 * @param matching
	 * @return
	 */
	public ProcessTransformation runProcessExtraction(ProcessMatching matching) {
		// Start process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("matching", matching);
		ProcessInstance instance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("processtransformation", variables);
		// Return Result
		ProcessTransformation extraction = (ProcessTransformation) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("extraction")
				.singleResult().getValue();
		return extraction;
	}
}
