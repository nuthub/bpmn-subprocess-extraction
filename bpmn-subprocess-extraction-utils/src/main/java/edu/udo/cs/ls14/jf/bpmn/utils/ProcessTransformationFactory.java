package edu.udo.cs.ls14.jf.bpmn.utils;

import java.util.UUID;

import org.eclipse.emf.common.util.URI;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class ProcessTransformationFactory {

	public static ProcessTransformation createProcessTransformation(
			ProcessMatching processMatching) {
		ProcessTransformation transformation = BpmnTransformationFactory.eINSTANCE
				.createProcessTransformation();
		new BpmnTransformationResourceFactoryImpl()
				.createResource(
						URI.createFileURI(UUID.randomUUID().toString()
								+ ".bpmntransformation")).getContents()
				.add(transformation);
		transformation.setProcessMatching(processMatching);
		return transformation;
	}

}
