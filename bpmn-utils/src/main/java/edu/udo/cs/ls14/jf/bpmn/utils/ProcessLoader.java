package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

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

public class ProcessLoader {

	public static Definitions getDefinitions(URL url) throws Exception {
		if (url == null) {
			throw new Exception("URL ist null!");
		}
		registerFactories();
		URI fileUri = URI.createFileURI(url.getFile());
		Resource resource = new Bpmn2ResourceFactoryImpl().createResource(URI
				.createURI(""));
		resource.load(new FileInputStream(fileUri.toFileString()), null);
		EList<EObject> contents = resource.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		Definitions definitions = docRoot.getDefinitions();
		return definitions;
	}

	public static BPMNDiagram getDiagramFromDefinitions(Definitions definitions)
			throws Exception {
		List<BPMNDiagram> diagrams = definitions.getDiagrams();
		if (diagrams.size() < 1) {
			throw new Exception("Definitions object contains no Diagram");
		} else if (diagrams.size() > 1) {
			throw new Exception(
					"Definitions object contains more than one Diagram");
		}
		return diagrams.get(0);
	}

	public static Process getProcessFromDefinitions(Definitions definitions)
			throws Exception {
		List<Process> processes = definitions.getRootElements().stream()
				.filter(r -> r instanceof Process).map(p -> (Process) p)
				.collect(Collectors.toList());
		if (processes.size() < 1) {
			throw new Exception("Definitions object contains no Process");
		} else if (processes.size() > 1) {
			throw new Exception(
					"Definitions object contains more than one Process");
		}
		return processes.get(0);
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


	@Deprecated
	public static Process getProcessFromResource(Resource resource)
			throws Exception {
		List<RootElement> roots = getDefinitionsFromResource(resource)
				.getRootElements();
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

	@Deprecated
	public static Definitions getDefinitionsFromResource(Resource resource)
			throws Exception {
		EList<EObject> contents = resource.getContents();
		if (!(contents.get(0) instanceof DocumentRoot)) {
			throw new Exception("load error");
		}
		DocumentRoot docRoot = ((DocumentRoot) contents.get(0));
		Definitions definitions = docRoot.getDefinitions();
		return definitions;
	}

}
