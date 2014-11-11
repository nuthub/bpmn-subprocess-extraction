package edu.udo.cs.ls14.jf.transformation;

import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.analysis.pst.FragmentOld;

@Deprecated
public class FragmentComparatorOld {
	public static FragmentOld getBetter(Pair<FragmentOld, FragmentOld> pair) {
		if (pair.getValue0().getContainedFlowNodes(n -> true).size() > pair
				.getValue1().getContainedFlowNodes(n -> true).size()) {
			return pair.getValue1();
		} else {
			return pair.getValue0();
		}
	}


}
