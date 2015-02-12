package edu.udo.cs.ls14.jf.bpmntransformation.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessMatchingAdapter;
import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessTransformationAdapter;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * A Service that creates a new ProcessTransformation object containing given
 * process matching object.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface ProcessTransformationFactorySEI {

	/**
	 * Create a new ProcessTransformationF object with added ProcessMatching
	 * object.
	 * 
	 * @param processMatching
	 *            ProcessMatching to add to new ProcessTransformation object
	 * @return new ProcessTransformation object containing given ProcessMatching
	 *         object
	 */
	@WebMethod
	@WebResult(name = "transformationXml")
	@XmlJavaTypeAdapter(XmlProcessTransformationAdapter.class)
	public ProcessTransformation create(
			@WebParam(name = "processMatchingXml") @XmlJavaTypeAdapter(XmlProcessMatchingAdapter.class) ProcessMatching processMatching);

}
