package edu.udo.cs.ls14.jf.fragmentmatching;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

public class FragmentPairJointerSequential {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairJointerSequential.class);

	public static FragmentMatching join(FragmentMatching matchingIn) {
		FragmentMatching matchingOut = BpmnMatchingFactory.eINSTANCE
				.createFragmentMatching();
		matchingOut.getPairs().addAll(matchingIn.getPairs());
		Pair<FragmentPair, FragmentPair> s;
		s = findSequence(matchingOut);
		while (s != null) {
			LOG.info("Found sequence: "
					+ FragmentUtil.toString(s.getValue0().getA()) + "+"
					+ FragmentUtil.toString(s.getValue0().getB()) + " / "
					+ FragmentUtil.toString(s.getValue1().getA()) + "+"
					+ FragmentUtil.toString(s.getValue1().getB()));
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
						return Pair.with(p, q);
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
		p.getA().setExit(q.getA().getExit());
		p.getB().setExit(q.getB().getExit());

		matching.getPairs().remove(q);
		return matching;
	}

}
