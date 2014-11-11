package edu.udo.cs.ls14.jf.transformation;

import java.util.function.Predicate;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

public class FragmentComparatorSize {

	public static Fragment getBetter(FragmentPair pair) {
		Predicate<FlowElement> filter = e -> e instanceof Activity
				|| e instanceof Event || e instanceof Gateway;
		int sizeA = FragmentUtil.getFlowElements(pair.getA(), filter).size();
		int sizeB = FragmentUtil.getFlowElements(pair.getB(), filter).size();
		if (sizeA <= sizeB) {
			return pair.getA();
		} else {
			return pair.getB();
		}
	}
}
