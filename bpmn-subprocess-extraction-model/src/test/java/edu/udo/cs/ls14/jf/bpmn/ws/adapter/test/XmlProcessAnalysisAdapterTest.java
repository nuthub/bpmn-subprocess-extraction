package edu.udo.cs.ls14.jf.bpmn.ws.adapter.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlDefinitionsAdapter;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessAnalysisAdapter;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public class XmlProcessAnalysisAdapterTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testPM1() throws Exception {
		String filename = "/bpmn/parallelGateway/parallelism1.bpmn";
		XmlProcessAnalysisAdapter adapter = new XmlProcessAnalysisAdapter();

		// Create example ProcessAnalysis
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		analysis.setDefinitions(loadDefinitions(filename));

		// ProcessAnalysis =marshal=> XML2
		String xml2 = adapter.marshal(analysis);
		// XML2 =unmarshal=> ProcessAnalysis
		assertNotNull(adapter.unmarshal(xml2));

	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}

	private Definitions loadDefinitions(String filename) throws Exception {
		Definitions definitions = new XmlDefinitionsAdapter().unmarshal( readFile(filename));
		return definitions;
	}

}
