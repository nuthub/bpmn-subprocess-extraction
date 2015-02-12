package edu.udo.cs.ls14.jf.bpmntransformation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

/**
 * Removes all Fragment pairs whose fragments do not contain at least two
 * activities and/or events.
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairFilterTrivial {
	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterTrivial.class);

	/**
	 * Remove trivial fragment pairs from given process matching with contained
	 * fragment matching.
	 * 
	 * @param matching
	 *            given ProcessMatching with contained fragment matching
	 * @return ProcessMatching with Fragment matching without trivial fragment
	 *         pairs
	 */
	public static FragmentMatching filter(FragmentMatching matching) {
		List<FragmentPair> removePairs = new ArrayList<FragmentPair>();
		for (FragmentPair pair : matching.getPairs()) {
			int size0 = FragmentUtil.getEventsAndActivites(pair.getA()).size();
			int size1 = FragmentUtil.getEventsAndActivites(pair.getB()).size();
			if (size0 != size1) {
				throw new NotImplementedException(
						"1:n and n:m correspondences not yet implemented.");
			}
			if (size0 < 2) {
				LOG.info(FragmentUtil.toString(pair.getA()) + " / "
						+ FragmentUtil.toString(pair.getB())
						+ " is filtered out, because of size=" + size0);
				removePairs.add(pair);
			}
		}
		matching.getPairs().removeAll(removePairs);
		return matching;
	}

}
