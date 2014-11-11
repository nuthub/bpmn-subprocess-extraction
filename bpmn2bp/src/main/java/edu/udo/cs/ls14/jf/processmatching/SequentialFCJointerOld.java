package edu.udo.cs.ls14.jf.processmatching;

import java.util.Set;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.pst.FragmentOld;

public class SequentialFCJointerOld {

	private static final Logger LOG = LoggerFactory
			.getLogger(SequentialFCJointerOld.class);

	public static Set<Pair<FragmentOld, FragmentOld>> joinSequences(ProcessMatchingOld matching) {
		// TODO: do not clone
		ProcessMatchingOld unionedMatching = matching.clone();
		Set<Pair<FragmentOld, FragmentOld>> pairs = unionedMatching
				.getFragmentCorrespondencesOld();
		Pair<Pair<FragmentOld, FragmentOld>, Pair<FragmentOld, FragmentOld>> s;
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
	private static Pair<Pair<FragmentOld, FragmentOld>, Pair<FragmentOld, FragmentOld>> findSequence(
			Set<Pair<FragmentOld, FragmentOld>> pairs) {
		for (Pair<FragmentOld, FragmentOld> p : pairs) {
			for (Pair<FragmentOld, FragmentOld> q : pairs) {
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

	private static Set<Pair<FragmentOld, FragmentOld>> joinSequence(
			Set<Pair<FragmentOld, FragmentOld>> pairs,
			Pair<Pair<FragmentOld, FragmentOld>, Pair<FragmentOld, FragmentOld>> sequence) {
		Pair<FragmentOld, FragmentOld> p = sequence.getValue0();
		Pair<FragmentOld, FragmentOld> q = sequence.getValue1();
		// Create new unioned fragment
		FragmentOld union0 = new FragmentOld(p.getValue0().getResource(), p
				.getValue0().getProcess(), p.getValue0().getEntry(), q
				.getValue0().getExit());
		FragmentOld union1 = new FragmentOld(p.getValue1().getResource(), p
				.getValue1().getProcess(), p.getValue1().getEntry(), q
				.getValue1().getExit());
		pairs.add(Pair.with(union0, union1));
		pairs.remove(p);
		pairs.remove(q);
		return pairs;
	}

}
