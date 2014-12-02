package edu.udo.cs.ls14.jf.bpmn.utils;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

public class ProcessMatchingFactory {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingFactory.class);

	public static ProcessMatching createEmptyMatching(
			ProcessAnalysis analysis1, ProcessAnalysis analysis2) {
		ProcessMatching matching = BpmnMatchingFactory.eINSTANCE
				.createProcessMatching();
		matching.setAnalysis1(analysis1);
		matching.setAnalysis2(analysis2);
		return matching;
	}

	public static NodeMatching getFullNodeMatching(Definitions definitions1,
			Definitions definitions2) {
		NodeMatching matching = BpmnMatchingFactory.eINSTANCE
				.createNodeMatching();
		Process process1 = null;
		Process process2 = null;
		try {
			process1 = DefinitionsUtil.getProcess(definitions1);
		} catch (Exception e) {
			LOG.error("Could not get Process from Definitions1");
			return null;
		}
		try {
			process2 = DefinitionsUtil.getProcess(definitions2);
		} catch (Exception e) {
			LOG.error("Could not get Process from Definitions2");
			return null;
		}
		for (FlowElement e1 : process1.getFlowElements()) {
			if (e1 instanceof Activity || e1 instanceof Event) {
				for (FlowElement e2 : process2.getFlowElements()) {
					if (e2 instanceof Activity || e2 instanceof Event) {
						NodePair pair = BpmnMatchingFactory.eINSTANCE
								.createNodePair();
						pair.setA((FlowNode) e1);
						pair.setB((FlowNode) e2);
						matching.getPairs().add(pair);
					}
				}
			}
		}
		return matching;
	}

	public static FragmentMatching getFullFragmentMatching(
			ProcessAnalysis analysis1, ProcessAnalysis analysis2) {
		FragmentMatching matching = BpmnMatchingFactory.eINSTANCE
				.createFragmentMatching();
		ProcessStructureTree pst1 = ((ProcessStructureTree) analysis1
				.getResults().get("pst"));
		if (pst1 == null) {
			LOG.error("PST is not contained in first ProcessAnalysis");
		}
		ProcessStructureTree pst2 = ((ProcessStructureTree) analysis2
				.getResults().get("pst"));
		if (pst2 == null) {
			LOG.error("PST is not contained in second ProcessAnalysis");
		}
		for (Fragment f1 : pst1.getFragments()) {
			for (Fragment f2 : pst2.getFragments()) {
				FragmentPair pair = BpmnMatchingFactory.eINSTANCE
						.createFragmentPair();
				pair.setA(f1);
				pair.setB(f2);
				matching.getPairs().add(pair);
			}
		}
		return matching;
	}
}
