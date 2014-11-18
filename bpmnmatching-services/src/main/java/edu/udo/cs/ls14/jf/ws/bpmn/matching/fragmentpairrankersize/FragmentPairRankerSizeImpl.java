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
	public ProcessMatching setFragmentPairRankings(ProcessMatching matching) {
		LOG.info("Service input: " + matching);
		if (matching == null) {
			LOG.error("Input matching is null!");
			return null;
		}
		FragmentMatching fMatching = matching.getFragmentMatching();
		FragmentMatching rankedFMatching = FragmentPairRankerSize
				.rankFragments(fMatching);
		matching.setFragmentMatching(rankedFMatching);
		return matching;
	}

}
