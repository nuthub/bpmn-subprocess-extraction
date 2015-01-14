package edu.udo.cs.ls14.jf.analysis.behaviorprofile.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.IOUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
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
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		BehavioralProfile bp = createBpFromBpmnOld(basename);
		// print profile
		System.out.println(bp);
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
	public void testParallelism1() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism1";
//		BehavioralProfile bp = createBpFromBpmn(pathname, basename);
		// assertNotNull(bp);
	}

	@Test
	public void testParallelism2() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism2";
		// BehavioralProfile bp = createBpFromBpmn(pathname, basename);
		// assertNotNull(bp);
	}

	@Test
	public void testParallelism3() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism3";
		BehavioralProfile bp = createBpFromBpmn(pathname, basename,
				Arrays.asList("n_start", "T1", "T2", "T3", "T4", "T5", "T6",
						"E1", "E2", "E3", "E4", "n_end"));
		assertNotNull(bp);
	}

	@Test
	public void testComplete1() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename = "complete1";
		BehavioralProfile bp = createBpFromBpmn(pathname, basename,
				Arrays.asList("n_start", "T1", "T2", "T3", "T4", "T5", "T6",
						"E1", "E2", "E3", "E4", "n_end"));
		assertNotNull(bp);
	}

	@Test
	public void testComplete2() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename = "complete2";
		BehavioralProfile bp = createBpFromBpmn(pathname, basename,
				Arrays.asList("n_start", "T1", "T2", "T3", "T4", "T5", "T6",
						"E1", "E2", "E3", "E4", "n_end"));
		assertNotNull(bp);
	}

	/**
	 * Tests, that (EMF) list may contain a value multiple times (unique=false)
	 * TODO: move to another suite
	 */
	@Test
	public void testTrace() {
		Trace t = BpmnAnalysisFactory.eINSTANCE.createTrace();
		System.out.println(t.getNodes().getClass().getName());
		FlowNode n = Bpmn2Factory.eINSTANCE.createTask();
		n.setId("bla");
		n.setName("blub");
		assertTrue(t.getNodes().add(n));
		assertTrue(t.getNodes().add(n));
		assertTrue(t.getNodes().add(n));
		assertTrue(t.getNodes().add(n));
	}

	/**
	 * Move debug output to another class
	 * 
	 * @param pathname
	 * @param basename
	 * @param nodes
	 * @return
	 * @throws Exception
	 */
	private BehavioralProfile createBpFromBpmn(String pathname,
			String basename, List<String> nodes) throws Exception {
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
		IOUtil.writeDot("/tmp" + pathname, basename + "-ptnet",
				converter.toDot());

		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		String dot = rg.toDot(process, ptnet.getContainedItem());
		IOUtil.writeDot("/tmp" + pathname, basename + "-reachabilityGraph",
				dot);

		// Create Traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		// output traces
		IOUtil.writeTxtFile(tracesToString(traceProfile), "/tmp/" + pathname
				+ basename + "-traces.txt");
		System.out.println("Wrote traces to /tmp" + pathname + basename
				+ "-traces.txt");
		// create Behavioral Profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process,
				traceProfile);
		// writeTxtFile(bpToString(bp), "/tmp" + pathname + basename
		// + "-behavioralProfile.txt");
		// System.out.println("Wrote Behavioral Profile to /tmp" + pathname
		// + basename + "-behavioralProfile.txt");
		IOUtil.writeTxtFile(bpToSuccRelTabular(nodes, bp), "/tmp" + pathname
				+ basename + "-nachfolgerelation.tex");
		System.out.println("Wrote succession relation to /tmp" + pathname
				+ basename + "-nachfolgerelation.tex");
		IOUtil.writeTxtFile(bpToTabular(nodes, bp), "/tmp" + pathname
				+ basename + "-verhaltensprofil.tex");
		System.out.println("Wrote Behavioral Profile to /tmp" + pathname
				+ basename + "-verhaltensprofil.tex");
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
		String dot = rg.toDot(process, ptnet.getContainedItem());
		IOUtil.writeAndRunDot("/tmp/", basename, dot, "png");

		// Create Traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		// output traces
		System.out.println(tracesToString(traceProfile));
		// create Behavioral Profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process,
				traceProfile);
		return bp;
	}

	/**
	 * TODO: move elsewhere
	 * 
	 * @param bp
	 * @return
	 */
	private String bpToString(BehavioralProfile bp) {
		StringBuffer sb = new StringBuffer();
		for (BehavioralRelation rel : bp.getRelations()) {
			sb.append(rel.getLeft().getName() + " / "
					+ rel.getRight().getName() + " : "
					+ rel.getRelation().toString());
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	private String bpToSuccRelTabular(List<String> nodes, BehavioralProfile bp) {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		// begin table
		sb.append("\\begin{tabular}{|r||");
		for (int i = 0; i < nodes.size(); i++) {
			sb.append("c|");
		}
		sb.append("}" + nl);
		// Header
		sb.append("\\hline" + nl);
		sb.append("$\\succ_M$ ");
		for (String node : nodes) {
			sb.append(" & " + node.replaceAll("\\_", "\\\\_"));
		}
		// rows
		sb.append("\\\\\\hline\\hline" + nl);
		for (String row : nodes) {
			sb.append(row.replaceAll("\\_", "\\\\_"));
			for (String col : nodes) {
				sb.append(" & ");
				for (BehavioralRelation rel : bp.getRelations()) {
					if (rel.getLeft().getName().equals(row)
							&& rel.getRight().getName().equals(col)
							&& (rel.getRelation() == BehavioralRelationType.PARALLEL || rel
									.getRelation() == BehavioralRelationType.DIRECT_SUCCESSOR)) {
						sb.append("$\\bullet$");
					}
				}
			}
			sb.append(" \\\\\\hline" + nl);
		}
		// end table
		sb.append("\\end{tabular}" + nl);
		return sb.toString();
	}

	private String bpToTabular(List<String> nodes, BehavioralProfile bp) {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		// begin table
		sb.append("\\begin{tabular}{|r||");
		for (int i = 0; i < nodes.size(); i++) {
			sb.append("c|");
		}
		sb.append("}" + nl);
		// Header
		sb.append("\\hline" + nl);
		sb.append("$BP_M$ ");
		for (String node : nodes) {
			sb.append(" & " + node.replaceAll("\\_", "\\\\_"));
		}
		// rows
		sb.append("\\\\\\hline\\hline" + nl);
		for (String row : nodes) {
			sb.append(row.replaceAll("\\_", "\\\\_"));
			for (String col : nodes) {
				sb.append(" & ");
				for (BehavioralRelation rel : bp.getRelations()) {
					if (rel.getLeft().getName().equals(row)
							&& rel.getRight().getName().equals(col)) {
						switch (rel.getRelation()) {
						case NO_SUCCESSION:
							sb.append("\\#");
							break;
						case DIRECT_SUCCESSOR:
							sb.append("$\\rightarrow$");
							break;
						case DIRECT_PREDECESSOR:
							sb.append("$\\leftarrow$");
							break;
						case PARALLEL:
							sb.append("$\\parallel$");
							break;
						}
					}
				}
			}
			sb.append(" \\\\\\hline" + nl);
		}
		// end table
		sb.append("\\end{tabular}" + nl);
		return sb.toString();
	}

	/**
	 * TODO: move elsewhere
	 * 
	 * @param traceProfile
	 * @return
	 */
	private String tracesToString(TraceProfile traceProfile) {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		for (Trace trace : traceProfile.getTraces()) {
			List<String> nodeList = new ArrayList<String>();
			for (FlowNode node : trace.getNodes()) {
				nodeList.add(node.getName() != null
						&& !node.getName().equals("") ? node.getName() : node
						.getId());
				// sb.append(", "
				// + (node.getName() != null && !node.getName().equals("") ?
				// node
				// .getName() : node.getId()));
			}
			sb.append(StringUtils.join(nodeList, ", "));
			if (trace.isFinished()) {
				sb.append(" .");
			} else {
				sb.append(" ...");
			}
			sb.append(nl);
		}
		return sb.toString();
	}

}
