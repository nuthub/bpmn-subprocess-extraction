package edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile.test;

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
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.registry.Registries;
import edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile.BehavioralProfilerImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile.BehavioralProfilerSEI;

public class BehavioralProfilerWSTest {

	private String url = "http://localhost:8880/BehavioralProfilerService";
	private Endpoint endpoint;
	private Service service;
	private BehavioralProfilerSEI port;
	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() throws Exception {
		// Create WebService endpoint
		endpoint = Endpoint.create(new BehavioralProfilerImpl());
		endpoint.publish(url);

		// Create WebService client port
		QName serviceName = new QName(
				"http://behavioralprofile.bpmn.ws.jf.ls14.cs.udo.edu/",
				"BehavioralProfilerImplService");
		service = Service.create(new URL(url + "?wsdl"), serviceName);
		port = service.getPort(BehavioralProfilerSEI.class);

		// Register resource factories
		Registries.registerAll();
		// Create Resource Set
		resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getFile());
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
		Definitions definitions = resSet
				.loadDefinitions("PM1-mit-Fragment1.bpmn");
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);
		analysis = port.profile(analysis);
		BehavioralProfile profile = (BehavioralProfile) analysis.getResults()
				.get("behavioralProfile");
		assertNotNull(profile);
		assertEquals(49, profile.getRelations().size());
	}
}
