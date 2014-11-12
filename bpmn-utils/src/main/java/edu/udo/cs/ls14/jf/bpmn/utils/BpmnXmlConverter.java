package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.bpmn2.util.Bpmn2XMLProcessor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

public class BpmnXmlConverter {

	private static XMLProcessor proc = new Bpmn2XMLProcessor();

	public static EObject xml2Bpmn(String xmlString) throws IOException {
		ByteArrayInputStream is = new ByteArrayInputStream(xmlString.getBytes());
		Resource resource = proc.load(is, null);
		return resource.getContents().get(0);
	}

	public static String bpmn2Xml(EObject eObject) throws IOException {
		proc.getEPackageRegistry().putIfAbsent(Bpmn2Package.eNS_URI, Bpmn2Package.eINSTANCE);
		Resource res = new Bpmn2ResourceFactoryImpl().createResource(URI
				.createURI(""));
		res.getContents().add(EcoreUtil.copy(eObject));
		return proc.saveToString(res, null);
	}

}
