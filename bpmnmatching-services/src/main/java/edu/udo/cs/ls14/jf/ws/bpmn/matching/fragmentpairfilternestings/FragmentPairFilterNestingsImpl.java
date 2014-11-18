package edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternestings;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairFilterNestings;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternestings.FragmentPairFilterNestingsSEI")
public class FragmentPairFilterNestingsImpl implements
		FragmentPairFilterNestingsSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNestingsImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching matching) {
		LOG.info("Service input: " + matching);
		if (matching == null) {
			LOG.error("Input matching is null!");
			return null;
		}
		FragmentMatching fMatching = matching.getFragmentMatching();
		FragmentMatching filteredFMatching = FragmentPairFilterNestings
				.filter(fMatching);
		matching.setFragmentMatching(filteredFMatching);
		return matching;
	}

}
