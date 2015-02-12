package edu.udo.cs.ls14.jf.bpmn.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * XMLAdapter implementation for ProcessMatching objects.
 * 
 * @author Julian Flake
 *
 */
public class XmlProcessMatchingAdapter extends
		XmlAdapter<String, ProcessMatching> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessMatching unmarshal(String v) throws Exception {
		Registries.registerAll();
		return (ProcessMatching) EObjectXmlConverter.xml2EObject(
				"bpmnmatching", v);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String marshal(ProcessMatching v) throws Exception {
		Registries.registerAll();
		return EObjectXmlConverter.eObject2Xml("bpmnmatching", v);
	}

}
