package edu.udo.cs.ls14.jf.bpmntransformation;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * Runs all steps of transformation phase.
 * 
 * TODO: use EMF version
 * 
 * @author Julian Flake
 *
 */
public interface IProcessTransformer {

	/**
	 * Do the transformation.
	 * 
	 * @param matching
	 *            given ProcessMatching
	 * @return resulting ProcessTransformation object
	 * @throws Exception
	 *             if an exception occurs during transformation
	 */
	ProcessTransformation transform(ProcessMatching matching) throws Exception;

}