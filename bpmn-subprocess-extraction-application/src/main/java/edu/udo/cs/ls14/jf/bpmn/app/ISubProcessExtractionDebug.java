package edu.udo.cs.ls14.jf.bpmn.app;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * This interface has to be implemented by a sub process application that writes
 * debug output.
 * 
 * @author Julian Flake
 *
 */
public interface ISubProcessExtractionDebug {

	/**
	 * Do initial stuff if needed.
	 */
	public abstract void init();

	/**
	 * Run the whole process of subprocess extraction and write debug files.
	 * 
	 * @param filename1
	 *            path to first BPMN file
	 * @param filename2
	 *            path to second BPMN file
	 * @param targetDir
	 *            targer directory of debug files
	 * @return ProcessTransformation object with results.
	 * @throws Exception
	 *             if any error occurs
	 */
	public abstract ProcessTransformation runDebug(String filename1,
			String filename2, String targetDir) throws Exception;

}