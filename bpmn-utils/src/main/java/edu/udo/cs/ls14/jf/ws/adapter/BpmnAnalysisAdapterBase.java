package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;

public abstract class BpmnAnalysisAdapterBase<T extends EObject> extends
		XmlAdapter<String, T> {

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		registerFactoriesAndPackages();
		return (T) EObjectXmlConverter.xml2EObject("bpmnanalysis", v);
	}

	@Override
	public String marshal(T v) throws Exception {
		registerFactoriesAndPackages();
		return EObjectXmlConverter.eObject2Xml("bpmnanalysis", v);
	}

	private void registerFactoriesAndPackages() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnanalysis",
						new BpmnAnalysisResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		EPackage.Registry.INSTANCE.put(BpmnAnalysisPackage.eNS_URI,
				BpmnAnalysisPackage.eINSTANCE);

	}
}
