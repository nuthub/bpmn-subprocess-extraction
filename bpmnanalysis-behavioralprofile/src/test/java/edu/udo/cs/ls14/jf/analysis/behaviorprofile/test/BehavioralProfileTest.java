package edu.udo.cs.ls14.jf.analysis.behaviorprofile.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behavioralprofile.debug.BPDebugUtil;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
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
		String pathname = "/bpmn/parallelGateway/";
		String basename = "PM1-mit-Fragment1";
		BehavioralProfile bp = createBpFromBpmnOld(pathname, basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testPM2() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "PM2-mit-Fragment1";
		BehavioralProfile bp = createBpFromBpmnOld(pathname, basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testBpmn2bpLoopingXor() throws Exception {
		String pathname = "/bpmn/loops/";
		String basename = "looping-xor";
		BehavioralProfile bp = createBpFromBpmnOld(pathname, basename);
		assertNotNull(bp);
	}

	@Test
	public void testBpmn2bpLoopingEvents() throws Exception {
		String pathname = "/bpmn/loops/";
		String basename = "looping-events-example";
		BehavioralProfile bp = createBpFromBpmnOld(pathname, basename);
		assertNotNull(bp);
	}

	@Test
	public void testParallelism1() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism1";
		BehavioralProfile bp = createBpFromBpmnOld(pathname, basename);
		assertNotNull(bp);
	}

	@Test
	public void testParallelism2() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism2";
		BehavioralProfile bp = createBpFromBpmnOld(pathname, basename);
		assertNotNull(bp);
	}

	@Test
	public void testParallelism3() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism3";
		BehavioralProfile bp = createBpFromBpmnOld(pathname, basename);
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

		// create P/T-Net from bpmn
		Bpmn2PtnetConverter converter = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = converter.convertToPetriNet(process);

		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());

		// Create Traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);

		// create Behavioral Profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process,
				traceProfile);

		// Write Debug files
		BPDebugUtil.writeDebugFiles("/tmp/" + pathname, basename, process,
				converter, ptnet, rg, nodes, traceProfile, bp);
		return bp;
	}

	@Deprecated
	private BehavioralProfile createBpFromBpmnOld(String pathname,
			String basename) throws Exception {
		System.out.println("Now profiling " + basename);
		// Load BPMN model
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		Process process = DefinitionsUtil.getProcess(definitions);

		// create P/T-Net from bpmn
		Bpmn2PtnetConverter converter = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = converter.convertToPetriNet(process);
		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		// Create Traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		// create Behavioral Profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process,
				traceProfile);
		return bp;
	}

}
