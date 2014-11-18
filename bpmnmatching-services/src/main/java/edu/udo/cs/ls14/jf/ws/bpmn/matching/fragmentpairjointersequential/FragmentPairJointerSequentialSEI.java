package edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairjointersequential;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.ws.adapter.XmlProcessMatchingAdapter;

@WebService
public interface FragmentPairJointerSequentialSEI {

	@WebMethod
	@WebResult(name = "processMatchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching join(
			@WebParam(name = "processMatchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching matching);

}
