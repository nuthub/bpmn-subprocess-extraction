package edu.udo.cs.ls14.jf.utils.bpmn;

import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ResourceCopier {

	public static Resource copy(Resource resource, String targetFile)
			throws Exception {
		registerFactories();
		ResourceSet resSet = resource.getResourceSet();
		URI resourceUri = URI.createFileURI(targetFile);
		Resource res = resSet.createResource(resourceUri);

		EcoreUtil.Copier copier = new EcoreUtil.Copier();
		res.getContents().addAll(copier.copyAll(resource.getContents()));
		copier.copyReferences();
		res.save(null);
		return res;
	}

	// TODO: also in ProcessLoader
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
