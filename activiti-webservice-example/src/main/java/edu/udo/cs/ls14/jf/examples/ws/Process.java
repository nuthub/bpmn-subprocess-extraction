package edu.udo.cs.ls14.jf.examples.ws;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Process {

	private static final Logger LOG = LoggerFactory
			.getLogger(Process.class);
	private static final String PROCESS_MODEL = "WebServiceCall.bpmn20.xml";
	private ProcessEngine processEngine;
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private HistoryService historyService;

	public void init() {
		processEngine = ProcessEngines.getDefaultProcessEngine();
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		historyService = processEngine.getHistoryService();
		repositoryService.createDeployment()
				.addClasspathResource(PROCESS_MODEL).deploy();
		LOG.info("Number of process definitions: "
				+ repositoryService.createProcessDefinitionQuery().count());
	}

	public int run(String testString) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("dataInputOfProcess", testString);
		LOG.info("Starting process instance.");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("myProcess", variables);
		LOG.info("process instance ID: " + processInstance.getId());
		HistoricVariableInstance var = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(processInstance.getId())
				.variableName("dataOutputOfProcess").singleResult();
		LOG.info("Returning " + var.getValue());
		return (Integer) var.getValue();
	}
}
