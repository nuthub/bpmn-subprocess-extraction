package edu.udo.cs.ls14.jf.bpmn.app;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * This interface has to be implemented by a sub process application.
 * 
 * @author Julian Flake
 *
 */
public interface SubProcessExtraction {

	/**
	 * Do initial stuff if needed.
	 */
	public abstract void init();

	/**
	 * Run the whole process of subprocess extraction.
	 * 
	 * @param definitions1
	 *            first BPMN Definitions object
	 * @param definitions2
	 *            second BPMN Definitions object
	 * @return ProcessTransformation object with results.
	 * @throws Exception
	 *             if any error occurs
	 */
	public abstract ProcessTransformation run(Definitions definitions1,
			Definitions definitions2) throws Exception;

}