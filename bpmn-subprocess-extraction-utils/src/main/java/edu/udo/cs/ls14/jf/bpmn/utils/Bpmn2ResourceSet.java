package edu.udo.cs.ls14.jf.bpmn.utils;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import edu.udo.cs.ls14.jf.registry.Registries;

/**
 * Resource Set singleton for BPMN2 resources.
 * 
 * @author Julian Flake
 *
 */
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
	 *            Definitions object to add to created resource
	 * @return created Resource
	 * @throws Exception
	 *             if an error occurs
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
		Resource resource = Registries.getResourceFactory("bpmn")
				.createResource(fileURI);
		resource.load(null);
		getResources().add(resource);
		return ((DocumentRoot) resource.getContents().get(0)).getDefinitions();
	}

}
