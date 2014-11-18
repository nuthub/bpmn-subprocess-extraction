package edu.udo.cs.ls14.jf.ws.bpmn.matching.nodepairfilter;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodePairFilter;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.matching.nodepairfilter.NodePairFilterSEI")
public class NodePairFilterImpl implements NodePairFilterSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(NodePairFilterImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		processMatching.setNodeMatching(NodePairFilter.filter(processMatching
				.getNodeMatching()));
		return processMatching;
	}
}
