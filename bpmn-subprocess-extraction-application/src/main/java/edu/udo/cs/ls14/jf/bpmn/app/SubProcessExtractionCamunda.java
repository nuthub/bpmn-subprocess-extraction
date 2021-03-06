package edu.udo.cs.ls14.jf.bpmn.app;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.eclipse.bpmn2.Definitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * {@inheritDoc}
 * 
 * This is an application implementation based on camunda bpm process engine.
 * Implements basic application interface and profiling interface.
 * 
 * @author Julian Flake
 *
 */
public class SubProcessExtractionCamunda implements ISubProcessExtraction,
		ISubProcessExtractionProfiling {

	private ProcessEngine processEngine;
	private boolean initialized = false;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubProcessExtractionCamunda.class.getName());

	/**
	 * 
	 * Set up process engine and deploy exuecutable bpmn models.
	 * 
	 */
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
		initialized = true;
	}

	/**
	 * {@inheritDoc}
	 */
	public ProcessTransformation run(Definitions definitions1,
			Definitions definitions2) throws Exception {
		// Start process instance
		if (!initialized) {
			init();
		}
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("definitions1", definitions1);
		variables.put("definitions2", definitions2);
		long timeStart = System.currentTimeMillis();
		ProcessInstance instance = processEngine.getRuntimeService()
				.startProcessInstanceByKey("subprocessextraction", variables);
		long timeEnd = System.currentTimeMillis();
		LOGGER.info("Complete Process took: " + (timeEnd - timeStart) + "ms");
		// Return Result
		ProcessTransformation transformation = (ProcessTransformation) processEngine
				.getHistoryService().createHistoricVariableInstanceQuery()
				.processInstanceId(instance.getId())
				.variableName("transformation").singleResult().getValue();
		return transformation;
	}

	/**
	 * This is an alternative implementation, which doesn't make use of super
	 * process and call activities. This is used for performance measuring
	 * purposes.
	 */
	@Override
	public ProcessTransformation runAndProfile(Definitions definitions1,
			Definitions definitions2) throws Exception {

		if (!initialized) {
			init();
		}
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
