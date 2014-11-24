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
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
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
		// create profile
		BehavioralProfile profile = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		profile.getTraces().addAll(Tracer.getTraces(process, rg));
		profile.getRelations()
				.addAll(BehavioralProfiler.generateProfile(process,
						profile.getTraces()));
		analysis.getResults().put(ProcessAnalysis.BEHAVIORALPROFILE, profile);
		execution.setVariable("processanalysis", analysis);
	}
}
