package edu.udo.cs.ls14.jf.services.bpmn;

import javax.jws.WebMethod;
import javax.jws.WebParam;
//import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;

//@WebService
public class Bpmn2Ptnet {

	@WebMethod
	public Object translate(
			@WebParam(name = "definitionsXmi") String definitionsXml) {
		try {
//			Definitions definitions = (Definitions) BpmnXmlConverter.xml2Bpmn(definitionsXml, Definitions.class);
//			Bpmn2PtnetConverter conv = new Bpmn2PtnetConverter();
//			Process process = ProcessLoader
//					.getProcessFromDefinitions(definitions);
//			PetriNetHLAPI net = conv.convertToPetriNet(process);
//			PetriNetHLAPI net = new PetriNetHLAPI("id", PNTypeHLAPI.PTNET);
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
