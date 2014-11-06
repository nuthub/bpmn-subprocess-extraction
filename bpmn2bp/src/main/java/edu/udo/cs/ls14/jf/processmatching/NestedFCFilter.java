package edu.udo.cs.ls14.jf.processmatching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;

public class NestedFCFilter {

	private static final Logger LOG = LoggerFactory
			.getLogger(NestedFCFilter.class.getName());

	public static FragmentMatching filter(FragmentMatching matchingIn) {
		FragmentMatching matchingOut = BpmnAnalysisFactory.eINSTANCE
				.createFragmentMatching();
		for (FragmentPair c1 : matchingIn.getPairs()) {
			boolean isContainedInOther = false;
			for (FragmentPair c2 : matchingIn.getPairs()) {
				if (!c1.getA().equals(c2.getB())
						&& !c1.getB().equals(c2.getB())
						&& (FragmentUtil.contains(c2.getA(), c1.getA()))
						|| FragmentUtil.contains(c2.getB(), c1.getB())) {
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
