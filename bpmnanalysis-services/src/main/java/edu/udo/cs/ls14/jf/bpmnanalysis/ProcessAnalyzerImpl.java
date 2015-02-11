package edu.udo.cs.ls14.jf.bpmnanalysis;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BPDebugUtil;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.analysis.pst.PSTDebugUtil;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.CPDebugUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisUtil;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

/**
 * {@inheritDoc}
 * 
 * @author JulianFlake
 *
 */
public class ProcessAnalyzerImpl implements ProcessAnalyzer {

	private Bpmn2PtnetConverter bpmn2ptnet = new Bpmn2PtnetConverter();
	private ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
	private PSTBuilder pstBuilder = new PSTBuilder();

	public ProcessAnalysis analyzeAndDebug(Definitions definitions,
			String pathname, String basename, String debugFilesDir,
			List<String> nodes) throws Exception {

		bpmn2ptnet = new Bpmn2PtnetConverter();
		reachabilityGraph = new ReachabilityGraph();
		pstBuilder = new PSTBuilder();

		// Create Analysis object
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);
		// analysis.getDefinitions().setTargetNamespace(
		// "http://" + UUID.randomUUID().toString());
		Process process = DefinitionsUtil.getProcess(analysis.getDefinitions());

		// create PST
		analysis.getResults().put(ProcessAnalysisUtil.PROCESSTRUCTURETREE,
				pstBuilder.getTree(analysis.getDefinitions()));

		// create petri net
		PetriNetHLAPI ptnet = bpmn2ptnet.convertToPetriNet(process);

		// create reachabilitygraph
		reachabilityGraph.createFromPTNet(ptnet.getContainedItem());

		// create traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process,
				reachabilityGraph);
		analysis.getResults().put(ProcessAnalysisUtil.TRACEPROFILE,
				traceProfile);

		// create behavioral profile
		BehavioralProfile behavioralProfile = BehavioralProfiler
				.generateProfile(process, traceProfile);
		analysis.getResults().put(ProcessAnalysisUtil.BEHAVIORALPROFILE,
				behavioralProfile);

		// create conditional profile
		analysis.getResults().put(ProcessAnalysisUtil.CONDITIONALPROFILE,
				ConditionalProfiler.generateProfile(process));

		// TODO fontsizes as parameters
		PSTDebugUtil.writeDebugFiles(debugFilesDir, basename, pstBuilder, 32,
				32, 60);
		BPDebugUtil.writeDebugFiles(debugFilesDir, basename, process,
				bpmn2ptnet, ptnet, reachabilityGraph, nodes, traceProfile,
				behavioralProfile);
		CPDebugUtil.writeDebugFiles(
				debugFilesDir,
				basename,
				nodes,
				(ConditionalProfile) analysis.getResults().get(
						ProcessAnalysisUtil.CONDITIONALPROFILE),
				"conditionalprofile-model");
		// done
		return analysis;
	}

	@Override
	public ProcessAnalysis analyze(Definitions definitions) throws Exception {
		bpmn2ptnet = new Bpmn2PtnetConverter();
		reachabilityGraph = new ReachabilityGraph();
		pstBuilder = new PSTBuilder();

		// Create Analysis object
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);
		Process process = DefinitionsUtil.getProcess(analysis.getDefinitions());

		// create PST
		analysis.getResults().put(ProcessAnalysisUtil.PROCESSTRUCTURETREE,
				pstBuilder.getTree(analysis.getDefinitions()));

		// create petri net
		PetriNetHLAPI ptnet = bpmn2ptnet.convertToPetriNet(process);
		// create reachabilitygraph
		reachabilityGraph.createFromPTNet(ptnet.getContainedItem());
		// create traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process,
				reachabilityGraph);
		// create behavioral profile
		BehavioralProfile behavioralProfile = BehavioralProfiler
				.generateProfile(process, traceProfile);
		// Put results
		analysis.getResults().put(ProcessAnalysisUtil.TRACEPROFILE,
				traceProfile);
		analysis.getResults().put(ProcessAnalysisUtil.BEHAVIORALPROFILE,
				behavioralProfile);

		// create conditional profile
		analysis.getResults().put(ProcessAnalysisUtil.CONDITIONALPROFILE,
				ConditionalProfiler.generateProfile(process));

		// done
		return analysis;
	}

}
