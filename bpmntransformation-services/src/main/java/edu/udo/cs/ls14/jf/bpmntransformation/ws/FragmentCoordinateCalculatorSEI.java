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
 * matching and adds center points to all fragments in the fragment matchings.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface FragmentCoordinateCalculatorSEI {

	/**
	 * Calculate center points for all fragment pairs in fragment matching
	 * contained in given ProcessMatching object.
	 * 
	 * @param processMatching
	 *            given ProcessMatching object with contained fragment matching
	 * @return ProcessMatching with fragment matching where each fragment has
	 *         its center property set.
	 */
	@WebMethod
	@WebResult(name = "processMatchingXml")
	@XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class)
	public ProcessMatching calculateCoordinates(
			@WebParam(name = "processMatchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching processMatching);

}
