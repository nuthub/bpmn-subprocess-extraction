package edu.udo.cs.ls14.jf.bpmnapplication;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class ProcessAnalyzer {

	public static ProcessAnalysis analyze(Definitions definitions)
			throws Exception {
		Process process = ProcessUtil.getProcessFromDefinitions(definitions);

		// Create Analysis object
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);

		// create petri net
		Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = bpmn2ptnet.convertToPetriNet(process);

		// create reachabilitygraph
		ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
		reachabilityGraph.createFromPTNet(ptnet.getContainedItem());

		// create traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process,
				reachabilityGraph);
		analysis.getResults().put(ProcessAnalysis.TRACEPROFILE, traceProfile);

		// create behavioral profile
		BehavioralProfile behavioralProfile = BehavioralProfiler
				.generateProfile(process, traceProfile);
		analysis.getResults().put(ProcessAnalysis.BEHAVIORALPROFILE,
				behavioralProfile);

		// create conditional profile
		analysis.getResults().put(ProcessAnalysis.CONDITIONALPROFILE,
				ConditionalProfiler.generateProfile(process));

		// create PST
		PSTBuilder pstBuilder = new PSTBuilder();
		analysis.getResults().put(ProcessAnalysis.PROCESSTRUCTURETREE,
				pstBuilder.getTree(definitions));

		// done
		return analysis;
	}
}
