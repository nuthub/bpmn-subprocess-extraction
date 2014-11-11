package edu.udo.cs.ls14.jf.services.bpmn;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmn.utils.BpmnXmlConverter;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysisResult;

@WebService
public class BpmnAnalysis {

	@WebMethod
	@WebResult(name = "analysis")
	public ProcessAnalysis analyze(
			@WebParam(name = "definitionsXml") String definitionsXml)
			throws Exception {

		ProcessAnalysis ana = BpmnAnalysisFactory.eINSTANCE.createProcessAnalysis();
		ana.setId("myID");
		ana.setDefinitions((Definitions) BpmnXmlConverter.xml2Bpmn(
				definitionsXml, Definitions.class));
		// cond prof
		ProcessAnalysisResult eCp = ana.getResults().get("conditionalProfile");
		System.out.println(eCp);
		return ana;

	}

}
