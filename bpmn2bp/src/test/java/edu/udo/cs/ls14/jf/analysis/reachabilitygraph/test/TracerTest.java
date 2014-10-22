package edu.udo.cs.ls14.jf.analysis.reachabilitygraph.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Set;

import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Trace;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class TracerTest {

	@Test
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		runTest(basename, 6);
	}

	@Test
	public void testPM2() throws Exception {
		String basename = "PM2-mit-Fragment1";
		// TODO: check if 10 is expected
		runTest(basename, 10);
	}

	@Test
	public void testXorExample() throws Exception {
		String basename = "xor-example";
		runTest(basename, 2);
	}

	@Test
	public void testLoopingEventsExample() throws Exception {
		String basename = "looping-events-example";
		runTest(basename, 2);
	}

	@Test
	public void loopingLoopingXor() throws Exception {
		String basename = "looping-xor";
		runTest(basename, 3);
	}

	@Test
	public void eventBasedGatewayExclusive() throws Exception {
		String basename = "event-based-gateway-exclusive";
		runTest(basename, 2);
	}

	@Test
	public void eventBasedGatewayParallel() throws Exception {
		String basename = "event-based-gateway-parallel";
		runTest(basename, 2);
	}

	private void runTest(String basename, int expectedTracesSize)
			throws Exception {
		Process process = ProcessLoader.loadFirstProcessFromResource(getClass()
				.getResource("../../bpmn/" + basename + ".bpmn"));
		System.out.println("Now testing " + basename);
		ReachabilityGraph rg = new ReachabilityGraph();
		File file = new File(getClass().getResource(
				"../../pnml/" + basename + ".pnml").toURI());
		rg.createFromPnml(file);
		System.out.println("|V| = " + rg.getVertices().size());
		System.out.println("|E| = " + rg.getEdges().size());
		Set<Trace> traces = Tracer.getTraces(process, rg);
		traces.forEach(t -> System.out.println(t));
		assertEquals(expectedTracesSize, traces.size());
	}

}
