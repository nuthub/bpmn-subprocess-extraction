package edu.udo.cs.ls14.jf.bpmnanalysis.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlDefinitionsAdapter;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessAnalysisAdapter;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

/**
 * A Service that creates an ProcessAnalysis object and adds Definitions to it.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface ProcessAnalysisFactorySEI {

	/**
	 * 
	 * @param definitions
	 *            Definitions object with contained process model
	 * @return ProcessAnalysis object with contained Definitions object
	 */
	@WebMethod
	@WebResult(name = "processAnalysisXml")
	@XmlJavaTypeAdapter(XmlProcessAnalysisAdapter.class)
	public ProcessAnalysis createProcessAnalysis(
			@WebParam(name = "definitionsXml") @XmlJavaTypeAdapter(XmlDefinitionsAdapter.class) Definitions definitions);

}
