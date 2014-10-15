package edu.udo.cs.ls14.jf.utils.bpmn;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ProcessLoader {

	public static BPMNDiagram getDiagramFromResource(Resource res)
			throws Exception {
		EList<EObject> contents = res.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		List<BPMNDiagram> diagrams = docRoot.getDefinitions().getDiagrams();
		if (diagrams.isEmpty()) {
			throw new Exception("no diagrams found");
		}
		return diagrams.get(0);
	}

	private static DocumentRoot getDocumentRootFromUrl(URL url)
			throws Exception {
		Resource res = getBpmnResource(url);
		EList<EObject> contents = res.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		return docRoot;
	}

	private static Definitions getDefinitionsFromUrl(URL url) throws Exception {
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

	public static Resource getBpmnResource(URL url) throws Exception {
		if (url == null) {
			throw new Exception("URL ist null!");
		}
		registerFactories();
		URI fileUri = URI.createFileURI(url.getFile());
		org.eclipse.emf.ecore.resource.Resource res = new ResourceSetImpl()
				.getResource(fileUri, true);
		return res;
	}

	public static Resource getBpmnResource(File file) throws Exception {
		if (file == null) {
			throw new Exception("File ist null!");
		}
		registerFactories();
		URI fileUri = URI.createURI(file.getAbsolutePath());
		Resource res = new ResourceSetImpl().getResource(fileUri, true);
		return res;
	}

	public static Process getProcessFromResource(Resource resource)
			throws Exception {
		EList<EObject> contents = resource.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		List<RootElement> roots = docRoot.getDefinitions().getRootElements();
		if (roots.isEmpty()) {
			throw new Exception("no diagrams found");
		}
		return (Process) roots.get(0);
	}

	private static void registerFactories() {
		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.containsKey("bpmn2")) {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					"bpmn2", new Bpmn2ResourceFactoryImpl());
		}
		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.containsKey("bpmn")) {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					"bpmn", new Bpmn2ResourceFactoryImpl());
		}
	}

}
