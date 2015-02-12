package edu.udo.cs.ls14.jf.bpmnanalysis.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessAnalysisAdapter;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

/**
 * A Service that takes a ProcessAnalysis object and adds the behavioral profile
 * to it.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface BehavioralProfilerSEI {

	/**
	 * Call the behavioral profiler.
	 * 
	 * @param processAnalysis
	 *            ProcessAnalysis object with contained Definitions.
	 * @return ProcessAnalysis object with added behavioral profile.
	 */
	@WebMethod
	@WebResult(name = "analysisXml")
	@XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class)
	public ProcessAnalysis profile(
			@WebParam(name = "analysisXml") @XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class) ProcessAnalysis processAnalysis);
}
