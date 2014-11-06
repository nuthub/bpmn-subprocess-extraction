package edu.udo.cs.ls14.jf.analysis;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.analysis.pst.PST;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;

@Deprecated
public class ProcessAnalyzerOld {

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

		// create behavioral profile
		BehavioralProfile behavioralProfile = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		// create traces
		EList<Trace> traces = Tracer.getTraces(process, reachabilityGraph);
		analysis.setTraces(traces);
		behavioralProfile.getTraces().addAll(traces);
		// create relations
		behavioralProfile.getRelations().addAll(
				BehavioralProfiler.generateProfile(process,
						analysis.getTraces()));
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
