package edu.udo.cs.ls14.jf.services.bpmn;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.bpmn.utils.BpmnXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;

@WebService
public class NumberOfNodes {

	private static final Logger LOG = Logger.getLogger(NumberOfNodes.class
			.getName());

	/**
	 * Expects xmi of definitions object
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	@WebMethod
	/** Important, otherwise, Activiti has no access to return value **/
	@WebResult(name = "numOfActivities")
	public int getNumOfActivities(
			@WebParam(name = "definitionsXmi") String definitionsXml)
			throws Exception {
		return countFlowElements(definitionsXml, e -> e instanceof Activity);
	}

	@WebMethod
	@WebResult(name = "numOfActivitiesAndEvents")
	public int getNumOfActivitiesAndEvents(
			@WebParam(name = "definitionsXmi") String definitionsXml)
			throws Exception {
		return countFlowElements(definitionsXml, e -> e instanceof Activity
				|| e instanceof Event);
	}

	@WebMethod
	@WebResult(name = "numOfGateways")
	public int getNumOfGateways(
			@WebParam(name = "definitionsXmi") String definitionsXml)
			throws Exception {
		return countFlowElements(definitionsXml, e -> e instanceof Gateway);
	}

	private int countFlowElements(String definitionsXml,
			Predicate<FlowElement> filter) throws Exception {
		if (definitionsXml == null) {
			LOG.warning("Received null input");
			return -1;
		}
		LOG.info("Received definitionsXmi of length " + definitionsXml.length());
		LOG.fine("Received: " + definitionsXml);
		Definitions definitions = BpmnXmlConverter.xml2Bpmn(definitionsXml);
		Process p = ProcessUtil.getProcessFromDefinitions(definitions);
		int size = p.getFlowElements().stream().filter(filter).mapToInt(a -> 1)
				.sum();
		LOG.info("Size is " + size);
		return size;
	}
}
