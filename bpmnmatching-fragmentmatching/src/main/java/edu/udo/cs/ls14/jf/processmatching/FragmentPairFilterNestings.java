package edu.udo.cs.ls14.jf.processmatching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;

public class FragmentPairFilterNestings {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNestings.class.getName());

	public static FragmentMatching filter(FragmentMatching matchingIn) {
		FragmentMatching matchingOut = BpmnMatchingFactory.eINSTANCE
				.createFragmentMatching();
		for (FragmentPair c1 : matchingIn.getPairs()) {
			boolean isContainedInOther = false;
			for (FragmentPair c2 : matchingIn.getPairs()) {
				if (!c1.getA().equals(c2.getA())
						&& !c1.getB().equals(c2.getB())
						&& (FragmentUtil.contains(c2.getA(), c1.getA())
						|| FragmentUtil.contains(c2.getB(), c1.getB()))) {
					LOG.info(c1
							+ " is filtered out, because it is contained in "
							+ c2);
					isContainedInOther = true;
				}
			}
			if (!isContainedInOther) {
				LOG.info(c1 + " is not contained in any other fragment");
				matchingOut.getPairs().add(c1);
			}
		}
		return matchingOut;
	}

}
