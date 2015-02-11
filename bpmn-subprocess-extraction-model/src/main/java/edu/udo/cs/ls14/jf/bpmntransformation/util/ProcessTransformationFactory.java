package edu.udo.cs.ls14.jf.bpmntransformation.util;

import java.util.UUID;

import org.eclipse.emf.common.util.URI;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * Factory method for ProcessTransformation objects.
 * 
 * @author Julian Flake
 *
 */
public class ProcessTransformationFactory {

	/**
	 * Creates a new ProcessTransformation object with ProcessMatching object
	 * added.
	 * 
	 * @param processMatching
	 *            ProcessMatching object to add
	 * @return non empty ProcessTransformation object
	 */
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
