package edu.udo.cs.ls14.jf.processmatching;

import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.pst.Fragment;

public class ProcessMatcher {

	private final static Logger LOG = LoggerFactory
			.getLogger(ProcessMatcher.class);

	public static ProcessMatching match(ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) throws Exception {

		// create matching object
		ProcessMatching matching = new ProcessMatching();
		matching.setAnalysis1(analysis1);
		matching.setAnalysis2(analysis2);

		// determine and save node correspondences
		LOG.debug("Matching nodes");
		Set<Pair<FlowNode, FlowNode>> nodeMappings = NodeMatcher.getCorrespondences(
				analysis1.getResource(), analysis2.getResource());
		matching.setNodeCorrespondences(nodeMappings);
		LOG.debug(nodeMappings.size() + " Node Correspondences: ");
		nodeMappings.stream().forEach(
				m -> LOG.debug(" (" + m.getValue0().getName() + ","
						+ m.getValue1().getName() + ")"));

		// get Fragment Correspondences
		Set<Pair<Fragment, Fragment>> fragmentMapping = FragmentMatcher
				.getCorrespondences(analysis1, analysis2, nodeMappings);
		matching.setFragmentCorrespondences(fragmentMapping);

		LOG.info("Done matching " + analysis1.getProcess().getId() + " and "
				+ analysis2.getProcess().getId());
		return matching;
	}

}
