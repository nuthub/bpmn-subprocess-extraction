package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;

public class XmlBpmnAnalysisAdapter<T extends EObject> extends XmlAdapter<String, T> {

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String v) throws Exception {
		return (T) EObjectXmlConverter.xml2EObject("bpmnanalysis", v);
	}

	@Override
	public String marshal(T v) throws Exception {
		return EObjectXmlConverter.eObject2Xml("bpmnanalysis", v);
	}

}
