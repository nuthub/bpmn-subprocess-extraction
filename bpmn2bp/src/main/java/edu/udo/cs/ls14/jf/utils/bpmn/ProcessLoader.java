package edu.udo.cs.ls14.jf.utils.bpmn;

import java.net.URL;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ProcessLoader {

	public static DocumentRoot getDocumentRootFromUrl(URL url) throws Exception {
		Resource res = getBpmnResource(url);
		EList<EObject> contents = res.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		return docRoot;
	}
	
	public static Definitions getDefinitionsFromUrl(URL url) throws Exception {
		DocumentRoot docRoot = getDocumentRootFromUrl(url);
		Definitions definitions = docRoot.getDefinitions();
		return definitions;
	}

	public static Process loadFirstProcessFromResource(URL url)
			throws Exception {
		Definitions definitions = getDefinitionsFromUrl(url);
		if (!(definitions.getRootElements().get(0) instanceof Process)) {
			throw new Exception("no process found");
		}
		Process process = (Process) definitions.getRootElements().get(0);
		return process;
	}

	public static BPMNDiagram getDiagram(URL url) throws Exception {
		DocumentRoot r = getDocumentRootFromUrl(url);
		List<BPMNDiagram> diagrams = r.getDefinitions().getDiagrams();
		if(diagrams.isEmpty()) {
			throw new Exception("no diagrams found");
		}
		return diagrams.get(0);
	}
	
	public static Resource getBpmnResource(URL url) throws Exception {
		if (url == null) {
			throw new Exception("URL ist null!");
		}
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"bpmn2", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"bpmn", new Bpmn2ResourceFactoryImpl());
		URI fileUri = URI.createFileURI(url.getFile());
		Resource res = new ResourceSetImpl().getResource(fileUri, true);
		return res;
	}

}
