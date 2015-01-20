package edu.udo.cs.ls14.jf.transformation;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

/**
 * This is also available as modeled process
 * 
 * @author flake
 *
 */
public class ProcessExtractor {

	public static ProcessExtraction extract(ProcessMatching pMatching)
			throws Exception {
		// Do the extraction
		// 1. determine order in fragments
		pMatching.setFragmentMatching(FragmentPairRankerSize
				.rankFragments(pMatching.getFragmentMatching()));
		// 2. compute coords
		for (FragmentPair pair : pMatching.getFragmentMatching().getPairs()) {
			pair.getA().setCenter(CoordinateCalculator.getCenter(pair.getA()));
			pair.getB().setCenter(CoordinateCalculator.getCenter(pair.getB()));
		}
		// 3. generate labels
		for (FragmentPair pair : pMatching.getFragmentMatching().getPairs()) {
			pair.getA().setLabel(LabelGenerator.getLabel(pair.getA()));
			pair.getB().setLabel(LabelGenerator.getLabel(pair.getB()));
		}
		// 4. crop better fragment and replace matched fragments
		return FragmentExtractor.transform(pMatching);
	}

}
