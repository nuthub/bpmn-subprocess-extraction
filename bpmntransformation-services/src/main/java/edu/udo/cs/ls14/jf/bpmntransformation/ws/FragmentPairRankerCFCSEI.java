package edu.udo.cs.ls14.jf.bpmntransformation.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * A Service that takes a ProcessMatching object with contained fragment
 * matching and ranks all fragment pairs by their size (number of contained
 * Events and Activities).
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface FragmentPairRankerCFCSEI {

	/**
	 * Rank Fragments of each pair in fragment matching contained in given
	 * process matching by control flow complexity.
	 * 
	 * @param processMatching
	 *            given process matching with contained fragment matching
	 * @return process matching with fragment matching where each pair is ranked
	 *         by CFC
	 */
	@WebMethod
	@WebResult(name = "processMatchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching rank(
			@WebParam(name = "processMatchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching processMatching);

}
