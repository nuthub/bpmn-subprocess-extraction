package edu.udo.cs.ls14.jf.bpmntransformation;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.CoordinateCalculator;
import edu.udo.cs.ls14.jf.bpmntransformation.Extractor;
import edu.udo.cs.ls14.jf.bpmntransformation.FragmentPairFilterTrivial;
import edu.udo.cs.ls14.jf.bpmntransformation.FragmentPairRanker;
import edu.udo.cs.ls14.jf.bpmntransformation.LabelGenerator;
import edu.udo.cs.ls14.jf.bpmntransformation.Modifier;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationFactory;

/**
 * This is also available as modeled process
 * 
 * TODO: Logging, if required
 * 
 * @author flake
 *
 */
public class ProcessTransformerImpl implements IProcessTransformer {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessTransformation transform(ProcessMatching pMatching)
			throws Exception {
		// 1. select non trivial matchings
		pMatching.setFragmentMatching(FragmentPairFilterTrivial
				.filter(pMatching.getFragmentMatching()));
		// 2. determine order in fragments
		pMatching.setFragmentMatching(FragmentPairRanker.rankByCFC(pMatching
				.getFragmentMatching()));
		// 3. compute coords
		for (FragmentPair pair : pMatching.getFragmentMatching().getPairs()) {
			pair.getA().setCenter(CoordinateCalculator.getCenter(pair.getA()));
			pair.getB().setCenter(CoordinateCalculator.getCenter(pair.getB()));
		}
		// 4. generate labels
		for (FragmentPair pair : pMatching.getFragmentMatching().getPairs()) {
			pair.getA().setLabel(LabelGenerator.getLabel(pair.getA()));
			pair.getB().setLabel(LabelGenerator.getLabel(pair.getB()));
		}
		// 5. create transformation object
		ProcessTransformation transformation = ProcessTransformationFactory
				.createProcessTransformation(pMatching);
		// 6. extract better fragments
		transformation = Extractor.extract(transformation);
		// 7. modify input models
		transformation = Modifier.modify(transformation);
		// 8. return
		return transformation;

	}

}
