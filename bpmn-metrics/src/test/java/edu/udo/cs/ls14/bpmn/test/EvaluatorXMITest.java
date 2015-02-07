package edu.udo.cs.ls14.bpmn.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.xml.transform.TransformerException;

import org.eclipse.ocl.ParserException;
import org.junit.Test;

public class EvaluatorXMITest extends EvaluatorBaseTest {

	private static final String XMI_RESOURCE = "/process_1.bpmn2xmi";
	private static final String OCL_RESOURCE = "/Measures.ocl";

	@Test
	public void testBpmnWithOclFile() throws ParserException, IOException,
			URISyntaxException, TransformerException {
		// Load created bpmn2xmi
		String xmiPath = getClass().getResource(
				 XMI_RESOURCE).getPath();
		// Get ocl stream
		InputStream oclStream = getClass().getResourceAsStream(
				OCL_RESOURCE);
		// Evaluate
		System.out.println(evaluate(xmiPath, oclStream));
	}

}
