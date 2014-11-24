package edu.udo.cs.ls14.jf.bpmn.application;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.util.EcoreUtil;

import edu.udo.cs.ls14.jf.bpmn.cfg.MyProcessEngineConfiguration;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

public class SubProcessExtraction {

	private ProcessEngine processEngine;

	public void init() {
		// Start the engine
		MyProcessEngineConfiguration config = (MyProcessEngineConfiguration) MyProcessEngineConfiguration
				.createProcessEngineConfigurationFromResourceDefault();
		processEngine = config.buildProcessEngine();
		// deploy processes
		String[] processes = { "subprocessextraction", "processanalysis",
				"processmatching", "processextraction" };
		for (String process : processes) {
			processEngine.getRepositoryService().createDeployment()
					.enableDuplicateFiltering()
					.addClasspathResource(process + ".bpmn").deploy();
		}
	}

	public ProcessExtraction getExtraction(Definitions definitions1,
			Definitions definitions2) throws Exception {
		// Start process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("definitions1", EcoreUtil.copy(definitions1));
		variables.put("definitions2", EcoreUtil.copy(definitions2));
		ProcessInstance instance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("subprocessextraction", variables);

		// Return Result
		ProcessExtraction extraction = (ProcessExtraction) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("extraction")
				.singleResult().getValue();
		return extraction;
	}

}
