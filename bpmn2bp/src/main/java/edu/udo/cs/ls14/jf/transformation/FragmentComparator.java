package edu.udo.cs.ls14.jf.transformation;

import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.analysis.pst.Fragment;

public class FragmentComparator {
	public static Fragment getBetter(Pair<Fragment, Fragment> pair) {
		if (pair.getValue0().getContainedFlowNodes(n -> true).size() > pair
				.getValue1().getContainedFlowNodes(n -> true).size()) {
			return pair.getValue1();
		} else {
			return pair.getValue0();
		}
	}


}
