package edu.udo.cs.ls14.jf.analysis;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.EList;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.analysis.pst.PST;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class ProcessAnalyzer {

	public static ProcessAnalysis analyze(Definitions definitions) throws Exception {
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE.createProcessAnalysis();
		analysis.setDefinitions(definitions);
		Process process = ProcessUtil.getProcessFromDefinitions(definitions);

		// create petri net
		Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = bpmn2ptnet.convertToPetriNet(process);

		// create reachabilitygraph
		ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
		reachabilityGraph.createFromPTNet(ptnet.getContainedItem());

		// create behavioral profile
		BehavioralProfile behavioralProfile = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		// create traces
		EList<Trace> traces = Tracer.getTraces(process, reachabilityGraph);
		behavioralProfile.getTraces().addAll(traces);
		// create relations
		behavioralProfile.getRelations().addAll(
				BehavioralProfiler.generateProfile(process,
						behavioralProfile.getTraces()));
		analysis.getResults().put("behavioralProfile", behavioralProfile);

		// create conditional profile
		ConditionalProfile conditionalProfile = ConditionalProfiler
				.generateProfile(process);
		analysis.getResults().put("conditionalProfile", conditionalProfile);

		// create PST
		ProcessStructureTree ePst = BpmnAnalysisFactory.eINSTANCE
				.createProcessStructureTree();
		PST pst = new PST();
		pst.createFromDefinitions(definitions);
		ePst.getFragments().addAll(pst.getFragments());
		analysis.getResults().put("pst", ePst);

		// done
		return analysis;
	}

}
