package edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.ws.adapter.XmlConditionalProfileAdapter;
import edu.udo.cs.ls14.jf.ws.adapter.XmlDefinitionsAdapter;

@WebService
public interface ConditionalProfilerSEI {

	@WebMethod
	@WebResult(name = "conditionalProfileXml")
	@XmlJavaTypeAdapter(XmlConditionalProfileAdapter.class)
	public ConditionalProfile profile(
			@WebParam(name = "definitionsXml") @XmlJavaTypeAdapter(XmlDefinitionsAdapter.class) Definitions definitions)
			throws Exception;
}
