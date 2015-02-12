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
 * adds a full node matching (cartesian product).
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface FullNodeMatchingSEI {

	/**
	 * Create a full node matching and add it to given ProcessMatching object.
	 * 
	 * @param matching
	 *            given ProcessMatching with contained Definitions
	 * @return Process matching with full node matching
	 */
	@WebMethod
	@WebResult(name = "matchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching createFullNodeMatching(
			@WebParam(name = "matchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching matching);

}
