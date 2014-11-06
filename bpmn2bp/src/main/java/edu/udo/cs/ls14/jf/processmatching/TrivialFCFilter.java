package edu.udo.cs.ls14.jf.processmatching;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;

public class TrivialFCFilter {
	private static final Logger LOG = LoggerFactory
			.getLogger(TrivialFCFilter.class);

	public static FragmentMatching filter(FragmentMatching matchingIn) {
		FragmentMatching matchingOut = BpmnAnalysisFactory.eINSTANCE
				.createFragmentMatching();
		for (FragmentPair pair : matchingIn.getPairs()) {
			int size0 = FragmentUtil.getEventsAndActivites(pair.getA()).size();
			int size1 = FragmentUtil.getEventsAndActivites(pair.getB()).size();
			if (size0 != size1) {
				throw new NotImplementedException(
						"1:n and n:m correspondences not yet implemented.");
			}
			if (size0 >= 2) {
				LOG.info(pair + " is filtered out, because of size=" + size0);
				matchingOut.getPairs().add(pair);
			}
		}
		return matchingOut;
	}

}
