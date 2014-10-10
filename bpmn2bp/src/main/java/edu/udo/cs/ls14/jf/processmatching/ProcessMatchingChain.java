package edu.udo.cs.ls14.jf.processmatching;

import java.net.URL;

import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessMatchingChain {

	public static ProcessMatching getCandidates(URL url1, URL url2) throws Exception {
		Process process1 = ProcessLoader.loadFirstProcessFromResource(url1);
		Process process2 = ProcessLoader.loadFirstProcessFromResource(url2);

		// Find all matches
		System.out.println("Find all matches.");
		ProcessMatching allMatching = ProcessMatcher.match(process1, process2);
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
