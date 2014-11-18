package edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternodes;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairFilterNodes;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternodes.FragmentPairFilterNodesSEI")
public class FragmentPairFilterNodesImpl implements FragmentPairFilterNodesSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterNodesImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching matching) {
		LOG.info("Service input: " + matching);
		if (matching == null) {
			LOG.error("Input matching is null!");
			return null;
		}
		NodeMatching nMatching = matching.getNodeMatching();
		FragmentMatching fMatching = matching.getFragmentMatching();
		FragmentMatching filteredFMatching = FragmentPairFilterNodes.filter(
				fMatching, nMatching);
		matching.setFragmentMatching(filteredFMatching);
		return matching;
	}

}
