package edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilterbehavior;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairFilterBehavior;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilterbehavior.FragmentPairFilterBehaviorSEI")
public class FragmentPairFilterBehaviorImpl implements
		FragmentPairFilterBehaviorSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterBehaviorImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching matching) {
		LOG.info("Service input: " + matching);
		if (matching == null) {
			LOG.error("Input matching is null!");
			return null;
		}
		NodeMatching nMatching = matching.getNodeMatching();
		FragmentMatching fMatching = matching.getFragmentMatching();
		ProcessAnalysis analysis1 = matching.getAnalysis1();
		ProcessAnalysis analysis2 = matching.getAnalysis2();
		FragmentMatching filteredFMatching = FragmentPairFilterBehavior.filter(
				fMatching, nMatching, analysis1, analysis2);
		matching.setFragmentMatching(filteredFMatching);
		return matching;
	}

}
