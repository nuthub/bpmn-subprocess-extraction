package edu.udo.cs.ls14.jf.bpmn.analysis.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
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
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		analysis.getResults().put(ProcessAnalysis.TRACEPROFILE, traceProfile);

		// create profile
		BehavioralProfile behavioralProfile = BehavioralProfiler
				.generateProfile(process, traceProfile);
		analysis.getResults().put(ProcessAnalysis.BEHAVIORALPROFILE,
				behavioralProfile);

		execution.setVariable("processanalysis", analysis);
	}
}