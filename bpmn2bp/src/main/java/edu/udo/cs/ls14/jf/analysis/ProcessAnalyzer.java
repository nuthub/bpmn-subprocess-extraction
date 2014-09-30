package edu.udo.cs.ls14.jf.analysis;

import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmn2pnml.Bpmn2PnmlConverter;
import edu.udo.cs.ls14.jf.pst.PST;
import edu.udo.cs.ls14.jf.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.reachabilitygraph.Tracer;

public class ProcessAnalyzer {

	public static ProcessAnalysis analyze(Process process) throws Exception {
		ProcessAnalysis analysis = new ProcessAnalysis();
		analysis.setProcess(process);
		// create petri net
		Bpmn2PnmlConverter bpmn2ptnet = new Bpmn2PnmlConverter();
		analysis.setPtnet(bpmn2ptnet.convertToPetriNet(process));
		// create reachabilitygraph
		ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
		reachabilityGraph.createFromPTNet(analysis.getPtnet()
				.getContainedItem());
		analysis.setReachabilityGraph(reachabilityGraph);
		// create traces
		analysis.setTraces(Tracer.getTraces(process, reachabilityGraph));
		// create behavioral profile
		BehavioralProfile behavioralProfile = new BehavioralProfile();
		behavioralProfile.generateFromTraces(process, analysis.getTraces());
		analysis.setBehavioralProfile(behavioralProfile);
		// create PST
		PST pst = new PST();
		pst.createFromProcess(process);
		analysis.setPst(pst);
		return analysis;
	}

}