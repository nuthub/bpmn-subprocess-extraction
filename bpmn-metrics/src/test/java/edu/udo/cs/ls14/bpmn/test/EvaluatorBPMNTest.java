package edu.udo.cs.ls14.bpmn.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import javax.xml.transform.TransformerException;

import org.eclipse.ocl.ParserException;
import org.junit.Test;

import edu.udo.cs.ls14.bpmn.XSLTransformer;

public class EvaluatorBPMNTest extends EvaluatorBaseTest {

	private static final String MODEL_RESOURCE = "/process_1.bpmn";
	// Slightly modified XSLT file (removed Point type declaration inside waypoint)
	private static final String XSLT_RESOURCE = "/BPMN20-ToXMI.xslt";
	private static final String XMI_FILENAME = "/tmp/process_1.bpmn2xmi";
	private static final String OCL_RESOURCE = "/Measures.ocl";

	@Test
	public void testBpmnWithOclFile() throws ParserException, IOException,
			URISyntaxException, TransformerException {
		// Create bpmn2xmi from bpmn file
		InputStream in = getClass().getResourceAsStream(MODEL_RESOURCE);
		InputStream xslt = getClass().getResourceAsStream(XSLT_RESOURCE);
		File xmiFile = new File(XMI_FILENAME);
		OutputStream out = new FileOutputStream(xmiFile);
		XSLTransformer.transform(in, xslt, out);
		// Get ocl stream
		InputStream oclStream = getClass().getResourceAsStream(OCL_RESOURCE);
		// Evaluate
		System.out.println(evaluate(xmiFile.getPath(), oclStream));
	}

}
