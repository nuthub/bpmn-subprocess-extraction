package edu.udo.cs.ls14.jf.analysis.behaviorprofile.test;

import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.jbpt.utils.IOUtils;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class BehavioralProfileTest {

	@Test
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testPM2() throws Exception {
		String basename = "PM2-mit-Fragment1";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testPM3() throws Exception {
		String basename = "PM3-mit-Fragment2";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testBpmn2bpLoopingXor() throws Exception {
		String basename = "looping-xor";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testBpmn2bpLoopingEvents() throws Exception {
		String basename = "looping-events-example";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	private BehavioralProfile createBpFromBpmn(String basename)
			throws Exception {
		System.out.println("Now profiling " + basename);
		// Load BPMN model
		Resource resource = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test").getPath())
				.loadResource(basename + ".bpmn");
		Process process = ProcessUtil
				.getProcessFromDocumentRoot((DocumentRoot) resource
						.getContents().get(0));

		// create P/T-Net from bpmn
		Bpmn2PtnetConverter converter = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = converter.convertToPetriNet(process);
		// converter.saveToPnmlFile("/tmp/" + basename + ".pnml");

		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		String dot = rg.toDot();
		System.out.println(dot);
		IOUtils.invokeDOT("/tmp", basename + "-reachabilityGraph.png", dot);

		// Create Traces
		BehavioralProfile bp = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		bp.getTraces().addAll(Tracer.getTraces(process, rg));
		// output traces
		for (Trace trace : bp.getTraces()) {
			System.out.println(trace);
			for (FlowNode node : trace.getNodes()) {
				System.out.println(" " + node.getId());
			}
		}

		// create Behavioral Profile
		bp.getRelations().addAll(
				BehavioralProfiler.generateProfile(process, bp.getTraces()));
		return bp;
	}

}
