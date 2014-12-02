package edu.udo.cs.ls14.jf.registry;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class Registries {

	public static void registerAll() {
		registerBpmn();
		registerBpmnAnalysis();
		registerBpmnMatching();
		registerBpmnTransformation();
	}

	public static void registerBpmnAnalysis() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnanalysis",
						new BpmnAnalysisResourceFactoryImpl());
		EPackage.Registry.INSTANCE.putIfAbsent(BpmnAnalysisPackage.eNS_URI,
				BpmnAnalysisPackage.eINSTANCE);

	}

	public static void registerBpmnMatching() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnmatching",
						new BpmnMatchingResourceFactoryImpl());
		EPackage.Registry.INSTANCE.putIfAbsent(BpmnMatchingPackage.eNS_URI,
				BpmnMatchingPackage.eINSTANCE);
	}

	public static void registerBpmnTransformation() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmntransformation",
						new BpmnTransformationResourceFactoryImpl());
		EPackage.Registry.INSTANCE.putIfAbsent(
				BpmnTransformationPackage.eNS_URI,
				BpmnTransformationPackage.eINSTANCE);
	}

	public static void registerBpmn() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		EPackage.Registry.INSTANCE.putIfAbsent(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
	}

	public static Resource.Factory getResourceFactory(String name)
			throws Exception {
		if (!Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.containsKey(name)) {
			throw new Exception("Resource.Factory for extension " + name
					+ " not registered.");
		}
		return ((Resource.Factory) Resource.Factory.Registry.INSTANCE
				.getExtensionToFactoryMap().get(name));
	}

}
