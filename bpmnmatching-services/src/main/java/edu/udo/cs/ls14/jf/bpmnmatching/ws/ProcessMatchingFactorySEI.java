package edu.udo.cs.ls14.jf.bpmnmatching.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessAnalysisAdapter;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * A Service that takes tow ProcessAnalysis objects creates a new
 * ProcessMatching object that contains the two analysis objects. to it.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface ProcessMatchingFactorySEI {

	/**
	 * Create a new ProcessMatching object that contains two given analysis
	 * objects.
	 * 
	 * @param analysis1
	 *            first ProcessAnalysis with contained Definitions
	 * @param analysis2
	 *            second ProcessAnalysis with contained Definitions
	 * @return new Process Matching object with contained Analysis objects
	 */
	@WebMethod
	@WebResult(name = "matchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching create(
			@WebParam(name = "analysis1Xml") @XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class) ProcessAnalysis analysis1,
			@WebParam(name = "analysis2Xml") @XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class) ProcessAnalysis analysis2);

}
