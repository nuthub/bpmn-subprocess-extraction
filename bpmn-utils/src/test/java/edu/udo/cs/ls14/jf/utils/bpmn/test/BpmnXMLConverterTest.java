package edu.udo.cs.ls14.jf.utils.bpmn.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.DocumentRoot;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.BpmnXmlConverter;

public class BpmnXMLConverterTest {

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

	/*
	 * File -> XML -> DocumentRoot -> XML -> DocumentRoot
	 */
	private void runTest(String fileName) throws Exception {
		// get XML
		String xml1 = readFile(fileName);
		// convert XML to EObject
		DocumentRoot docRoot1 = (DocumentRoot) BpmnXmlConverter.xml2Bpmn(xml1);
		// convert EObject to XML
		String xml2 = BpmnXmlConverter.bpmn2Xml(docRoot1.getDefinitions());
		// convert XML to EObject
		DocumentRoot docRoot2 = (DocumentRoot) BpmnXmlConverter.xml2Bpmn(xml2);
		// compare
		assertEquals(docRoot1.getDefinitions().getId(), docRoot2
				.getDefinitions().getId());

	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}

}
