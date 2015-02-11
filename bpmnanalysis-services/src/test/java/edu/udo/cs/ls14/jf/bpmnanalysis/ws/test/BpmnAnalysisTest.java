package edu.udo.cs.ls14.jf.bpmnanalysis.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.BpmnAnalysisSEI;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl.BpmnAnalysisImpl;

public class BpmnAnalysisTest {

	private String url = "http://localhost:8880/BpmnAnalysisTest";
	private Endpoint endpoint;
	private Service service;
	private BpmnAnalysisSEI port;
	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() throws Exception {
		// Create WebService endpoint
		endpoint = Endpoint.create(new BpmnAnalysisImpl());
		endpoint.publish(url);

		// Create WebService client port
		QName serviceName = new QName(
				"http://impl.ws.bpmnanalysis.jf.ls14.cs.udo.edu/",
				"BpmnAnalysisImplService");
		service = Service.create(new URL(url + "?wsdl"), serviceName);
		port = service.getPort(BpmnAnalysisSEI.class);

		resSet = Bpmn2ResourceSet.getInstance();
		// Register resource factories
		Registries.registerAll();
	}

	@After
	public void tearDown() {
		endpoint.stop();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAnalyze() throws Exception {
		// create some test data
		Definitions definitions = resSet.loadDefinitions(getClass()
				.getResource("/bpmn/parallelGateway/parallelism1.bpmn")
				.getPath());
		System.out.println("Request: " + definitions);
		ProcessAnalysis analysisOut = port.analyze(definitions);
		System.out.println("Response: " + analysisOut.getResults());
		assertNotNull(analysisOut);
		assertEquals(4, analysisOut.getResults().size());
	}

	@Test
	public void testEmptyDefinitions() throws Exception {
		Definitions definitions = Bpmn2Factory.eINSTANCE.createDefinitions();
		ProcessAnalysis analysisOut = port.analyze(definitions);
		assertNull(analysisOut);
	}

}
