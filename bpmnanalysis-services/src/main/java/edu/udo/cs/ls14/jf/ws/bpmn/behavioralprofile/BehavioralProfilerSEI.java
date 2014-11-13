package edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.ws.adapter.XmlBehavioralProfileAdapter;
import edu.udo.cs.ls14.jf.ws.adapter.XmlDefinitionsAdapter;

@WebService
public interface BehavioralProfilerSEI {

	@WebMethod
	@WebResult(name = "behavioralProfileXml")
	@XmlJavaTypeAdapter(XmlBehavioralProfileAdapter.class)
	public BehavioralProfile profile(
			@WebParam(name = "definitionsXml") @XmlJavaTypeAdapter(XmlDefinitionsAdapter.class) Definitions definitions)
			throws Exception;
}
