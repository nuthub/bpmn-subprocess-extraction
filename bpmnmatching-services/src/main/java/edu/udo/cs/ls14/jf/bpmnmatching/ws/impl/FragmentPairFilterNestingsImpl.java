package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching.FragmentPairFilterNestings;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNestingsSEI;

/**
 * Implementation of FragmentPairFilterNestingsSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNestingsSEI")
public class FragmentPairFilterNestingsImpl implements
		FragmentPairFilterNestingsSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNestingsImpl.class);

	/**
	 * {@inheritDoc}
	 */
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
