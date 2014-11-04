package edu.udo.cs.ls14.jf.services.bpmn;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfile;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.services.adapter.ConditionalProfileAdapter;

@WebService
public class Bpmn2CP {

	@WebMethod
	@WebResult(name = "conditionalProfile")
	@XmlJavaTypeAdapter(ConditionalProfileAdapter.class)
	public ConditionalProfile getConditionalProfile(
			@WebParam(name = "definitionsXmi") String definitionsXml) throws Exception {
		EObject eObject = EObjectXmlConverter.xml2EObject(definitionsXml);
		if (!(eObject instanceof Definitions)) {
			throw new Exception("definitionsXmi does not contain "
					+ Definitions.class.getName());
		}
		Definitions definitions = (Definitions) eObject;
		Process process = ProcessLoader
				.getProcessFromDefinitions(definitions);
		return ConditionalProfiler.generateProfile(process);
	}

}
