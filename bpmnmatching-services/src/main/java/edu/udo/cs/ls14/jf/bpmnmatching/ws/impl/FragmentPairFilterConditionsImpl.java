package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterConditionsSEI;
import edu.udo.cs.ls14.jf.fragmentmatching.FragmentPairFilterConditions;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterConditionsSEI")
public class FragmentPairFilterConditionsImpl implements
		FragmentPairFilterConditionsSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentPairFilterConditionsImpl.class);

	@Override
	public ProcessMatching filter(ProcessMatching processMatching) {
		LOG.info("Service input: " + processMatching);
		NodeMatching nMatching = processMatching.getNodeMatching();
		FragmentMatching fMatching = processMatching.getFragmentMatching();
		ProcessAnalysis analysis1 = processMatching.getAnalysis1();
		ProcessAnalysis analysis2 = processMatching.getAnalysis2();
		FragmentMatching filteredFMatching = FragmentPairFilterConditions.filter(
				fMatching, nMatching, analysis1, analysis2);
		processMatching.setFragmentMatching(filteredFMatching);
		return processMatching;
	}

}
