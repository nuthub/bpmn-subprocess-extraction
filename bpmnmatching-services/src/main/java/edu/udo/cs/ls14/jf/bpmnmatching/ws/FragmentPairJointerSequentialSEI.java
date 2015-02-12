package edu.udo.cs.ls14.jf.bpmnmatching.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * A Service that takes a ProcessMatching object with contained fragment
 * matching and joins all sequences of fragment pairs that are contained in the
 * fragment matching.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface FragmentPairJointerSequentialSEI {

	/**
	 * Join sequences of matched fragment pairs.F
	 * 
	 * @param processMatching
	 *            given ProcessMatching with contained fragment matching
	 * @return ProcessMatching with contained fragment matching that potentially
	 *         contains joined and non canonical fragment pairs
	 */
	@WebMethod
	@WebResult(name = "matchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching join(
			@WebParam(name = "matchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching processMatching);

}
