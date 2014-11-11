package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.pst.FragmentOld;

public class NestedFCFilterOld {

	private static final Logger LOG = LoggerFactory
			.getLogger(NestedFCFilterOld.class.getName());

	public static Set<Pair<FragmentOld, FragmentOld>> filter(ProcessMatchingOld matching) {
		Set<Pair<FragmentOld, FragmentOld>> filteredCorrespondences = new HashSet<Pair<FragmentOld, FragmentOld>>();
		for (Pair<FragmentOld, FragmentOld> c1 : matching
				.getFragmentCorrespondencesOld()) {
			boolean isContainedInOther = false;
			for (Pair<FragmentOld, FragmentOld> c2 : matching
					.getFragmentCorrespondencesOld()) {
				if (!c1.getValue0().equals(c2.getValue0())
						&& !c1.getValue1().equals(c2.getValue1())
						&& (c2.getValue0().contains(c1.getValue0()) || c2
								.getValue1().contains(c1.getValue1()))) {
					LOG.info(c1
							+ " is filtered out, because it is contained in "
							+ c2);
					isContainedInOther = true;
				}
			}
			if (!isContainedInOther) {
				LOG.info(c1 + " is not contained in any other fragment");
				filteredCorrespondences.add(c1);
			}
		}
		return filteredCorrespondences;
	}

}
