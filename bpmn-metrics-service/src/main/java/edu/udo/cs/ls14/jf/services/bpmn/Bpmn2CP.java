package edu.udo.cs.ls14.jf.services.bpmn;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.BpmnXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;

@WebService
public class Bpmn2CP {

	@WebMethod
	@WebResult(name = "conditionalProfile")
	public ConditionalProfile getConditionalProfile(
			@WebParam(name = "definitionsXmi") String definitionsXml) throws Exception {
		Definitions definitions = (Definitions) BpmnXmlConverter.xml2Bpmn(definitionsXml, Definitions.class);
		Process process = ProcessLoader
				.getProcessFromDefinitions(definitions);
		return ConditionalProfiler.generateProfile(process);
	}

}
