package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentLabellerSEI;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentLabellerSEI")
public class FragmentLabellerImpl implements FragmentLabellerSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentLabellerImpl.class);

	@Override
	public ProcessMatching generateLabels(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		try {
			for (FragmentPair pair : processMatching.getFragmentMatching()
					.getPairs()) {
				pair.getA().setLabel(LabelGenerator.getLabel(pair.getA()));
				pair.getB().setLabel(LabelGenerator.getLabel(pair.getB()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return processMatching;
	}
}
