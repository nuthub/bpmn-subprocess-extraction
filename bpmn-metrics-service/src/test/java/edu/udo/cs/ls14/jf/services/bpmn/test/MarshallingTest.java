package edu.udo.cs.ls14.jf.services.bpmn.test;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Ignore;
import org.junit.Test;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

public class MarshallingTest {

	@Test
	@Ignore
	public void testMarshallPair() throws Exception {
		java.io.StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(TestObject.class);
		javax.xml.bind.Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		TestObject testObject = new TestObject();
		marshaller.marshal(testObject, sw);
		String xml = sw.toString();
		printXml(xml);

		Unmarshaller unmarshaller = context.createUnmarshaller();
		TestObject testObject2 = (TestObject) unmarshaller
				.unmarshal(new StringReader(xml));
//		System.out.println(testObject2.task.getName());
		// System.out.println(testObject2.profile2.get(0).key + " => " +
		// testObject2.profile2.get(0).value);
		// System.out.println(testObject2.profile2.get(1).key + " => " +
		// testObject2.profile2.get(1).value);
//		System.out.println("profile: " + testObject2.profile);

	}

	private void printXml(String xml) throws Exception {
		// Formatted output
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(
				OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "1");
		transformer.transform(new StreamSource(new StringReader(xml)),
				new StreamResult(System.out));
	}
}
