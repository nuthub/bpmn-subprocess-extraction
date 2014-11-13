package edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile;

import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.EList;

import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile.BehavioralProfilerSEI")
public class BehavioralProfilerImpl implements BehavioralProfilerSEI {

	@Override
	public BehavioralProfile profile(Definitions definitions) throws Exception {
		Process process = ProcessUtil.getProcessFromDefinitions(definitions);

		// create petri net
		Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = bpmn2ptnet.convertToPetriNet(process);

		// create reachabilitygraph
		ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
		reachabilityGraph.createFromPTNet(ptnet.getContainedItem());

		// create behavioral profile
		BehavioralProfile profile = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		// create traces
		EList<Trace> traces = Tracer.getTraces(process, reachabilityGraph);
		profile.getTraces().addAll(traces);
		// create relations
		profile
				.getRelations()
				.addAll(edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler
						.generateProfile(process, profile.getTraces()));

		return profile;
	}

}
