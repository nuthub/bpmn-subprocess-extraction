package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching.FragmentPairFilterNodes;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNodesSEI;

/**
 * Implementation of FragmentPairFilterNodesSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNodesSEI")
public class FragmentPairFilterNodesImpl implements FragmentPairFilterNodesSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNodesImpl.class);

	/**
	 * {@inheritDoc}
	 */
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
