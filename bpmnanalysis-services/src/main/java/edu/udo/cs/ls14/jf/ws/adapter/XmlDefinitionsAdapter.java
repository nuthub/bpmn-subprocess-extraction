package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;

public class XmlDefinitionsAdapter extends XmlAdapter<String, Definitions> {

	@Override
	public Definitions unmarshal(String v) throws Exception {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		return (Definitions) EObjectXmlConverter.xml2EObject("bpmn", v);
	}

	@Override
	public String marshal(Definitions v) throws Exception {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		return EObjectXmlConverter.eObject2Xml("bpmn", v);
	}

}
