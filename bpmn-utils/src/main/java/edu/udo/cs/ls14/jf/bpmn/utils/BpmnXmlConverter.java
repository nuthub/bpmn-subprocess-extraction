package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.bpmn2.util.Bpmn2XMLProcessor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

public class BpmnXmlConverter {

	private static XMLProcessor proc = new Bpmn2XMLProcessor();

	/**
	 * Deserializes xml to Definitions object.
	 * 
	 * @param xmlString
	 * @return
	 * @throws IOException
	 */
	public static Definitions xml2Bpmn(String xmlString) throws IOException {
		ByteArrayInputStream is = new ByteArrayInputStream(xmlString.getBytes());
		Resource resource = proc.load(is, null);
		return ((DocumentRoot) resource.getContents().get(0)).getDefinitions();
	}

	/**
	 * Serializes Definitions object to xml string.
	 */
	public static String bpmn2Xml(Definitions definitions) throws IOException {
		proc.getEPackageRegistry().putIfAbsent(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		Resource res = new Bpmn2ResourceFactoryImpl().createResource(URI
				.createURI(""));
		res.getContents().add(EcoreUtil.copy(definitions));
		return proc.saveToString(res, null);
	}

}
