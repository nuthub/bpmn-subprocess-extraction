package edu.udo.cs.ls14.jf.processmatching;

import org.eclipse.emf.ecore.resource.Resource;

public class ProcessMatchingChain {

	public static ProcessMatching getCandidates(Resource res1, Resource res2) throws Exception {

		// Find all matches
		System.out.println("Find all matches.");
		ProcessMatching allMatching = ProcessMatcher.match(res1, res2);
		// Filter out nested fragment matches
		System.out.println("Filter out nested matches.");
		ProcessMatching filteredMatching = NestedFCFilter.filter(allMatching);
		// build sequence unions
		System.out.println("Union matches in sequence.");
		ProcessMatching unionedMatching = SequentialFCJointer
				.joinSequences(filteredMatching);
		// filter out trivial fragments (|nodes| < 2)
		System.out.println("Filter out trivial matches.");
		ProcessMatching candidates = TrivialFCFilter
				.filter(unionedMatching);
		return candidates;

	}
	
}
