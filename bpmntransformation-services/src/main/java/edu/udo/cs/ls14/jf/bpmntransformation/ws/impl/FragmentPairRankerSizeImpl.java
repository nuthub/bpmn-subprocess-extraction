package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.FragmentPairRanker;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentPairRankerSizeSEI;

/**
 * Implementation of FragmentPairFilterBehaviorSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentPairRankerSizeSEI")
public class FragmentPairRankerSizeImpl implements FragmentPairRankerSizeSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairRankerSizeImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessMatching rank(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		FragmentMatching fMatching = processMatching.getFragmentMatching();
		FragmentMatching rankedFMatching = FragmentPairRanker
				.rankBySize(fMatching);
		processMatching.setFragmentMatching(rankedFMatching);
		return processMatching;
	}

}
