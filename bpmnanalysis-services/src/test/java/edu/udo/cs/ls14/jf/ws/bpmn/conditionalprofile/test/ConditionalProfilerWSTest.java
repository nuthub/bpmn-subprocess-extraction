package edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.eclipse.bpmn2.Definitions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.registry.Registries;
import edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile.ConditionalProfilerImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile.ConditionalProfilerSEI;

public class ConditionalProfilerWSTest {

	private String url = "http://localhost:8880/ConditionalProfilerService";
	private Endpoint endpoint;
	private Service service;
	private ConditionalProfilerSEI port;
	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() throws Exception {
		// Create WebService endpoint
		endpoint = Endpoint.create(new ConditionalProfilerImpl());
		endpoint.publish(url);

		// Create WebService client port
		QName serviceName = new QName(
				"http://conditionalprofile.bpmn.ws.jf.ls14.cs.udo.edu/",
				"ConditionalProfilerImplService");
		service = Service.create(new URL(url + "?wsdl"), serviceName);
		port = service.getPort(ConditionalProfilerSEI.class);

		resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getFile());
		// Register resource factories
		Registries.registerAll();
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
		Definitions definitions = resSet.loadDefinitions("PM1-mit-Fragment1.bpmn");
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);
		analysis = port.profile(analysis);
		ConditionalProfile profile = (ConditionalProfile) analysis.getResults()
				.get("conditionalProfile");
		assertNotNull(profile);
		assertEquals(7, profile.getRelations().size());
		// TODO: assertions
	}
}
