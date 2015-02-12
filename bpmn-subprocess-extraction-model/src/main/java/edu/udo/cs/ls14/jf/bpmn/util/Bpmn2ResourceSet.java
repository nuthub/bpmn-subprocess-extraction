package edu.udo.cs.ls14.jf.bpmn.util;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * Resource Set singleton for BPMN2 resources.
 * 
 * @author Julian Flake
 *
 */
public class Bpmn2ResourceSet extends ResourceSetImpl {
	private static Bpmn2ResourceSet instance;

	/**
	 * Hide public constructor.
	 */
	private Bpmn2ResourceSet() {
		super();
	}

	/**
	 * Returns singleton instance of this resource set and creates it if it has
	 * not been created yet.
	 * 
	 * @return the singleton instance
	 */

	public static Bpmn2ResourceSet getInstance() {
		if (instance == null) {
			instance = new Bpmn2ResourceSet();
		}
		return instance;
	}

	/**
	 * Forces reinitialization.
	 */
	public void clear() {
		instance = new Bpmn2ResourceSet();
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
		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.containsKey("bpmn")) {
			Registries.registerBpmn();
		}
		Resource resource = Registries.getResourceFactory("bpmn")
				.createResource(fileURI);
		resource.getContents().add(definitions);
		getResources().add(resource);
		return resource;
	}

	/**
	 * Loads Definitions object from a file.
	 * 
	 * @param filename
	 *            path to BPMN XML file
	 * @return loaded Definitions object
	 * @throws Exception
	 *             if Definitions could not be loaded
	 */
	public Definitions loadDefinitions(String filename) throws Exception {
		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.containsKey("bpmn")) {
			Registries.registerBpmn();
		}
		URI fileURI = URI.createFileURI(filename);
		Resource resource = Registries.getResourceFactory("bpmn")
				.createResource(fileURI);
		resource.load(null);
		getResources().add(resource);
		return ((DocumentRoot) resource.getContents().get(0)).getDefinitions();
	}

}
