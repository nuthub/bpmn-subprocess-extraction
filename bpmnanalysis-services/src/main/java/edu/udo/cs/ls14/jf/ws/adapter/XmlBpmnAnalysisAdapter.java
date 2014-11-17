package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;

public class XmlBpmnAnalysisAdapter<T extends EObject> extends
		XmlAdapter<String, T> {

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnanalysis",
						new BpmnAnalysisResourceFactoryImpl());
		return (T) EObjectXmlConverter.xml2EObject("bpmnanalysis", v);
	}

	@Override
	public String marshal(T v) throws Exception {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnanalysis",
						new BpmnAnalysisResourceFactoryImpl());
		return EObjectXmlConverter.eObject2Xml("bpmnanalysis", v);
	}

}
