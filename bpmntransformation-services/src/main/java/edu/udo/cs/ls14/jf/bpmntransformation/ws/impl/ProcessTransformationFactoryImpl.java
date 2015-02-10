package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.ProcessTransformationFactorySEI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.ProcessTransformationFactorySEI")
public class ProcessTransformationFactoryImpl implements
		ProcessTransformationFactorySEI {

	@Override
	public ProcessTransformation create(ProcessMatching processMatching) {
		return ProcessTransformationFactory
				.createProcessTransformation(processMatching);
	}

}
