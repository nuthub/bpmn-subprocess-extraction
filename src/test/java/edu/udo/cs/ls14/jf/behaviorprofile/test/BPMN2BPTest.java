package edu.udo.cs.ls14.jf.behaviorprofile.test;

import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.Set;

import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmn2pnml.Bpmn2PnmlConverter;
import edu.udo.cs.ls14.jf.bpmn2pnml.ProcessLoader;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.reachabilitygraph.Trace;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class BPMN2BPTest {


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
	
	private BehavioralProfile createBpFromBpmn(String basename) throws Exception {
		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		
		// Load BPMN model
		Process process = ProcessLoader.loadFirstProcessFromResource(url);
		
		// create P/T-Net from bpmn
		Bpmn2PnmlConverter converter = new Bpmn2PnmlConverter();
		PetriNetHLAPI ptnet = converter.convertToPTNet(process);
		
		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
				
		// Create Traces
		Set<Trace> traces = rg.getTraces();
		System.out.println(traces);
		System.out.println("---------------------------------");

		// create Behavioral Profile
		BehavioralProfile bp = new BehavioralProfile();
		bp.generateFromTraces(traces);		

		return bp;
	}

}
