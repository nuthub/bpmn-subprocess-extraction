package edu.udo.cs.ls14.jf.bpmnmatching;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * Interface of matching phase.
 * 
 * TODO: use EMF version
 * 
 * @author Julian Flake
 *
 */
public interface IProcessMatcher {

	/**
	 * Takes two analysis objects with contained Definitions and returns a valid
	 * minimal fragment matching.
	 * 
	 * @param analysis1
	 *            first analysis object with contained Definitions object
	 * @param analysis2
	 *            first analysis object with contained Definitions object
	 * @return valid minimal fragment matching
	 * @throws Exception
	 *             if any error occurs
	 */
	ProcessMatching match(ProcessAnalysis analysis1, ProcessAnalysis analysis2)
			throws Exception;
}