package edu.udo.cs.ls14.jf.transformation;

import java.util.function.Predicate;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

public class FragmentPairRankerSize {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FragmentPairRankerSize.class);

	public static FragmentMatching rankFragments(FragmentMatching matching) {
		LOGGER.info("I have to rank " + matching.getPairs().size() + " fragment pairs.");
		for (FragmentPair pair : matching.getPairs()) {
			Predicate<FlowElement> filter = e -> e instanceof Activity
					|| e instanceof Event || e instanceof Gateway;
			int sizeA = FragmentUtil.getFlowElements(pair.getA(), filter)
					.size();
			int sizeB = FragmentUtil.getFlowElements(pair.getB(), filter)
					.size();
			if (sizeA <= sizeB) {
				LOGGER.info("a is better Fragment in pair "
						+ FragmentUtil.toString(pair.getA()) + " / "
						+ FragmentUtil.toString(pair.getB()));
				pair.setBetter(pair.getA());
			} else {
				LOGGER.info("b is better Fragment in pair "
						+ FragmentUtil.toString(pair.getA()) + " / "
						+ FragmentUtil.toString(pair.getB()));
				pair.setBetter(pair.getB());
			}
		}
		return matching;
	}

}
