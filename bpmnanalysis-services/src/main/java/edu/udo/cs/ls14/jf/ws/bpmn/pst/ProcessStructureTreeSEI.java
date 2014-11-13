package edu.udo.cs.ls14.jf.ws.bpmn.pst;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.ws.adapter.XmlDefinitionsAdapter;
import edu.udo.cs.ls14.jf.ws.adapter.XmlProcessStructureTreeAdapter;

@WebService
public interface ProcessStructureTreeSEI {

	@WebMethod
	@WebResult(name = "processStructureTreeXml")
	@XmlJavaTypeAdapter(XmlProcessStructureTreeAdapter.class)
	public ProcessStructureTree getPst(
			@WebParam(name = "definitionsXml") @XmlJavaTypeAdapter(XmlDefinitionsAdapter.class) Definitions definitions)
			throws Exception;
}
