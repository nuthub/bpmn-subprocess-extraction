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
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * {@inheritDoc}
 * 
 * This is an application implementation based on camunda bpm process engine.
 * 
 * @author Julian Flake
 *
 */
public class SubProcessExtractionCamunda implements SubProcessExtraction {

	private ProcessEngine processEngine;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubProcessExtractionCamunda.class.getName());

	/**
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
	}

	/**
	 * {@inheritDoc}
	 */
	public ProcessTransformation run(Definitions definitions1,
			Definitions definitions2) throws Exception {
		// Start process instance
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

}
