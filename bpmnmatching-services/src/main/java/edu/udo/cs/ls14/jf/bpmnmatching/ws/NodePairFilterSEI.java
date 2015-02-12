package edu.udo.cs.ls14.jf.bpmnmatching.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * A Service that takes a ProcessMatching with contained node matching and
 * filters all inequivalent node pairs from the node matching. to it.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface NodePairFilterSEI {

	/**
	 * Remove all inequivalent pairs of node from node matching contained in ProcessMatching object.
	 * 
	 * @param processMatching
	 *            given ProcessMatching with contained Definitions
	 * @return Process Matching with valid node matching
	 */
	@WebMethod
	@WebResult(name = "matchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching filter(
			@WebParam(name = "matchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching processMatching);

}
