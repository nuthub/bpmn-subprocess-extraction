package edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairrankersize;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairRankerSize;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairrankersize.FragmentPairRankerSizeSEI")
public class FragmentPairRankerSizeImpl implements FragmentPairRankerSizeSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairRankerSizeImpl.class);

	@Override
	public ProcessMatching setFragmentPairRankings(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		if (processMatching == null) {
			LOG.error("Input matching is null!");
			return null;
		}
		FragmentMatching fMatching = processMatching.getFragmentMatching();
		FragmentMatching rankedFMatching = FragmentPairRankerSize
				.rankFragments(fMatching);
		processMatching.setFragmentMatching(rankedFMatching);
		return processMatching;
	}

}
