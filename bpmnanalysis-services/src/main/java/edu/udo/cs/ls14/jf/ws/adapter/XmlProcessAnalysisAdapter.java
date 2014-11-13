package edu.udo.cs.ls14.jf.ws.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public class XmlProcessAnalysisAdapter extends XmlAdapter<String, ProcessAnalysis> {

	@Override
	public ProcessAnalysis unmarshal(String v) throws Exception {
		return (ProcessAnalysis) EObjectXmlConverter.xml2EObject("bpmnanalysis", v);
	}

	@Override
	public String marshal(ProcessAnalysis v) throws Exception {
		return EObjectXmlConverter.eObject2Xml("bpmnanalysis", v);
	}

}