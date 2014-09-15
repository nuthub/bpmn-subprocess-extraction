package edu.udo.cs.ls14.jf.reachabilitygraph.test;

import java.io.File;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.udo.cs.ls14.jf.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.reachabilitygraph.Edge;
import edu.udo.cs.ls14.jf.reachabilitygraph.Marking;
import edu.udo.cs.ls14.jf.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.reachabilitygraph.Trace;
import edu.udo.cs.ls14.jf.utils.graph.GraphVisualizer;

public class RGGeneratorTest {

	@Test
	public void testRG_PM1() throws Exception {
		ReachabilityGraph g = testGenerateRG("PM1-mit-Fragment1");
		Set<Trace> traces = g.getTraces();
		testGenerateProfile(traces);
	}

	@Test
	public void testPM1() throws Exception {
		ReachabilityGraph g = testGenerateRG("PM2-mit-Fragment1");
		Set<Trace> traces = g.getTraces();
		testGenerateProfile(traces);
	}

	@Test
	public void testXor() throws Exception {
		ReachabilityGraph g = testGenerateRG("xor-example");
		Set<Trace> traces = g.getTraces();
		testGenerateProfile(traces);
	}

	@Test
	public void testLoop() throws Exception {
		ReachabilityGraph g = testGenerateRG("looping-events-example");
		Set<Trace> traces = g.getTraces();
		testGenerateProfile(traces);
	}

	@Test
	public void loopingXor() throws Exception {
		ReachabilityGraph g = testGenerateRG("looping-xor");
		Set<Trace> traces = g.getTraces();
		testGenerateProfile(traces);
	}

	@Test
	public void eventBasedGatewayExclusive() throws Exception {
		ReachabilityGraph g = testGenerateRG("event-based-gateway-exclusive");
		Set<Trace> traces = g.getTraces();
		testGenerateProfile(traces);
	}

	@Test
	public void eventBasedGatewayParallel() throws Exception {
		ReachabilityGraph g = testGenerateRG("event-based-gateway-parallel");
		Set<Trace> traces = g.getTraces();
		testGenerateProfile(traces);
	}

	private void testGenerateProfile(Set<Trace> traces) throws Exception {
		BehavioralProfile bp = new BehavioralProfile();
		bp.generateFromTraces(traces);
		System.out.println(bp);
	}

	private ReachabilityGraph testGenerateRG(String basename) throws Exception {
		System.out.println("Now testing " + basename);
		ReachabilityGraph rg = new ReachabilityGraph();
		File file = new File(getClass().getResource(
				"../../pnml/" + basename + ".pnml").toURI());
		rg.createFromPnml(file);
		System.out.println("|V| = " + rg.getVertices().size());
		System.out.println("|E| = " + rg.getEdges().size());
		// rg.saveGraphML("/tmp/" + BASENAME + ".graphml");
		return rg;
	}

	public static void main(String[] args) throws Exception {
		RGGeneratorTest rgtest = new RGGeneratorTest();

		ReachabilityGraph graph = rgtest.testGenerateRG("looping-xor");

		GraphVisualizer<Marking, Edge> vis = new GraphVisualizer<Marking, Edge>();
		JPanel panel = vis.visualize(graph,
				new ToStringLabeller<Marking>(),
				new ToStringLabeller<Edge>());
		// Create Window / App / JFrame
		JFrame frame = new JFrame("Reachability Graph");
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
