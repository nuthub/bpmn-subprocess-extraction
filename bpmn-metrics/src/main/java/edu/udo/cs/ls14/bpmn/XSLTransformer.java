package edu.udo.cs.ls14.bpmn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransformer {

	public static void transform(InputStream in, InputStream xslt,
			OutputStream out) throws IOException, URISyntaxException,
			TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xsltSource = new StreamSource(xslt);
		Transformer transformer = factory.newTransformer(xsltSource);
		System.out.println(transformer.getOutputProperties());
		Source textSource = new StreamSource(in);
		transformer.transform(textSource, new StreamResult(out));
	}

}