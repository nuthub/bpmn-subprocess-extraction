package edu.udo.cs.ls14.jf.bpmnmatching.nodematching;

import java.util.ArrayList;
import java.util.List;

import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;

/**
 * Removes all node pairs from a node matching, whose nodes are not equivalent.
 * 
 * @author Julian Flake
 *
 */
public class NodePairFilter {

	/**
	 * Remove all inequivalent pairs of nodes from given node matching.
	 * 
	 * @param matching
	 *            given node matching
	 * @return node matching without inequivalent pairs.
	 */
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
