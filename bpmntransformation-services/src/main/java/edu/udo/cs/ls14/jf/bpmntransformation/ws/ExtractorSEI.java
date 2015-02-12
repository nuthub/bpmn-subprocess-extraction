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
 * matching and returns the ProcessTransformation object with new process models
 * in results property.
 * 
 * @author Julian Flake
 *
 */
@WebService
public interface ExtractorSEI {
	/**
	 * Create a new process model for every fragment pair in fragment matching
	 * that is contained in the ProcessMatching contained in
	 * ProcessTransformation object. New models are added to results property of
	 * ProcessTransformation object.
	 * 
	 * @param transformation
	 *            given ProcessTransformation object
	 * @return Process Transformation object with added results
	 */
	@WebMethod
	@WebResult(name = "transformationXml")
	@XmlJavaTypeAdapter(XmlProcessTransformationAdapter.class)
	public ProcessTransformation extract(
			@WebParam(name = "transformationXml") @XmlJavaTypeAdapter(XmlProcessTransformationAdapter.class) ProcessTransformation transformation);
}
