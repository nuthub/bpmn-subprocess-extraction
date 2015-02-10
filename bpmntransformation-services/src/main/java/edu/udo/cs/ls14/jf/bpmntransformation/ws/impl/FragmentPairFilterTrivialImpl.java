package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentPairFilterTrivialSEI;
import edu.udo.cs.ls14.jf.transformation.FragmentPairFilterTrivial;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentPairFilterTrivialSEI")
public class FragmentPairFilterTrivialImpl implements
		FragmentPairFilterTrivialSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterTrivialImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		processMatching.setFragmentMatching(FragmentPairFilterTrivial
				.filter(processMatching.getFragmentMatching()));
		return processMatching;
	}
}
