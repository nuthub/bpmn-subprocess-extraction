package edu.udo.cs.ls14.jf.analysis.behaviorprofile.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.jbpt.utils.IOUtils;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.DotUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import edu.udo.cs.ls14.jf.registry.Registries;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class BehavioralProfileTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testPM2() throws Exception {
		String basename = "PM2-mit-Fragment1";
		BehavioralProfile bp = createBpFromBpmnOld(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testPM3() throws Exception {
		String basename = "PM3-mit-Fragment2";
		BehavioralProfile bp = createBpFromBpmnOld(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testBpmn2bpLoopingXor() throws Exception {
		String basename = "looping-xor";
		BehavioralProfile bp = createBpFromBpmnOld(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testBpmn2bpLoopingEvents() throws Exception {
		String basename = "looping-events-example";
		BehavioralProfile bp = createBpFromBpmnOld(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testPM1() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism1";

		BehavioralProfile bp = createBpFromBpmn(pathname, basename);
		// print profile
		outputBp(bp);
	}

	@Test
	public void testComplete() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename = "complete2";
		BehavioralProfile bp = createBpFromBpmn(pathname, basename);
		String bpStr = outputBp(bp);

	}

	private BehavioralProfile createBpFromBpmn(String pathname, String basename)
			throws Exception {
		System.out.println("Now profiling " + basename + ".bpmn");
		// Load BPMN model
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		Process process = DefinitionsUtil.getProcess(definitions);

		// Create output directory
		new File("/tmp/" + pathname).mkdirs();

		// create P/T-Net from bpmn
		Bpmn2PtnetConverter converter = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = converter.convertToPetriNet(process);
		converter.saveToPnmlFile("/tmp/" + pathname + basename + ".pnml");

		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		String dot = rg.toDot();

		DotUtil.writeDot("/tmp" + pathname, basename + "-reachabilityGraph",
				dot, "png");

		// Create Traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		// output traces
		StringBuffer sb = new StringBuffer();
		for (Trace trace : traceProfile.getTraces()) {
			for (FlowNode node : trace.getNodes()) {
				sb.append(", "
						+ (node.getName() != null && !node.getName().equals("") ? node
								.getName() : node.getId()));
			}
			if (trace.isFinished()) {
				sb.append(" .");
			} else {
				sb.append(" ...");
			}
			sb.append(System.getProperty("line.separator"));
		}
		BufferedWriter out = new BufferedWriter(new FileWriter("/tmp"
				+ pathname + basename + "-traces.txt"));
		out.write(sb.toString());
		out.flush();
		out.close();
		System.out.println("Wrote traces to /tmp" + pathname + basename
				+ "-traces.txt");
		// create Behavioral Profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process,
				traceProfile);
		String bpStr = outputBp(bp);
		out = new BufferedWriter(new FileWriter("/tmp" + pathname + basename
				+ "-behavioralProfile.txt"));
		out.write(bpStr);
		out.flush();
		out.close();
		System.out.println("Wrote Behavioral Profile to /tmp" + pathname
				+ basename + "-behavioralProfile.txt");
		return bp;
	}

	@Deprecated
	private BehavioralProfile createBpFromBpmnOld(String basename)
			throws Exception {
		System.out.println("Now profiling " + basename);
		// Load BPMN model
		Definitions definitions = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test").getPath())
				.loadDefinitions(basename + ".bpmn");
		Process process = DefinitionsUtil.getProcess(definitions);

		// create P/T-Net from bpmn
		Bpmn2PtnetConverter converter = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = converter.convertToPetriNet(process);
		// converter.saveToPnmlFile("/tmp/" + basename + ".pnml");

		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		String dot = rg.toDot();

		IOUtils.invokeDOT("/tmp", basename + "-reachabilityGraph.png", dot);

		// Create Traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		// output traces
		for (Trace trace : traceProfile.getTraces()) {
			for (FlowNode node : trace.getNodes()) {
				System.out
						.print(" -> "
								+ (node.getName() != null
										&& !node.getName().equals("") ? node
										.getName() : node.getId()));
			}
			if (trace.isFinished()) {
				System.out.println(" .");
			} else {
				System.out.println(" ...");
			}
		}
		// create Behavioral Profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process,
				traceProfile);
		return bp;
	}

	private String outputBp(BehavioralProfile bp) {
		StringBuffer sb = new StringBuffer();
		for (BehavioralRelation rel : bp.getRelations()) {
			sb.append(rel.getLeft().getName() + " / "
					+ rel.getRight().getName() + " : "
					+ rel.getRelation().toString());
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

}
