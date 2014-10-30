package edu.udo.cs.ls14.jf.examples.jaxws.client.test;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.examples.jaxws.Hello;

public class TestHelloService {

	private static final String URL_STRING = "http://localhost:8080/emf-ws-example/HelloService?wsdl";
	private static final String SERVICE_URI = "http://jaxws.examples.jf.ls14.cs.udo.edu/";

	@Test
	@Ignore	// erfordert laufenden AS auf localhost:8080
	public void testSayHello() throws MalformedURLException {
		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName(SERVICE_URI, "HelloService");
		Service service = Service.create(new URL(URL_STRING), qname);
		Hello hello = service.getPort(Hello.class);
		System.out.println(hello.sayHello("Julian"));
	}
}
