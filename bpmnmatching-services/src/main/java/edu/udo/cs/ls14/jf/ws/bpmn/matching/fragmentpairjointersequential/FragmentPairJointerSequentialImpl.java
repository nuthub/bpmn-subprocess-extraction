package edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairjointersequential;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairJointerSequential;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairjointersequential.FragmentPairJointerSequentialSEI")
public class FragmentPairJointerSequentialImpl implements
		FragmentPairJointerSequentialSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairJointerSequentialImpl.class);

	@Override
	public ProcessMatching join(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		if (processMatching == null) {
			LOG.error("Input matching is null!");
			return null;
		}
		FragmentMatching fMatching = processMatching.getFragmentMatching();
		FragmentMatching filteredFMatching = FragmentPairJointerSequential
				.join(fMatching);
		processMatching.setFragmentMatching(filteredFMatching);
		return processMatching;
	}

}
