package edu.udo.cs.ls14.jf.bpmn.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * XMLAdapter implementation for ProcessTransformation objects of the
 * BpmnTransformation package.
 * 
 * @author Julian Flake
 */
public class XmlProcessTransformationAdapter extends
		XmlAdapter<String, ProcessTransformation> {

	@Override
	public ProcessTransformation unmarshal(String v) throws Exception {
		Registries.registerAll();
		return (ProcessTransformation) EObjectXmlConverter.xml2EObject(
				"bpmntransformation", v);
	}

	@Override
	public String marshal(ProcessTransformation v) throws Exception {
		Registries.registerAll();
		return EObjectXmlConverter.eObject2Xml("bpmntransformation", v);
	}

}
