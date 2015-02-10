package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FullNodeMatchingSEI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.FullNodeMatchingSEI")
public class FullNodeMatchingImpl implements FullNodeMatchingSEI {

	@Override
	public ProcessMatching createFullNodeMatching(ProcessMatching matching) {
		matching.setNodeMatching(ProcessMatchingFactory.getFullNodeMatching(
				matching.getAnalysis1().getDefinitions(), matching
						.getAnalysis2().getDefinitions()));
		return matching;
	}

}
