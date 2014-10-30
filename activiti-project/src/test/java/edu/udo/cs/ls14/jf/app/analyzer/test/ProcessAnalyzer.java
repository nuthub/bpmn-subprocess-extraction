package edu.udo.cs.ls14.jf.app.analyzer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Task;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessAnalyzer {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessAnalyzer.class);
	private ProcessEngine processEngine;
	private RuntimeService runtimeService;
	private HistoryService historyService;

	@Before
	public void setUp() {
		processEngine = ProcessEngines.getDefaultProcessEngine();
		LOG.info(processEngine.getProcessEngineConfiguration().toString());
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		repositoryService.createDeployment()
				.addClasspathResource("diagrams/ProcessAnalysis.bpmn").deploy();
		runtimeService = processEngine.getRuntimeService();
		historyService = processEngine.getHistoryService();
		LOG.info("Number of process definitions: "
				+ repositoryService.createProcessDefinitionQuery().count());
	}

	@Test
	public void testAnalyzer() throws Exception {
		Map<String, Object> variables = new HashMap<String, Object>();
		Resource res = ExamplesFactory.createResource(getClass());

		variables.put("resource", res);
		variables.put("testInteger", 1);
		variables.put("person", ExamplesFactory.createPerson());
		variables.put("docRoot", ExamplesFactory.createDocRoot(res));
		variables.put("task1", ExamplesFactory.createTask("A Task"));
		variables.put("task2", ExamplesFactory.createTask("A Task"));
		variables.put("definitions", ExamplesFactory.createDefintions(res));
		// Start a process instance
		String procId = runtimeService.startProcessInstanceByKey("myProcess",
				variables).getId();
		HistoricVariableInstance histVar;

		histVar = getHistoricVar(procId, "testInteger");
		assertEquals(histVar.getValue(), 1);

		histVar = getHistoricVar(procId, "person");
		assertEquals(histVar.getValue().toString(),
				"Person [name=Max Mustermann]");

		histVar = getHistoricVar(procId, "resource");
		assertNotNull(histVar.getValue());
		assertTrue(histVar.getValue() instanceof Resource);
		((Resource) histVar.getValue()).save(new FileOutputStream(
				"/tmp/test.bpmn"), null);

		histVar = getHistoricVar(procId, "docRoot");
		assertNotNull(histVar.getValue());
		assertTrue(histVar.getValue() instanceof DocumentRoot);

		histVar = getHistoricVar(procId, "task1");
		assertNotNull(histVar.getValue());
		assertTrue(histVar.getValue() instanceof Task);
		assertEquals("A Task", ((Task) histVar.getValue()).getName());

		histVar = getHistoricVar(procId, "task2");
		assertNotNull(histVar.getValue());
		assertTrue(histVar.getValue() instanceof Task);
		assertEquals("A Task", ((Task) histVar.getValue()).getName());

		assertEquals(
				((Task) getHistoricVar(procId, "task1").getValue()).getName(),
				((Task) getHistoricVar(procId, "task2").getValue()).getName());
	}

	private HistoricVariableInstance getHistoricVar(String procId,
			String varName) {
		HistoricVariableInstanceQuery q = historyService
				.createHistoricVariableInstanceQuery()
				.processInstanceId(procId).variableName(varName);
		HistoricVariableInstance link = q.singleResult();
		return link;
	}

}
