package edu.udo.cs.ls14.jf.ws.bpmn.metrics;

import javax.jws.WebService;

@WebService
public class NumberOfNodes {
//	private static final Logger LOG = Logger.getLogger(NumberOfNodes.class
//			.getName());

	/**
	 * Expects xmi of definitions object
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	/** WebResult is important, otherwise, Activiti has no access to return value **/
	/*
	@WebMethod
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
	
	*/
}
