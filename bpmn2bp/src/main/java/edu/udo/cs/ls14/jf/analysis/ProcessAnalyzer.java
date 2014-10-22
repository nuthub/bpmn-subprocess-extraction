package edu.udo.cs.ls14.jf.analysis;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.conditionalprofile.ConditionalProfile;
import edu.udo.cs.ls14.jf.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.pst.PST;
import edu.udo.cs.ls14.jf.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessAnalyzer {

	public static ProcessAnalysis analyze(Resource resource) throws Exception {
		ProcessAnalysis analysis = new ProcessAnalysis();
		analysis.setResource(resource);
		Process process = ProcessLoader.getProcessFromResource(resource);
		analysis.setProcess(process);

		// create petri net
		Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
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

		// create conditional profile
		ConditionalProfile conditionalProfile = ConditionalProfiler
				.generateProfile(resource);
		analysis.setConditionalProfile(conditionalProfile);

		// create PST
		PST pst = new PST();
		pst.createFromProcess(resource);
		analysis.setPst(pst);

		// done
		return analysis;
	}

}
