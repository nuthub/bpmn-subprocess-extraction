package edu.udo.cs.ls14.jf.bpmntransformation.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.ws.adapter.XmlProcessTransformationAdapter;

@WebService
public interface ExtractorSEI {

	@WebMethod
	@WebResult(name = "transformationXml")
	@XmlJavaTypeAdapter(XmlProcessTransformationAdapter.class)
	public ProcessTransformation extract(
			@WebParam(name = "transformationXml") @XmlJavaTypeAdapter(XmlProcessTransformationAdapter.class) ProcessTransformation transformation);
}
