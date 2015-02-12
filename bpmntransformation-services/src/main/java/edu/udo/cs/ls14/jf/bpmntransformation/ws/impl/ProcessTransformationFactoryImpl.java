package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.ProcessTransformationFactorySEI;

/**
 * Implementation of FragmentPairFilterBehaviorSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.ProcessTransformationFactorySEI")
public class ProcessTransformationFactoryImpl implements
		ProcessTransformationFactorySEI {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessTransformation create(ProcessMatching processMatching) {
		return ProcessTransformationFactory
				.createProcessTransformation(processMatching);
	}

}
