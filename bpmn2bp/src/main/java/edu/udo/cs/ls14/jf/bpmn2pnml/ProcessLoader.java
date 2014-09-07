package edu.udo.cs.ls14.jf.bpmn2pnml;

import java.net.URL;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ProcessLoader {

	public static Process loadFirstProcessFromResource(URL url) throws Exception {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"bpmn2", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"bpmn", new Bpmn2ResourceFactoryImpl());
		URI fileUri = URI.createFileURI(url
				.getFile());
		Resource res = new ResourceSetImpl().getResource(fileUri, true);
		EList<EObject> contents = res.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		Definitions definitions = docRoot.getDefinitions();
		if (!(definitions.getRootElements().get(0) instanceof Process)) {
			throw new Exception("no process found");
		}
		Process process = (Process) definitions.getRootElements().get(0);
		return process;

	}
	
}
