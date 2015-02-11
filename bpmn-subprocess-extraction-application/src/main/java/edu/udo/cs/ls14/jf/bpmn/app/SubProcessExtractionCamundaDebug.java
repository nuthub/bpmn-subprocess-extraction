package edu.udo.cs.ls14.jf.bpmn.app;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.eclipse.bpmn2.Definitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * This is an alternative camunda bpm process engine based implementation, which
 * doesn't make use of super process and call activities. This has been
 * implemented primarily for testing and performance measuring purposes.
 * 
 * @author Julian Flake
 *
 */
public class SubProcessExtractionCamundaDebug implements SubProcessExtraction {

	private ProcessEngine processEngine;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubProcessExtractionCamundaDebug.class.getName());

	@Override
	public void init() {
		Registries.registerAll();
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

	@Override
	public ProcessTransformation run(Definitions definitions1,
			Definitions definitions2) throws Exception {
		ProcessInstance instance = null;
		// Start process instance
		Map<String, Object> variables = new HashMap<String, Object>();

		// Analyze Model 1
		variables.clear();
		variables.put("definitions", definitions1);
		// Run analysis 1 twice to compensate start up latencies
		instance = processEngine.getRuntimeService().startProcessInstanceByKey(
				"processanalysis", variables);
		long timeAnalysis1Start = System.currentTimeMillis();
		instance = processEngine.getRuntimeService().startProcessInstanceByKey(
				"processanalysis", variables);
		long timeAnalysis1End = System.currentTimeMillis();
		ProcessAnalysis analysis1 = (ProcessAnalysis) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("analysis")
				.singleResult().getValue();

		// Analyze Model 2
		variables.clear();
		variables.put("definitions", definitions2);
		long timeAnalysis2Start = System.currentTimeMillis();
		instance = processEngine.getRuntimeService().startProcessInstanceByKey(
				"processanalysis", variables);
		long timeAnalysis2End = System.currentTimeMillis();
		ProcessAnalysis analysis2 = (ProcessAnalysis) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("analysis")
				.singleResult().getValue();

		// Match models
		variables.clear();
		variables.put("analysis1", analysis1);
		variables.put("analysis2", analysis2);
		long timeMatchingStart = System.currentTimeMillis();
		instance = processEngine.getRuntimeService().startProcessInstanceByKey(
				"processmatching", variables);
		long timeMatchingEnd = System.currentTimeMillis();
		ProcessMatching matching = (ProcessMatching) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId()).variableName("matching")
				.singleResult().getValue();

		// transform models
		variables.clear();
		variables.put("matching", matching);
		long timeTransformationStart = System.currentTimeMillis();
		instance = processEngine.getRuntimeService().startProcessInstanceByKey(
				"processtransformation", variables);
		long timeTransformationEnd = System.currentTimeMillis();
		ProcessTransformation transformation = (ProcessTransformation) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId())
				.variableName("transformation").singleResult().getValue();

		LOGGER.info("Time for Analyzing Model 1       : "
				+ (timeAnalysis1End - timeAnalysis1Start) + "ms");
		LOGGER.info("Time for Analyzing Model 2       : "
				+ (timeAnalysis2End - timeAnalysis2Start) + "ms");
		LOGGER.info("Time for Matching Models         : "
				+ (timeMatchingEnd - timeMatchingStart) + "ms");
		LOGGER.info("Time for Transformation of Models: "
				+ (timeTransformationEnd - timeTransformationStart) + "ms");

		return transformation;
	}

}
