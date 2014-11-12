package edu.udo.cs.ls14.jf.utils.bpmn.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;

public class EObjectXmlConverterTest {

	@Before
	public void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"bpmn", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"bpmnanalysis", new BpmnAnalysisResourceFactoryImpl());
	}

	@Test
	public void testConditionSequence() throws Exception {
		String filename = "/edu/udo/cs/ls14/jf/utils/bpmn/test/conditionSequence.bpmn";
		runTest(filename);
	}

	@Test
	public void testPM1() throws Exception {
		String filename = "/edu/udo/cs/ls14/jf/utils/bpmn/test/PM1-mit-Fragment1.bpmn";
		runTest(filename);
	}

	@Test
	public void testProcessAnalysis() throws Exception {
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		analysis.setId(EcoreUtil.generateUUID());
		String xml = EObjectXmlConverter.eObject2Xml("bpmnanalysis", analysis);
		ProcessAnalysis analysis2 = (ProcessAnalysis) EObjectXmlConverter
				.xml2EObject("bpmnanalysis", xml);
		assertEquals(analysis.getId(), analysis2.getId());
	}

	private void runTest(String fileName) throws Exception {
		String xml1 = readFile(fileName);
		// convert XML to EObject
		EObject eObj1 = EObjectXmlConverter.xml2EObject("bpmn", xml1);
		System.out.println(EObjectXmlConverter.xml2EObject("bpmn", xml1));

		// convert EObject to XML
		String xml2 = EObjectXmlConverter.eObject2Xml("bpmn", eObj1);

		// convert XML to EObject
		EObject eObj2 = EObjectXmlConverter.xml2EObject("bpmn", xml2);

		assertEquals(((Definitions) eObj1).getId(),
				((Definitions) eObj2).getId());

	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}

}
