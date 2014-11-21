package edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.ws.adapter.XmlProcessAnalysisAdapter;

@WebService
public interface ConditionalProfilerSEI {

	@WebMethod
	@WebResult(name = "analysisXml")
	@XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class)
	public ProcessAnalysis profile(
			@WebParam(name = "analysisXml") @XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class) ProcessAnalysis processAnalysis);
}
