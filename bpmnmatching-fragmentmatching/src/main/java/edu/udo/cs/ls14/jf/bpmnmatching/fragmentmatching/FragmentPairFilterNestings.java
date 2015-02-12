package edu.udo.cs.ls14.jf.bpmnmatching.fragmentmatching;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

/**
 * Removes all FragmentPairs from a FragmentMatching that are included in
 * another pair of the fragment matching.
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairFilterNestings {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNestings.class.getName());

	/**
	 * Removes all fragment pairs that are nested in other fragment pairs of a
	 * given FragmentMatching.
	 * 
	 * @param matching
	 *            given fragment matching
	 * @return fragment matching without pairs that are nested in other fragment
	 *         pairs
	 */
	public static FragmentMatching filter(FragmentMatching matching) {
		List<FragmentPair> removePairs = new ArrayList<FragmentPair>();
		for (FragmentPair c1 : matching.getPairs()) {
			boolean isContainedInOther = false;
			for (FragmentPair c2 : matching.getPairs()) {
				if (!c1.getA().equals(c2.getA())
						&& !c1.getB().equals(c2.getB())
						&& (FragmentUtil.contains(c2.getA(), c1.getA()) || FragmentUtil
								.contains(c2.getB(), c1.getB()))) {
					LOG.info(FragmentUtil.toString(c1.getA()) + " / "
							+ FragmentUtil.toString(c1.getB())
							+ " is filtered out, because it is contained in "
							+ FragmentUtil.toString(c2.getA()) + " / "
							+ FragmentUtil.toString(c2.getB()));
					isContainedInOther = true;
					removePairs.add(c1);
				}
			}
			if (!isContainedInOther) {
				LOG.info(FragmentUtil.toString(c1.getA()) + " / "
						+ FragmentUtil.toString(c1.getB())
						+ " is not contained in any other fragment");
			}
		}
		matching.getPairs().removeAll(removePairs);
		return matching;
	}

}
