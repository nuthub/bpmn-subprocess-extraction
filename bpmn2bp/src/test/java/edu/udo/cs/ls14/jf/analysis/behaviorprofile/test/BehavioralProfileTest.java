package edu.udo.cs.ls14.jf.analysis.behaviorprofile.test;

import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Set;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.jbpt.utils.IOUtils;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Trace;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
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
		URL url = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		System.out.println("Now profiling " + basename);
		// Load BPMN model
		Resource resource = ProcessLoader.getBpmnResource(url);
		Process process = ProcessLoader.getProcessFromResource(resource);

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
		Set<Trace> traces = Tracer.getTraces(process, rg);
		// output traces
		for (Trace trace : traces) {
			System.out.println(trace);
			for (String node : trace) {
				System.out.println(" " + node);
			}
		}

		// create Behavioral Profile
		return BehavioralProfiler.generateProfile(process, traces);
	}

}
