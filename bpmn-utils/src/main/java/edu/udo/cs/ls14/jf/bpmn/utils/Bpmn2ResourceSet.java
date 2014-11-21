package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class Bpmn2ResourceSet extends ResourceSetImpl {
	private String directory;
	private Bpmn2ResourceFactoryImpl resourceFactory;

	private Bpmn2ResourceSet() {
		super();
		getPackageRegistry().put(Bpmn2Package.eNS_URI, Bpmn2Package.eINSTANCE);
		getResourceFactoryRegistry().getExtensionToFactoryMap().put("bpmn",
				new Bpmn2ResourceFactoryImpl());
		resourceFactory = new Bpmn2ResourceFactoryImpl();
	}

	public Bpmn2ResourceSet(String baseDir) {
		this();
		directory = baseDir;
	}

	public String getDirectory() {
		return directory;
	}

	protected URI getCompletePathURI(String filename, String directory,
			boolean absolute) {
		StringBuilder builder = new StringBuilder(directory).append("/");
		builder.append(filename);
		return URI.createFileURI(absolute ? new File(builder.toString())
				.getAbsolutePath() : builder.toString());
	}

	public Resource createResource(String filename,
			Definitions definitions) {
		URI fileURI = getCompletePathURI(filename, directory, true);
		Resource resource = resourceFactory.createResource(fileURI);
		resource.getContents().add(definitions);
		getResources().add(resource);
		return resource;
	}

	public Resource loadResource(String filename) throws FileNotFoundException,
			IOException {
		URI fileURI = getCompletePathURI(filename, directory, true);
		Resource resource = resourceFactory.createResource(fileURI);
		resource.load(new BufferedInputStream(new FileInputStream(resource
				.getURI().toFileString())), null);
		getResources().add(resource);
		return resource;
	}

	public Definitions loadDefinitions(String filename)
			throws FileNotFoundException, IOException {
		return ((DocumentRoot) loadResource(filename).getContents().get(0))
				.getDefinitions();
	}

	public Resource getResource(String filename) {
		return getResource(getCompletePathURI(filename, directory, true), false);

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
