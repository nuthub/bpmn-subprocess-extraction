package edu.udo.cs.ls14.jf.utils.bpmn.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.Definitions;
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
	 * File -> XML -> Defintions -> XML -> Definitions
	 */
	private void runTest(String fileName) throws Exception {
		// get XML
		String xml1 = readFile(fileName);
		// convert XML to EObject
		Definitions definitions1 = (Definitions) BpmnXmlConverter.xml2Bpmn(
				xml1, Definitions.class);
		// convert EObject to XML
		String xml2 = BpmnXmlConverter
				.bpmn2Xml(definitions1, Definitions.class);
		// convert XML to EObject
		Definitions definitions2 = (Definitions) BpmnXmlConverter.xml2Bpmn(
				xml2, Definitions.class);
		// compare
		assertEquals(definitions1.getId(), definitions2.getId());

	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}

}
