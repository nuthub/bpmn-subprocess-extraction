package edu.udo.cs.ls14.jf.processmatching;

import java.util.Set;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.pst.Fragment;

public class SequentialFCJointer {

	private static final Logger LOG = LoggerFactory
			.getLogger(SequentialFCJointer.class);

	public static Set<Pair<Fragment, Fragment>> joinSequences(ProcessMatching matching) {
		// TODO: do not clone
		ProcessMatching unionedMatching = matching.clone();
		Set<Pair<Fragment, Fragment>> pairs = unionedMatching
				.getFragmentCorrespondences();
		Pair<Pair<Fragment, Fragment>, Pair<Fragment, Fragment>> s;
		s = findSequence(pairs);
		while (s != null) {
			LOG.debug("Found sequence: " + s);
			joinSequence(pairs, s);
			s = findSequence(pairs);
		}
		return pairs;
	}

	/**
	 * TODO: export to joiner class
	 * 
	 * @param matching
	 * @return
	 */
	private static Pair<Pair<Fragment, Fragment>, Pair<Fragment, Fragment>> findSequence(
			Set<Pair<Fragment, Fragment>> pairs) {
		for (Pair<Fragment, Fragment> p : pairs) {
			for (Pair<Fragment, Fragment> q : pairs) {
				if (p != q) {
					if (p.getValue0().getExit() == q.getValue0().getEntry()
							&& p.getValue1().getExit() == q.getValue1()
									.getEntry()) {
						return Pair.with(p, q);
					}
				}
			}
		}
		return null;
	}

	private static Set<Pair<Fragment, Fragment>> joinSequence(
			Set<Pair<Fragment, Fragment>> pairs,
			Pair<Pair<Fragment, Fragment>, Pair<Fragment, Fragment>> sequence) {
		Pair<Fragment, Fragment> p = sequence.getValue0();
		Pair<Fragment, Fragment> q = sequence.getValue1();
		// Create new unioned fragment
		Fragment union0 = new Fragment(p.getValue0().getResource(), p
				.getValue0().getProcess(), p.getValue0().getEntry(), q
				.getValue0().getExit());
		Fragment union1 = new Fragment(p.getValue1().getResource(), p
				.getValue1().getProcess(), p.getValue1().getEntry(), q
				.getValue1().getExit());
		pairs.add(Pair.with(union0, union1));
		pairs.remove(p);
		pairs.remove(q);
		return pairs;
	}

}
