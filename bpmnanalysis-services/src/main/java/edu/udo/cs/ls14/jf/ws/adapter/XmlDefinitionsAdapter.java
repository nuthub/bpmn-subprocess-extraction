package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;

public class XmlDefinitionsAdapter extends XmlAdapter<String, Definitions> {

	@Override
	public Definitions unmarshal(String v) throws Exception {
		return (Definitions) EObjectXmlConverter.xml2EObject("bpmn", v);
	}

	@Override
	public String marshal(Definitions v) throws Exception {
		return EObjectXmlConverter.eObject2Xml("bpmn", v);
	}

}
