package edu.udo.cs.ls14.jf.bpmn.ws.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;

/**
 * Serializes EObjects to XML and deserializes XML to EObjects.
 * 
 * @author Julian Flake
 *
 */
public class EObjectXmlConverter {

	public static String eObject2Xml(String extension, EObject eObject)
			throws Exception {
		Resource res = Bpmn2ResourceSet.getInstance().createResource(
				URI.createURI(UUID.randomUUID().toString() + "." + extension));
		res.getContents().add(eObject);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		res.save(os, getOptions());
		return os.toString();
	}

	public static EObject xml2EObject(String extension, String xmlString)
			throws Exception {
		Resource res = Bpmn2ResourceSet.getInstance().createResource(
				URI.createFileURI(UUID.randomUUID().toString() + "."
						+ extension));
		res.load(new ByteArrayInputStream(xmlString.trim().getBytes()),
				getOptions());
		return res.getContents().get(0);
	}

	private static Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
		return options;
	}
}
