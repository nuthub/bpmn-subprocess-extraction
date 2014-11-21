package edu.udo.cs.ls14.jf.bpmn.utils;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

public class ProcessExtractionFactory {

	public static ProcessExtraction createProcessTransformation(
			ProcessMatching processMatching) {
		ProcessExtraction transformation = BpmnTransformationFactory.eINSTANCE.createProcessExtraction();
		transformation.setProcessMatching(processMatching);
		return transformation;		
	}

}
