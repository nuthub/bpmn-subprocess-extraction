package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.IOException;

import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class BpmnXmlConverter {

	private static Resource.Factory factory = new Bpmn2ResourceFactoryImpl();

	public static EObject xml2Bpmn(String xmlString) throws IOException {
		return EObjectXmlConverter.xml2EObject(factory, xmlString);
	}

	public static EObject xml2Bpmn(String xmlString,
			Class<? extends EObject> expectedClass) throws IOException {
		return EObjectXmlConverter.xml2EObject(factory, xmlString,
				expectedClass);
	}

	public static String bpmn2Xml(EObject eObject) throws IOException {
		return EObjectXmlConverter.eObject2Xml(factory, eObject);
	}

	public static String bpmn2Xml(EObject eObject,
			Class<? extends EObject> expectedClass) throws IOException {
		return EObjectXmlConverter.eObject2Xml(factory, eObject, expectedClass);
	}

}
