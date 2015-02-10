package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.ProcessMatchingFactorySEI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.ProcessMatchingFactorySEI")
public class ProcessMatchingFactoryImpl implements ProcessMatchingFactorySEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingFactoryImpl.class);

	@Override
	public ProcessMatching create(ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) {
		LOG.info("Service input analysis1: " + analysis1);
		LOG.info("Service input analysis2: " + analysis2);
		ProcessMatching matching = ProcessMatchingFactory.createEmptyMatching(
				analysis1, analysis2);
		return matching;

	}
}
