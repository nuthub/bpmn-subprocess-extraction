package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzerOld;
import edu.udo.cs.ls14.jf.analysis.pst.FragmentOld;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.Analysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;

public class ProcessMatchingChain {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingChain.class);

	@Deprecated
	public static ProcessMatching getCandidatesOld(Resource res1, Resource res2)
			throws Exception {
		Definitions definitions1 = ProcessLoader
				.getDefinitionsFromResource(res1);
		Definitions definitions2 = ProcessLoader
				.getDefinitionsFromResource(res2);
		Process process1 = ProcessLoader
				.getProcessFromDefinitions(definitions1);
		Process process2 = ProcessLoader
				.getProcessFromDefinitions(definitions2);

		// create matching object
		ProcessMatching matching = new ProcessMatching();

		// analyze process1
		LOG.debug("Analyze process 1: " + process1.getName());
		ProcessAnalysis analysis1Old = ProcessAnalyzerOld.analyze(res1);
		matching.setAnalysis1(analysis1Old);

		// analyze process2
		LOG.debug("Analyze process 2: " + process2.getName());
		ProcessAnalysis analysis2Old = ProcessAnalyzerOld.analyze(res2);
		matching.setAnalysis2(analysis2Old);

		// determine node correspondences
		LOG.debug("Matching nodes");
		Set<Pair<FlowNode, FlowNode>> nodeMappings = NodeMatcher
				.getCorrespondencesOld(process1, process2);
		matching.setNodeCorrespondences(nodeMappings);
		LOG.debug(nodeMappings.size() + " node correspondences: ");

		// get all possible Fragment Correspondences
		LOG.info("create all possible fragment correspondences.");
		Set<Pair<FragmentOld, FragmentOld>> allMappingsOld = getAllMappingsOld(matching);
		matching.setFragmentCorrespondencesOld(allMappingsOld);
		LOG.info(allMappingsOld.size() + " possible fragment correspondences.");

		// Filter out fragments, that are not node equivalent
		LOG.info("Filter out matches that are not node equivalent");
		Set<Pair<FragmentOld, FragmentOld>> nodeEqMappingsOld = InequivalentNodeFCFilterOld
				.filter(matching);
		matching.setFragmentCorrespondencesOld(nodeEqMappingsOld);
		LOG.info(nodeEqMappingsOld.size() + " fragment correspondences left.");

		// Filter out matches with non-equivalent behaviour
		LOG.info("Filter out matches with inequivalent behavioural profiles");
		Set<Pair<FragmentOld, FragmentOld>> behaviourEquivalentMatchingOld = InequivalentBehaviourFCFilterOld
				.filter(matching);
		matching.setFragmentCorrespondencesOld(behaviourEquivalentMatchingOld);
		LOG.info(behaviourEquivalentMatchingOld.size()
				+ " fragment correspondences left.");

		// Filter out pairs with non-matching conditions
		LOG.info("Filter out matches with inequivalent conditional profiles");
		Set<Pair<FragmentOld, FragmentOld>> conditionEquivalentMatchingOld = InequivalentConditionsFCFilterOld
				.filter(matching);
		matching.setFragmentCorrespondencesOld(conditionEquivalentMatchingOld);
		LOG.info(conditionEquivalentMatchingOld.size()
				+ " fragment correspondences left.");

		// Filter out nested fragment matches
		LOG.info("Filter out nested matches.");
		Set<Pair<FragmentOld, FragmentOld>> unnestedMatchingOld = NestedFCFilterOld
				.filter(matching);
		matching.setFragmentCorrespondencesOld(unnestedMatchingOld);
		LOG.info(unnestedMatchingOld.size() + " fragment correspondences left.");

		// build sequence unions
		LOG.info("Union matches in sequence.");
		Set<Pair<FragmentOld, FragmentOld>> unionedMatchingOld = SequentialFCJointerOld
				.joinSequences(matching);
		matching.setFragmentCorrespondencesOld(unionedMatchingOld);
		LOG.info(unionedMatchingOld.size() + " fragment correspondences left.");

		// filter out trivial fragments (|nodes| < 2)
		LOG.info("Filter out trivial matches.");
		Set<Pair<FragmentOld, FragmentOld>> candidatesOld = TrivialFCFilterOld
				.filter(matching);
		matching.setFragmentCorrespondencesOld(candidatesOld);
		LOG.info(candidatesOld.size() + " fragment correspondences left.");

		return matching;
	}

	public static FragmentMatching getCandidates(Resource res1, Resource res2)
			throws Exception {

		// analyze process1
		Definitions definitions1 = ProcessLoader
				.getDefinitionsFromResource(res1);
		Process process1 = ProcessLoader
				.getProcessFromDefinitions(definitions1);
		LOG.debug("Analyze process 1: " + process1.getName());
		Analysis analysis1 = ProcessAnalyzer.analyze(definitions1);

		// analyze process2
		Definitions definitions2 = ProcessLoader
				.getDefinitionsFromResource(res2);
		Process process2 = ProcessLoader
				.getProcessFromDefinitions(definitions2);
		LOG.debug("Analyze process 2: " + process2.getName());
		Analysis analysis2 = ProcessAnalyzer.analyze(definitions2);

		// determine node correspondences
		LOG.debug("Matching nodes");
		NodeMatching nodeMatching = NodeMatcher.getCorrespondences(process1,
				process2);
		LOG.debug(nodeMatching.getPairs().size() + " node correspondences: ");

		// get all possible Fragment Correspondences
		LOG.info("create all possible fragment correspondences.");
		FragmentMatching allMatchings = getAllFragmentPairs(analysis1,
				analysis2);
		LOG.info(allMatchings.getPairs().size()
				+ " possible fragment correspondences.");

		// Filter out fragments, that are not node equivalent
		LOG.info("Filter out matches that are not node equivalent");
		FragmentMatching nodeEqMatchings = InequivalentNodeFCFilter.filter(
				nodeMatching, allMatchings);
		LOG.info(nodeEqMatchings.getPairs().size()
				+ " fragment correspondences left.");

		// Filter out matches with non-equivalent behaviour
		LOG.info("Filter out matches with inequivalent behavioural profiles");
		FragmentMatching behaviourEquivalentMatching = InequivalentBehaviourFCFilter
				.filter(nodeEqMatchings, nodeMatching, analysis1, analysis2);
		LOG.info(behaviourEquivalentMatching.getPairs().size()
				+ " fragment correspondences left.");

		// Filter out pairs with non-matching conditions
		LOG.info("Filter out matches with inequivalent conditional profiles");
		FragmentMatching conditionEquivalentMatching = InequivalentConditionsFCFilter
				.filter(behaviourEquivalentMatching, nodeMatching, analysis1,
						analysis2);
		LOG.info(conditionEquivalentMatching.getPairs().size()
				+ " fragment correspondences left.");

		// Filter out nested fragment matches
		LOG.info("Filter out nested matches.");
		FragmentMatching unnestedMatching = NestedFCFilter
				.filter(conditionEquivalentMatching);
		LOG.info(unnestedMatching.getPairs().size()
				+ " fragment correspondences left.");

		// build sequence unions
		LOG.info("Union matches in sequence.");
		FragmentMatching unionedMatching = SequentialFCJointer
				.joinSequences(unnestedMatching);
		LOG.info(unionedMatching.getPairs().size()
				+ " fragment correspondences left.");

		// filter out trivial fragments (|nodes| < 2)
		LOG.info("Filter out trivial matches.");
		FragmentMatching candidates = TrivialFCFilter.filter(unionedMatching);
		LOG.info(unionedMatching.getPairs().size()
				+ " fragment correspondences left.");

		return candidates;
	}

	private static Set<Pair<FragmentOld, FragmentOld>> getAllMappingsOld(
			ProcessMatching matching) {
		Set<Pair<FragmentOld, FragmentOld>> pairs = new HashSet<Pair<FragmentOld, FragmentOld>>();
		for (FragmentOld f1 : matching.getAnalysis1().getPst().getFragments2()) {
			for (FragmentOld f2 : matching.getAnalysis2().getPst()
					.getFragments2()) {
				pairs.add(Pair.with(f1, f2));
			}
		}
		return pairs;
	}

	private static FragmentMatching getAllFragmentPairs(Analysis analysis1,
			Analysis analysis2) {
		FragmentMatching matching = BpmnAnalysisFactory.eINSTANCE
				.createFragmentMatching();
		ProcessStructureTree pst1 = ((ProcessStructureTree) analysis1
				.getResults().get("pst"));
		ProcessStructureTree pst2 = ((ProcessStructureTree) analysis2
				.getResults().get("pst"));
		for (Fragment f1 : pst1.getFragments()) {
			for (Fragment f2 : pst2.getFragments()) {
				FragmentPair pair = BpmnAnalysisFactory.eINSTANCE
						.createFragmentPair();
				pair.setA(f1);
				pair.setB(f2);
				matching.getPairs().add(pair);
			}
		}
		return matching;
	}

}
