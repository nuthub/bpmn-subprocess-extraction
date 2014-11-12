package edu.udo.cs.ls14.jf.services.bpmn.test;

import static org.junit.Assert.assertEquals;
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
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.services.bpmn.BpmnAnalysis;
import edu.udo.cs.ls14.jf.services.bpmn.BpmnAnalysisSEI;

public class BpmnAnalysisTest {

	private String url = "http://localhost:8880/BpmnAnalysisTest";
	private Endpoint endpoint;
	private Service service;
	private BpmnAnalysisSEI port;

	@Before
	public void setUp() throws Exception {
		// Create WebService endpoint
		endpoint = Endpoint.create(new BpmnAnalysis());
		endpoint.publish(url);

		// Create WebService client port
		QName serviceName = new QName(
				"http://bpmn.services.jf.ls14.cs.udo.edu/",
				"BpmnAnalysisService");
		service = Service.create(new URL(url + "?wsdl"), serviceName);
		port = service.getPort(BpmnAnalysisSEI.class);
		System.out.println(service.getWSDLDocumentLocation());

		// Register resource factories
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
		Resource resource = new Bpmn2ResourceSet(
				"src/test/resources/edu/udo/cs/ls14/jf/services/bpmn/test")
				.loadResource("PM1-mit-Fragment1.bpmn");
		Definitions definitions = ((DocumentRoot) resource.getContents().get(0))
				.getDefinitions();
		System.out.println("Request: " + definitions);
		ProcessAnalysis analysisOut = port.analyze(definitions);
		System.out.println("Response: " + analysisOut.getResults());
		assertNotNull(analysisOut);
		assertEquals(3, analysisOut.getResults().size());
	}

}
