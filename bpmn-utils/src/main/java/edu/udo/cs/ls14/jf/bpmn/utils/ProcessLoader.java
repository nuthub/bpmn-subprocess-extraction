package edu.udo.cs.ls14.jf.bpmn.utils;

import java.net.URL;
import java.util.List;

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

	public static Process getProcessFromResource(Resource resource)
			throws Exception {
		EList<EObject> contents = resource.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		List<RootElement> roots = docRoot.getDefinitions().getRootElements();
		if (roots.isEmpty()) {
			throw new Exception("empty definitions found");
		}
		for (RootElement root : roots) {
			if (root instanceof Process) {
				return (Process) root;
			}
		}
		throw new Exception("Process not found in resource "
				+ resource.getURI());
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
