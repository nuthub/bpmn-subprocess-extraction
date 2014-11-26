package edu.udo.cs.ls14.jf.bpmn.analysis.delegates;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class BehavioralProfileDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis = (ProcessAnalysis) execution
				.getVariable("processanalysis");
		Process process = ProcessUtil.getProcessFromDefinitions(analysis
				.getDefinitions());
		// TODO: add ptnet / rg as results to analysis
		PetriNetHLAPI ptnet = new Bpmn2PtnetConverter()
				.convertToPetriNet(process);
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		// Create trace profile
		TraceProfile traceProfile = BpmnAnalysisFactory.eINSTANCE
				.createTraceProfile();
		traceProfile.getTraces().addAll(Tracer.getTraces(process, rg));
		// create profile
		BehavioralProfile behavioralProfile = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		behavioralProfile.getRelations().addAll(
				BehavioralProfiler.generateProfile(process,
						traceProfile.getTraces()));

		analysis.getResults().put(ProcessAnalysis.TRACEPROFILE, traceProfile);
		analysis.getResults().put(ProcessAnalysis.BEHAVIORALPROFILE,
				behavioralProfile);
		execution.setVariable("processanalysis", analysis);
	}
}
