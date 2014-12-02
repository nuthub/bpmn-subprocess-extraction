package edu.udo.cs.ls14.jf.ws.bpmn.adapter.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.registry.Registries;
import edu.udo.cs.ls14.jf.ws.adapter.XmlDefinitionsAdapter;
import edu.udo.cs.ls14.jf.ws.adapter.XmlProcessAnalysisAdapter;

public class XmlProcessAnalysisAdapterTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}
	
	@Test
	public void testXml() throws Exception {
		String xml = readFile("/edu/udo/cs/ls14/jf/bpmn/utils/test/bpmnAnalysis.xml");
		XmlProcessAnalysisAdapter adapter = new XmlProcessAnalysisAdapter();
		ProcessAnalysis analysis2 = adapter.unmarshal(xml);
		assertEquals("Definitions_1", analysis2.getDefinitions().getId());
		assertEquals(
				"process_1",
				DefinitionsUtil.getProcess(
						analysis2.getDefinitions()).getId());
	}

	@Test
	public void testPM1() throws Exception {
		String filename = "/edu/udo/cs/ls14/jf/bpmn/utils/test/empty.bpmn";
		XmlProcessAnalysisAdapter adapter = new XmlProcessAnalysisAdapter();

		// Create example ProcessAnalysis
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		analysis.setDefinitions(loadDefinitions(filename));

		// ProcessAnalysis =marshal=> XML2
		String xml2 = adapter.marshal(analysis);
		// XML2 =unmarshal=> ProcessAnalysis
		ProcessAnalysis analysis2 = adapter.unmarshal(xml2);

		// System.out.println(xml2);
		// System.out.println(analysis2.getDefinitions());
		System.out.println(DefinitionsUtil.getProcess(analysis2
				.getDefinitions()));
	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}

	private Definitions loadDefinitions(String filename) throws Exception {
		String xml = readFile(filename);
		Definitions definitions = new XmlDefinitionsAdapter().unmarshal(xml);
		return definitions;
	}

}
