package edu.udo.cs.ls14.jf.examples.jaxws.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Task;
import org.eclipse.emf.ecore.EObject;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.examples.jaxws.Analyzer;

public class TestAnalyzerService {

	private static final String URL_STRING = "http://localhost:8080/emf-ws-example/AnalyzerService?wsdl";
	private static final String SERVICE_URI = "http://jaxws.examples.jf.ls14.cs.udo.edu/";

	@Test
	@Ignore // erfordert laufenden AS auf localhost:8080
	public void testGetProcessId() throws MalformedURLException {
		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName(SERVICE_URI, "AnalyzerService");
		Service service = Service.create(new URL(URL_STRING), qname);
		Analyzer hello = service.getPort(Analyzer.class);
		Task task = Bpmn2Factory.eINSTANCE.createTask();
		task.setName("Hello Task");
		try {
			System.out.println("Task: " + task);
			String xmlString = EObjectXmlConverter.eObject2Xml(task);
			String responseXml = hello.analyze(xmlString);
			EObject eobj = EObjectXmlConverter.xml2EObject(responseXml);
			if(!(eobj instanceof Task)) {
				fail("response is no task");
			}
			Task responseTask = (Task) eobj;
			System.out.println("responseTask: " + responseTask);
			assertEquals(task.getName(), responseTask.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
