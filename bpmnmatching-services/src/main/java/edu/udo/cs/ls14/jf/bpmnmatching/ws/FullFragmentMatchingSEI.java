package edu.udo.cs.ls14.jf.bpmnmatching.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * A Service that takes a ProcessMatching object and adds a full fragment
 * matching (cartesian product).
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface FullFragmentMatchingSEI {

	/**
	 * 
	 * Create a full fragment matching and add it to given ProcessMatching object.
	 * 
	 * @param matching
	 *            given ProcessMatching with contained Definitions
	 * @return Process Matching with full fragment matching
	 */
	@WebMethod
	@WebResult(name = "matchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching createFullFragmentMatching(
			@WebParam(name = "matchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching matching);

}
