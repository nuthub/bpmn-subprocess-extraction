package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.CoordinateCalculator;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentCoordinateCalculatorSEI;

/**
 * Implementation of FragmentPairFilterBehaviorSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentCoordinateCalculatorSEI")
public class FragmentCoordinateCalculatorImpl implements
		FragmentCoordinateCalculatorSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentCoordinateCalculatorImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessMatching calculateCoordinates(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		try {
			for (FragmentPair pair : processMatching.getFragmentMatching()
					.getPairs()) {
				pair.getA().setCenter(
						CoordinateCalculator.getCenter(pair.getA()));
				pair.getB().setCenter(
						CoordinateCalculator.getCenter(pair.getB()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return processMatching;
	}
}
