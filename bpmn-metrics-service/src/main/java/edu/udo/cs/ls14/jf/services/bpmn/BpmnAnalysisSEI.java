package edu.udo.cs.ls14.jf.services.bpmn;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.services.adapter.XmlDefinitionsAdapter;
import edu.udo.cs.ls14.jf.services.adapter.XmlProcessAnalysisAdapter;

@WebService
public interface BpmnAnalysisSEI {

	/**
	 * Production Ready!!!
	 * 
	 * @param definitions
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	@WebResult(name = "processAnalysisXml")
	@XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class)
	public ProcessAnalysis analyze(
			@WebParam(name = "definitionsXml") @XmlJavaTypeAdapter(XmlDefinitionsAdapter.class) Definitions definition)
			throws Exception;

}