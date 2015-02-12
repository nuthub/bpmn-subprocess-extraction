package edu.udo.cs.ls14.jf.bpmn.app;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * This interface has to be implemented by a sub process application that has to
 * measure times of each phase.
 * 
 * @author Julian Flake
 *
 */
public interface ISubProcessExtractionProfiling {

	/**
	 * Do initial stuff if needed.
	 */
	public abstract void init();

	/**
	 * Run the whole process of subprocess extraction and profile phases.
	 * 
	 * @param definitions1
	 *            first BPMN Definitions object
	 * @param definitions2
	 *            second BPMN Definitions object
	 * @return ProcessTransformation object with results.
	 * @throws Exception
	 *             if any error occurs
	 */
	public abstract ProcessTransformation runAndProfile(
			Definitions definitions1, Definitions definitions2)
			throws Exception;

}