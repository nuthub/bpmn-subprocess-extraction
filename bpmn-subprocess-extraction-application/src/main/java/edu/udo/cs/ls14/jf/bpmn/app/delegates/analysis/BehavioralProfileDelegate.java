package edu.udo.cs.ls14.jf.bpmn.app.delegates.analysis;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class BehavioralProfileDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis = (ProcessAnalysis) execution
				.getVariable("analysis");
		Process process = DefinitionsUtil.getProcess(analysis
				.getDefinitions());
		// TODO: add ptnet / rg as results to analysis

		// Create Petri net
		PetriNetHLAPI ptnet = new Bpmn2PtnetConverter()
				.convertToPetriNet(process);
		// Create Reachability Graph
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());

		// Create trace profile
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		analysis.getResults().put(ProcessAnalysisUtil.TRACEPROFILE, traceProfile);

		// create profile
		BehavioralProfile behavioralProfile = BehavioralProfiler
				.generateProfile(process, traceProfile);
		analysis.getResults().put(ProcessAnalysisUtil.BEHAVIORALPROFILE,
				behavioralProfile);

		execution.setVariable("analysis", analysis);
	}
}
