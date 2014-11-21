package edu.udo.cs.ls14.jf.bpmn.process;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalysisProcess {

	private static final Logger LOG = LoggerFactory
			.getLogger(Process.class);
	private static final String PROCESS_MODEL = "edu/udo/cs/ls14/jf/bpmn/process_1.bpmn20.xml";
	private ProcessEngine processEngine;
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private HistoryService historyService;

	@Before
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
				.startProcessInstanceByKey("process_1", variables);
		LOG.info("process instance ID: " + processInstance.getId());
		HistoricVariableInstance var = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(processInstance.getId())
				.variableName("dataOutputOfProcess").singleResult();
		LOG.info("Returning " + var.getValue());
		return (Integer) var.getValue();
	}
	
	@Test
	public void testProcess() {
//		run("Hallo");
		System.out.println("done");
	}
}
