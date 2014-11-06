package edu.udo.cs.ls14.jf.processmatching;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair;

public class SequentialFCJointer {

	private static final Logger LOG = LoggerFactory
			.getLogger(SequentialFCJointer.class);

	public static FragmentMatching joinSequences(FragmentMatching matchingIn) {
		FragmentMatching matchingOut = BpmnAnalysisFactory.eINSTANCE
				.createFragmentMatching();
		Pair<FragmentPair, FragmentPair> s;
		s = findSequence(matchingOut);
		while (s != null) {
			LOG.debug("Found sequence: " + s);
			joinSequence(matchingOut, s);
			s = findSequence(matchingOut);
		}
		return matchingOut;
	}

	/**
	 * TODO: export to joiner class
	 * 
	 * @param matching
	 * @return
	 */
	private static Pair<FragmentPair, FragmentPair> findSequence(
			FragmentMatching matching) {
		for (FragmentPair p : matching.getPairs()) {
			for (FragmentPair q : matching.getPairs()) {
				if (p != q) {
					if (p.getA().getExit() == q.getA().getEntry()
							&& p.getB().getExit() == q.getB().getEntry()) {
						return Pair.with(q, p);
					}
				}
			}
		}
		return null;
	}

	private static FragmentMatching joinSequence(FragmentMatching matching,
			Pair<FragmentPair, FragmentPair> sequence) {
		FragmentPair p = sequence.getValue0();
		FragmentPair q = sequence.getValue1();
		// Create new unioned fragment
		Fragment union0 = BpmnAnalysisFactory.eINSTANCE.createFragment();
		union0.setEntry(p.getA().getEntry());
		union0.setExit(q.getA().getExit());

		Fragment union1 = BpmnAnalysisFactory.eINSTANCE.createFragment();
		union1.setEntry(p.getB().getEntry());
		union1.setExit(q.getB().getExit());

		FragmentPair pair = BpmnAnalysisFactory.eINSTANCE.createFragmentPair();
		pair.setA(union0);
		pair.setB(union1);
		matching.getPairs().add(pair);
		matching.getPairs().remove(p);
		matching.getPairs().remove(q);
		return matching;
	}

}
