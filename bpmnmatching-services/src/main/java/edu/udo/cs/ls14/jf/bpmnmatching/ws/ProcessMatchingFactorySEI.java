package edu.udo.cs.ls14.jf.bpmnmatching.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.ws.adapter.XmlProcessAnalysisAdapter;
import edu.udo.cs.ls14.jf.ws.adapter.XmlProcessMatchingAdapter;

@WebService
public interface ProcessMatchingFactorySEI {

	@WebMethod
	@WebResult(name = "matchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching create(
			@WebParam(name = "analysis1Xml") @XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class) ProcessAnalysis analysis1,
			@WebParam(name = "analysis2Xml") @XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class) ProcessAnalysis analysis2);

}
