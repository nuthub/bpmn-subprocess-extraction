package edu.udo.cs.ls14.jf.utils.bpmn.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;

public class EObjectXmlConverterTest {

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

	private void runTest(String fileName) throws Exception {
		String xml1 = readFile(fileName);
		Resource.Factory resFactory = new Bpmn2ResourceFactoryImpl();
		// convert XML to EObject
		DocumentRoot docRoot1 = (DocumentRoot) EObjectXmlConverter.xml2EObject(
				resFactory, xml1);

		// convert EObject to XML
		String xml2 = EObjectXmlConverter.eObject2Xml(resFactory, docRoot1);

		// convert XML to EObject
		DocumentRoot docRoot2 = (DocumentRoot) EObjectXmlConverter.xml2EObject(
				resFactory, xml2);

		assertEquals(docRoot1.getDefinitions().getId(), docRoot2
				.getDefinitions().getId());

	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}

}
