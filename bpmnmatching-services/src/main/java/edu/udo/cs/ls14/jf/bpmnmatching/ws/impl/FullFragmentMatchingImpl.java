package edu.udo.cs.ls14.jf.bpmnmatching.ws.impl;

import javax.jws.WebService;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FullFragmentMatchingSEI;

/**
 * Implementation of FullFragmentMatchingSEI Service endpoint interface.
 * 
 * @author Julian Flake
 *
 */
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnmatching.ws.FullFragmentMatchingSEI")
public class FullFragmentMatchingImpl implements FullFragmentMatchingSEI {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProcessMatching createFullFragmentMatching(ProcessMatching matching) {
		matching.setFragmentMatching(ProcessMatchingFactory
				.getFullFragmentMatching(matching.getAnalysis1(),
						matching.getAnalysis2()));
		return matching;
	}

}
