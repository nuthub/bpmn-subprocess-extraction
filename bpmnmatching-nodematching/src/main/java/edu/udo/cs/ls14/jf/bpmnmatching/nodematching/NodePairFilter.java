package edu.udo.cs.ls14.jf.bpmnmatching.nodematching;

import java.util.ArrayList;
import java.util.List;

import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;

public class NodePairFilter {

	// private static final Logger LOG = LoggerFactory
	// .getLogger(NodePairFilter.class);

	public static NodeMatching filter(NodeMatching matching) {
		NodeComparator comp = new NodeComparator();
		List<NodePair> removePairs = new ArrayList<NodePair>();
		for (NodePair pair : matching.getPairs()) {
			if (!comp.isEquivalent(pair.getA(), pair.getB())) {
				removePairs.add(pair);
			}
		}
		matching.getPairs().removeAll(removePairs);
		return matching;
	}
}
