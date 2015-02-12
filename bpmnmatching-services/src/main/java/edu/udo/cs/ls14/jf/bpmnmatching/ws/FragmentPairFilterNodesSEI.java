package edu.udo.cs.ls14.jf.bpmnmatching.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * A Service that takes a ProcessMatching with contained fragment matching and
 * removes all node inequivalent fragment pairs.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface FragmentPairFilterNodesSEI {

	/**
	 * Remove node inequivalent fragment pairs from fragment matching.
	 * 
	 * @param processMatching
	 *            given ProcessMatching with contained fragment matching
	 * @return ProcessMatching with contained filtered fragment matching
	 */
	@WebMethod
	@WebResult(name = "matchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching filter(
			@WebParam(name = "matchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching processMatching);

}
