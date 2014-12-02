package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;

public class EObjectXmlConverter {

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
		Resource res = factory.createResource(URI.createURI(UUID.randomUUID()
				.toString() + "." + extension));
		res.getContents().add(eObject);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		res.save(os, null);
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
		Resource xres = factory.createResource(URI.createURI(UUID.randomUUID()
				.toString() + "." + extension));
		xres.load(new ByteArrayInputStream(xmlString.trim().getBytes()), null);
		return xres.getContents().get(0);
	}
}
