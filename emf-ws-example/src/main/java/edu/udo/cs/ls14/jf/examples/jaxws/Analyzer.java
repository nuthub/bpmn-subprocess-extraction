package edu.udo.cs.ls14.jf.examples.jaxws;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;

@WebService
public class Analyzer {

	private static final Logger LOG = Logger
			.getLogger(Analyzer.class.getName());

	/**
	 * Expects xmi of definitions object
	 * 
	 * @param xmlString
	 * @return
	 */
	@WebMethod
	public String analyze(String xmlString) {
		LOG.info("definitionsXml: " + xmlString);
		EObject eobject = null;
		try {
			eobject = EObjectXmlConverter.xml2EObject(xmlString);
			LOG.info("Received: " + eobject.getClass().getName() + ": "
					+ eobject);
			String responseXml = EObjectXmlConverter.eObject2Xml(eobject);
			LOG.info("returning. " + responseXml);
			return responseXml;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
