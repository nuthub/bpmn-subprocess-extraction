package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.registry.Registries;

/**
 * Abstract XMLAdapter implementation for objects of the BpmnMatching package.
 * 
 * @author JulianFlake
 *
 * @param <T> concrete Type
 */
public abstract class BpmnMatchingAdapterBase<T extends EObject> extends
		XmlAdapter<String, T> {

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		Registries.registerAll();
		return (T) EObjectXmlConverter.xml2EObject("bpmnmatching", v);
	}

	@Override
	public String marshal(T v) throws Exception {
		Registries.registerAll();
		return EObjectXmlConverter.eObject2Xml("bpmnmatching", v);
	}

}
