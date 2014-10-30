package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class EObjectXmlConverter {

	public static EObject xml2EObject(String xmlString) throws IOException {
		EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		XMLResource xres = new XMLResourceImpl();
		xres.load(new ByteArrayInputStream(xmlString.trim().getBytes()), null);
		return xres.getContents().get(0);
	}

	public static String eObject2Xml(EObject eObject) throws IOException {
		XMLResource xres = new XMLResourceImpl();
		xres.getContents().add(eObject);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xres.save(os, null);
		return os.toString();
	}
	
}
