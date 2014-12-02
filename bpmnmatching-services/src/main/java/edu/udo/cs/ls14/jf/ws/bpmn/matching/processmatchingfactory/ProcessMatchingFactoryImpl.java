package edu.udo.cs.ls14.jf.ws.bpmn.matching.processmatchingfactory;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.matching.processmatchingfactory.ProcessMatchingFactorySEI")
public class ProcessMatchingFactoryImpl implements ProcessMatchingFactorySEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingFactoryImpl.class);

	@Override
	public ProcessMatching create(ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) {
		LOG.info("Service input analysis1: " + analysis1);
		LOG.info("Service input analysis2: " + analysis2);
		if (analysis1 == null) {
			LOG.error("analysis1 is null");
			return null;
		}
		if (analysis2 == null) {
			LOG.error("analysis2 is null");
			return null;
		}

		ProcessMatching matching = ProcessMatchingFactory.createEmptyMatching(
				analysis1, analysis2);
		matching.setNodeMatching(ProcessMatchingFactory.getFullNodeMatching(
				analysis1.getDefinitions(), analysis2.getDefinitions()));
		matching.setFragmentMatching(ProcessMatchingFactory
				.getFullFragmentMatching(analysis1, analysis2));
		return matching;

	}
}
