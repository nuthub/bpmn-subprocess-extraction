package edu.udo.cs.ls14.jf.bpmn.app;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.eclipse.bpmn2.Definitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SubProcessExtractionCamunda implements SubProcessExtraction {

	private ProcessEngine processEngine;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubProcessExtractionMixed.class.getName());

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
	 * (definitions1, definitions2) -> extraction
	 * 
	 * @param definitions1
	 * @param definitions2
	 * @return
	 * @throws Exception
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
		// TODO: remove this fix
		transformation = new ProcessTransformerImpl()
				.transform((ProcessMatching) processEngine.getHistoryService()
						.createHistoricVariableInstanceQuery()
						.processInstanceId(instance.getId())
						.variableName("matching").singleResult().getValue());
		transformation.eResource().save(System.out, null);
		return transformation;
	}

}
