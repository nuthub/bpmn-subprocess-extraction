package edu.udo.cs.ls14.bpmn.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import javax.xml.transform.TransformerException;

import org.junit.Test;

import edu.udo.cs.ls14.bpmn.XSLTransformer;

public class XSLTTest {

	@Test
	public void testTransform() throws TransformerException, IOException, URISyntaxException {
		InputStream in = getClass().getResourceAsStream("/process_1.bpmn");
		InputStream xslt = getClass().getResourceAsStream("/BPMN20-ToXMI.xslt");
		OutputStream out = new FileOutputStream(new File("/tmp/out.xmi"));
		XSLTransformer.transform(in, xslt, out);
	}
}
