package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNestingsSEI;
import edu.udo.cs.ls14.jf.fragmentmatching.FragmentPairFilterNestings;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNestingsSEI")
public class FragmentPairFilterNestingsImpl implements
		FragmentPairFilterNestingsSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNestingsImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		FragmentMatching fMatching = processMatching.getFragmentMatching();
		FragmentMatching filteredFMatching = FragmentPairFilterNestings
				.filter(fMatching);
		processMatching.setFragmentMatching(filteredFMatching);
		return processMatching;
	}

}
