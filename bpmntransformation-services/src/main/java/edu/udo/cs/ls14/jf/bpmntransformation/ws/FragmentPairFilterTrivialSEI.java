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
 * matching and removes all trivial fragment pairs in the fragment matchings.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface FragmentPairFilterTrivialSEI {

	/**
	 * Removes all trivial fragment pairs from fragment matching contained in
	 * given process matching.
	 *
	 * @param processMatching
	 *            given process matching
	 * @return process matching without trivial fragment pairs in contained
	 *         fragment matching
	 */
	@WebMethod
	@WebResult(name = "processMatchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching filter(
			@WebParam(name = "processMatchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching processMatching);

}
