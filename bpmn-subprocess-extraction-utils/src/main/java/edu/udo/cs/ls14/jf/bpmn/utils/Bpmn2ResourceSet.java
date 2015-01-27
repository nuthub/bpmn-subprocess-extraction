package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.File;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.emf.common.util.URI;
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

	/**
	 * 
	 * @param fileURI
	 *            should be a URI created by URI.createFileUri (isRelative() ==
	 *            false)
	 * @param definitions
	 * @return
	 * @throws Exception
	 */
	public Resource createResource(URI fileURI, Definitions definitions)
			throws Exception {
		Resource resource = Registries.getResourceFactory("bpmn")
				.createResource(fileURI);
		resource.getContents().add(definitions);
		 getResources().add(resource);
		return resource;
	}

	public Definitions loadDefinitions(String filename) throws Exception {
		URI fileURI = URI.createFileURI(filename);
		// URI fileURI = getCompletePathURI(filename, true);
		Resource resource = Registries.getResourceFactory("bpmn")
				.createResource(fileURI);
//		resource.load(new BufferedInputStream(new FileInputStream(resource
//				.getURI().toFileString())), null);
		resource.load(null);
		getResources().add(resource);
		return ((DocumentRoot) resource.getContents().get(0)).getDefinitions();
	}

	@Deprecated
	public Resource getResource(String filename) {
		return getResource(getCompletePathURI(filename, true), false);

	}

	@Deprecated
	protected URI getCompletePathURI(String filename, boolean absolute) {
		StringBuilder builder = new StringBuilder("/");
		builder.append(filename);
		return URI.createFileURI(absolute ? new File(builder.toString())
				.getAbsolutePath() : builder.toString());
	}

	@Deprecated
	public Resource createResource(String filename, Definitions definitions)
			throws Exception {
		URI fileURI = getCompletePathURI(filename, true);
		return createResource(fileURI, definitions);
	}
}
