package edu.udo.cs.ls14.jf.processmatching;

import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

public class FragmentPairBuilder {

	public static FragmentMatching getAllPairs(ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) {
		FragmentMatching matching = BpmnMatchingFactory.eINSTANCE
				.createFragmentMatching();
		ProcessStructureTree pst1 = ((ProcessStructureTree) analysis1
				.getResults().get("pst"));
		ProcessStructureTree pst2 = ((ProcessStructureTree) analysis2
				.getResults().get("pst"));
		for (Fragment f1 : pst1.getFragments()) {
			for (Fragment f2 : pst2.getFragments()) {
				FragmentPair pair = BpmnMatchingFactory.eINSTANCE
						.createFragmentPair();
				pair.setA(f1);
				pair.setB(f2);
				matching.getPairs().add(pair);
			}
		}
		return matching;
	}

}
