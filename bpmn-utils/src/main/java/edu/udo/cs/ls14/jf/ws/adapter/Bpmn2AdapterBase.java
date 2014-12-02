package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.registry.Registries;

public abstract class Bpmn2AdapterBase<T extends EObject> extends
		XmlAdapter<String, T> {

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		Registries.registerBpmn();
		return (T) EObjectXmlConverter.xml2EObject("bpmn", v);
	}

	@Override
	public String marshal(T v) throws Exception {
		Registries.registerBpmn();
		return EObjectXmlConverter.eObject2Xml("bpmn", v);
	}

}
