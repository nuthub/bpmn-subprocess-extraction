package edu.udo.cs.ls14.jf.examples.jaxws;

import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {

	private final static Logger LOG = Logger.getLogger(Hello.class.getName());

	@WebMethod
	public String sayHello(String name) {
		LOG.info("say hello");
		return "Hello " + name + ".";
	}


}
