package edu.udo.cs.ls14.jf.processmatching;

import org.eclipse.emf.ecore.resource.Resource;

public class ProcessMatchingChain {

	public static ProcessMatching getCandidates(Resource res1, Resource res2)
			throws Exception {

		
		// Find all matches
		System.out.println("Find all matches.");
		ProcessMatching allMatching = ProcessMatcher.match(res1, res2);
		System.out.println(allMatching.getFragmentCorrespondences().size()
				+ " fragment correspondences.");
		
		// Filter out pairs with non-matching conditions
		System.out
				.println("Filter out matches with inequivalent conditional profiles");
		ProcessMatching conditionEquivalentMatching = InequivalentConditionsFCFilter
				.filter(allMatching);
		System.out.println(conditionEquivalentMatching
				.getFragmentCorrespondences().size()
				+ " fragment correspondences.");
		
		// Filter out nested fragment matches
		System.out.println("Filter out nested matches.");
		ProcessMatching filteredMatching = NestedFCFilter
				.filter(conditionEquivalentMatching);
		System.out.println(filteredMatching
				.getFragmentCorrespondences().size()
				+ " fragment correspondences.");
		
		// build sequence unions
		System.out.println("Union matches in sequence.");
		ProcessMatching unionedMatching = SequentialFCJointer
				.joinSequences(filteredMatching);
		System.out.println(unionedMatching
				.getFragmentCorrespondences().size()
				+ " fragment correspondences.");
		
		// filter out trivial fragments (|nodes| < 2)
		System.out.println("Filter out trivial matches.");
		ProcessMatching candidates = TrivialFCFilter.filter(unionedMatching);
		System.out.println(candidates
				.getFragmentCorrespondences().size()
				+ " fragment correspondences.");
		
		return candidates;

	}

}
