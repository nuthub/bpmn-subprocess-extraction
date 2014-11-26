package edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile;

import javax.jws.WebService;

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

//@WebService(wsdlLocation = "WEB-INF/wsdl/BPMNAnalysis.wsdl", targetNamespace = "http://bpmnanalysis.bpmn.ws.jf.ls14.cs.udo.edu/", endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile.BehavioralProfilerSEI")
@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile.BehavioralProfilerSEI")
public class BehavioralProfilerImpl implements BehavioralProfilerSEI {

	@Override
	public ProcessAnalysis profile(ProcessAnalysis processAnalysis) {
		Process process;
		try {
			process = ProcessUtil.getProcessFromDefinitions(processAnalysis
					.getDefinitions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// create petri net
		Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet;
		try {
			ptnet = bpmn2ptnet.convertToPetriNet(process);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// create reachabilitygraph
		ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
		try {
			reachabilityGraph.createFromPTNet(ptnet.getContainedItem());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// create traces
		TraceProfile traceProfile;
		try {
			traceProfile = Tracer.getTraceProfile(process, reachabilityGraph);
			processAnalysis.getResults().put(ProcessAnalysis.TRACEPROFILE,
					traceProfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		// create behavioral profile
		BehavioralProfile profile = BehavioralProfiler.generateProfile(process,
				traceProfile);
		processAnalysis.getResults().put(ProcessAnalysis.BEHAVIORALPROFILE,
				profile);
		return processAnalysis;
	}

}
