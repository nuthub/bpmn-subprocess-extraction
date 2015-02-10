package edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl;

import javax.jws.WebService;

import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.BehavioralProfilerSEI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnanalysis.ws.BehavioralProfilerSEI")
public class BehavioralProfilerImpl implements BehavioralProfilerSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(BehavioralProfilerImpl.class);

	@Override
	public ProcessAnalysis profile(ProcessAnalysis processAnalysis) {
		Process process;
		try {
			process = DefinitionsUtil.getProcess(processAnalysis
					.getDefinitions());

			// create petri net
			Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
			PetriNetHLAPI ptnet;
			ptnet = bpmn2ptnet.convertToPetriNet(process);

			// create reachabilitygraph
			ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
			reachabilityGraph.createFromPTNet(ptnet.getContainedItem());

			// create traces
			TraceProfile traceProfile;
			traceProfile = Tracer.getTraceProfile(process, reachabilityGraph);
			processAnalysis.getResults().put(ProcessAnalysisUtil.TRACEPROFILE,
					traceProfile);

			// create behavioral profile
			BehavioralProfile profile = BehavioralProfiler.generateProfile(
					process, traceProfile);
			processAnalysis.getResults().put(
					ProcessAnalysisUtil.BEHAVIORALPROFILE, profile);

		} catch (Exception e) {
			LOG.error("Error during analyisis, aborting, returning empty analysis results: ", e);
			return null;
		}
		return processAnalysis;
	}

}
