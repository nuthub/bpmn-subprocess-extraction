package edu.udo.cs.ls14.jf.processmatching;

import java.util.function.Predicate;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

public class FragmentPairRankerSize {

	public FragmentMatching rankFragments(FragmentMatching matching) {
		for(FragmentPair pair : matching.getPairs()) {
			Predicate<FlowElement> filter = e -> e instanceof Activity
					|| e instanceof Event || e instanceof Gateway;
			int sizeA = FragmentUtil.getFlowElements(pair.getA(), filter).size();
			int sizeB = FragmentUtil.getFlowElements(pair.getB(), filter).size();
			if (sizeA <= sizeB) {
				pair.setBetter(pair.getA());
			} else {
				pair.setBetter(pair.getA());
			}
		}
		return matching;
	}

}
