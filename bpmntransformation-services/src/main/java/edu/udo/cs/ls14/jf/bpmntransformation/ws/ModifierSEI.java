package edu.udo.cs.ls14.jf.bpmntransformation.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmn.ws.adapter.XmlProcessTransformationAdapter;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * A Service that takes a ProcessTransformation object with contained process
 * matching and returns the ProcessTransformation object with modified input
 * models in results property.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface ModifierSEI {

	/**
	 * Replace Fragments by call activites in input models of a given
	 * ProcessTransformation object.
	 * 
	 * @param transformation
	 *            ProcessTransformation with contained ProcessMatching object
	 * 
	 * @return ProcessTransformation with modified input models in its results
	 *         property
	 */
	@WebMethod
	@WebResult(name = "transformationXml")
	@XmlJavaTypeAdapter(XmlProcessTransformationAdapter.class)
	public ProcessTransformation modify(
			@WebParam(name = "transformationXml") @XmlJavaTypeAdapter(XmlProcessTransformationAdapter.class) ProcessTransformation transformation);
}
