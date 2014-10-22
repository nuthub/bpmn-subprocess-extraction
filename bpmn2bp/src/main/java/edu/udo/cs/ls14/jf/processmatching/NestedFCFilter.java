package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.pst.Fragment;

public class NestedFCFilter {

	private static final Logger LOG = LoggerFactory
			.getLogger(NestedFCFilter.class.getName());

	public static Set<Pair<Fragment, Fragment>> filter(ProcessMatching matching) {
		Set<Pair<Fragment, Fragment>> filteredCorrespondences = new HashSet<Pair<Fragment, Fragment>>();
		for (Pair<Fragment, Fragment> c1 : matching
				.getFragmentCorrespondences()) {
			boolean isContainedInOther = false;
			for (Pair<Fragment, Fragment> c2 : matching
					.getFragmentCorrespondences()) {
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
