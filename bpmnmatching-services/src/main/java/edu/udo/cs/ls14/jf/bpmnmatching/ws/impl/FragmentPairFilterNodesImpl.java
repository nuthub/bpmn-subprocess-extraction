package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNodesSEI;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairFilterNodes;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNodesSEI")
public class FragmentPairFilterNodesImpl implements FragmentPairFilterNodesSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNodesImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		NodeMatching nMatching = processMatching.getNodeMatching();
		FragmentMatching fMatching = processMatching.getFragmentMatching();
		FragmentMatching filteredFMatching = FragmentPairFilterNodes.filter(
				fMatching, nMatching);
		processMatching.setFragmentMatching(filteredFMatching);
		return processMatching;
	}

}
