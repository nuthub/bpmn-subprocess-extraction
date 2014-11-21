package edu.udo.cs.ls14.jf.ws.bpmn.pst.test;

import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.pst.ProcessStructureTreeImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.pst.ProcessStructureTreeSEI;

public class ProcessStructureTreeWSTest {

	private String url = "http://localhost:8880/ProcessStructureTreeService";
	private Endpoint endpoint;
	private Service service;
	private ProcessStructureTreeSEI port;
	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() throws Exception {
		// Create WebService endpoint
		endpoint = Endpoint.create(new ProcessStructureTreeImpl());
		endpoint.publish(url);

		// Create WebService client port
		QName serviceName = new QName("http://pst.bpmn.ws.jf.ls14.cs.udo.edu/",
				"ProcessStructureTreeImplService");
		service = Service.create(new URL(url + "?wsdl"), serviceName);
		port = service.getPort(ProcessStructureTreeSEI.class);

		// Register resource factories
		resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getFile());
		Map<String, Object> map = Resource.Factory.Registry.INSTANCE
				.getExtensionToFactoryMap();
		map.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		map.putIfAbsent("bpmnanalysis", new BpmnAnalysisResourceFactoryImpl());
		map.putIfAbsent("bpmnmatching", new BpmnMatchingResourceFactoryImpl());

	}

	@After
	public void tearDown() {
		endpoint.stop();
	}

	/**
	 * Production ready!!!
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAnalyze() throws Exception {
		// create some test data
		Resource resource = resSet.loadResource("PM1-mit-Fragment1.bpmn");
		Definitions definitions = ((DocumentRoot) resource.getContents().get(0))
				.getDefinitions();
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);
		analysis = port.getPst(analysis);
		ProcessStructureTree pst = (ProcessStructureTree) analysis.getResults()
				.get("pst");
		assertNotNull(pst);
		// TODO: assertions
	}
}
