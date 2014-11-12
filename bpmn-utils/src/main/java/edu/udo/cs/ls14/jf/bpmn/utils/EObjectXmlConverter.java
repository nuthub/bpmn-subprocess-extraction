package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class EObjectXmlConverter {

	public static EObject xml2EObject(Resource.Factory factory, String xmlString)
			throws IOException {
		Resource xres = factory.createResource(URI.createURI(""));
		xres.load(new ByteArrayInputStream(xmlString.trim().getBytes()),
				getOptions());
		return xres.getContents().get(0);
	}

	@Deprecated
	public static EObject xml2EObject(Resource.Factory factory,
			String xmlString, Class<? extends EObject> expectedClass)
			throws IOException {
		EObject eObject = xml2EObject(factory, xmlString);
		if (!expectedClass.isInstance(eObject)) {
			throw new IllegalArgumentException(
					"Provided XML does not contain a " + expectedClass);
		}
		return expectedClass.cast(eObject);
	}

	public static String eObject2Xml(Resource.Factory factory, EObject eObject)
			throws IOException {
		Resource xres = factory.createResource(URI.createURI(""));
		xres.getContents().add(eObject);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xres.save(os, getOptions());
		return os.toString();
	}

	public static String eObject2Xml(EObject eObject) throws IOException {
		XMLResource xres = new XMLResourceImpl();
		System.out.println("Eobject has " + eObject.eContents().size() + " children");
		xres.getContents().add(eObject);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xres.save(os, getOptions());
		System.out.println("xres has " + xres.getContents().get(0).eContents().size() + " children");
		return os.toString();
	}

	private static Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
//		options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
//		options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		return options;
	}
}
