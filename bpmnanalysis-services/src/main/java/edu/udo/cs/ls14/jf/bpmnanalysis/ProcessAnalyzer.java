package edu.udo.cs.ls14.jf.bpmnanalysis;

import java.util.List;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

/**
 * ProcessAnalyzer interface. Turns a Definitions object into a ProcessAnalysis
 * object.
 * 
 * @author Julian Flake
 *
 */
public interface ProcessAnalyzer {

	/**
	 * Just analyze the Definitions object.
	 * 
	 * @param definitions
	 *            the Definitions object
	 * @return ProcessAnalysis object containing analysis results.
	 * @throws Exception
	 *             if an error occurs
	 */
	ProcessAnalysis analyze(Definitions definitions) throws Exception;

	/**
	 * Just analyze the Definitions object and write debug files.
	 * 
	 * @param definitions
	 *            the Definitions object
	 * @param pathname
	 *            directory where the definitions object resides
	 * @param basename
	 *            basename of analyzed definitions
	 * @param debugFilesDir
	 *            directory where the debug files shall be written
	 * @param nodes
	 *            Names of used nodes in process model
	 * 
	 * @return ProcessAnalysis object containing analysis results.
	 * @throws Exception
	 *             if an error occurs
	 */
	ProcessAnalysis analyzeAndDebug(Definitions definitions, String pathname,
			String basename, String debugFilesDir, List<String> nodes)
			throws Exception;
}