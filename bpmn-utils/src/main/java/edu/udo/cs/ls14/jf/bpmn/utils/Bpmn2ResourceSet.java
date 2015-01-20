package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import edu.udo.cs.ls14.jf.registry.Registries;

public class Bpmn2ResourceSet extends ResourceSetImpl {
	private static Bpmn2ResourceSet instance;

	private Bpmn2ResourceSet() {
		super();
	}

	public static Bpmn2ResourceSet getInstance() {
		if (instance == null) {
			instance = new Bpmn2ResourceSet();
		}
		return instance;
	}

	protected URI getCompletePathURI(String filename, boolean absolute) {
		StringBuilder builder = new StringBuilder("/");
		builder.append(filename);
		return URI.createFileURI(absolute ? new File(builder.toString())
				.getAbsolutePath() : builder.toString());
	}

	public Bpmn2Resource createResource(URI fileURI, Definitions definitions)
			throws Exception {
		Bpmn2Resource resource = (Bpmn2Resource) Registries.getResourceFactory(
				"bpmn").createResource(fileURI);
		resource.getContents().add(definitions);
		getResources().add(resource);
		return resource;
	}

	public Bpmn2Resource createResource(String filename, Definitions definitions)
			throws Exception {
		URI fileURI = getCompletePathURI(filename, true);
		return createResource(fileURI, definitions);
	}

	public Definitions loadDefinitions(String filename) throws Exception {
		URI fileURI = getCompletePathURI(filename, true);
		Bpmn2Resource resource = (Bpmn2Resource) Registries.getResourceFactory(
				"bpmn").createResource(fileURI);
		resource.load(new BufferedInputStream(new FileInputStream(resource
				.getURI().toFileString())), null);
		getResources().add(resource);
		return ((DocumentRoot) resource.getContents().get(0)).getDefinitions();
	}

	public Bpmn2Resource getResource(String filename) {
		return (Bpmn2Resource) getResource(getCompletePathURI(filename, true),
				false);

	}

	public String toContentString() {
		StringBuffer sb = new StringBuffer();
		for (Resource res : getResources()) {
			sb.append(res);
			sb.append(System.getProperty("line.separator"));
			for (EObject eObj : res.getContents()) {
				sb.append("  - " + eObj);
				sb.append(System.getProperty("line.separator"));
				for (EObject eObj2 : eObj.eContents()) {
					sb.append("    - " + eObj2);
					sb.append(System.getProperty("line.separator"));
				}
			}
			sb.append("---");
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

}
