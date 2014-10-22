package edu.udo.cs.ls14.jf.processmatching;

import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.pst.Fragment;

public class TrivialFCFilter {
	private static final Logger LOG = LoggerFactory
			.getLogger(TrivialFCFilter.class);

	public static Set<Pair<Fragment, Fragment>> filter(ProcessMatching matching) {
		// TODO: do not clone matching object
		ProcessMatching filtered = matching.clone();
		for (Pair<Fragment, Fragment> pair : matching
				.getFragmentCorrespondences()) {
			int size0 = getEventsAndActivites(pair.getValue0()).size();
			int size1 = getEventsAndActivites(pair.getValue1()).size();
			if (size0 != size1) {
				throw new NotImplementedException(
						"1:n and n:m correspondences not yet implemented.");
			}
			if (size0 < 2) {
				LOG.info(pair + " is filtered out, because of size=" + size0);
				filtered.getFragmentCorrespondences().remove(pair);
			}
		}
		return filtered.getFragmentCorrespondences();
	}

	// TODO: move to Fragment
	private static Set<FlowNode> getEventsAndActivites(Fragment fragment) {
		return fragment.getContainedFlowNodes(n -> n instanceof Activity
				|| n instanceof Event);
	}
}
