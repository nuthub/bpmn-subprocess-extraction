package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.LabelGenerator;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentLabellerSEI;

/**
 * Implementation of FragmentPairFilterBehaviorSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentLabellerSEI")
public class FragmentLabellerImpl implements FragmentLabellerSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentLabellerImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessMatching generateLabels(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		for (FragmentPair pair : processMatching.getFragmentMatching()
				.getPairs()) {
			pair.getA().setLabel(LabelGenerator.getLabel(pair.getA()));
			pair.getB().setLabel(LabelGenerator.getLabel(pair.getB()));
		}
		return processMatching;
	}
}
