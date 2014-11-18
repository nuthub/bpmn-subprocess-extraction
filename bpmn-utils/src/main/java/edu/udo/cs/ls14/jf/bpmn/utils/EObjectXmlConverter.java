package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

public class EObjectXmlConverter {

	private static final Logger LOG = LoggerFactory
			.getLogger(EObjectXmlConverter.class);

	/**
	 * Requires factories registered via
	 * Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
	 * 
	 * @param extension
	 * @param eObject
	 * @return
	 * @throws IOException
	 */
	public static String eObject2Xml(String extension, EObject eObject)
			throws Exception {
		Factory factory = (Factory) Resource.Factory.Registry.INSTANCE
				.getExtensionToFactoryMap().get(extension);
		if (factory == null) {
			throw new Exception(
					"There is no Resource.Factory registered for extension "
							+ extension
							+ ". Appropriate Resource.Factory has to be registered manually.");
		}
		Resource res = factory.createResource(URI.createURI(EcoreUtil
				.generateUUID() + "." + extension));
		LOG.info(
				"Trying to serialize: " + eObject);
		for(EObject eObj : eObject.eContents()) {
			LOG.info(eObj.toString());
			if(eObj instanceof FragmentMatching) {
				LOG.info("  " + eObj.eContents());
			}
		}
		res.getContents().add(eObject);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		res.save(os, getOptions());
		return os.toString();
	}

	/**
	 * Requires factories registered via
	 * Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
	 * 
	 * @param xmlString
	 * @return
	 * @throws IOException
	 */
	public static EObject xml2EObject(String extension, String xmlString)
			throws Exception {
		Resource.Factory factory = (Factory) Resource.Factory.Registry.INSTANCE
				.getExtensionToFactoryMap().get(extension);
		if (factory == null) {
			throw new Exception(
					"There is no Resource.Factory registered for extension '"
							+ extension
							+ "'. Appropriate Resource.Factory has to be registered manually.");
		}
		Resource xres = factory.createResource(URI.createURI(EcoreUtil
				.generateUUID() + "." + extension));
		xres.load(new ByteArrayInputStream(xmlString.trim().getBytes()),
				getOptions());
		return xres.getContents().get(0);
	}

	private static Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
		// options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		return options;
	}
}
