package edu.udo.cs.ls14.jf.analysis.reachabilitygraph.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;

public class TracerTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testConstructor() {
		assertNotNull(new Tracer());
	}

	@Test
	public void testParallelism1() throws Exception {
		String basename = "parallelism1";
		runTest("/bpmn/parallelGateway/", basename, 6);
	}

	@Test
	public void testParallelism2() throws Exception {
		String basename = "parallelism2";
		// TODO: check if 10 is expected
		runTest("/bpmn/parallelGateway/", basename, 10);
	}

	@Test
	public void testXorExample() throws Exception {
		String basename = "xor-example";
		runTest("/bpmn/exclusiveGateway/", basename, 2);
	}

	@Test
	public void testLoopingEventsExample() throws Exception {
		String basename = "looping-events-example";
		runTest("/bpmn/loops/", basename, 2);
	}

	@Test
	public void loopingLoopingXor() throws Exception {
		String basename = "looping-xor";
		runTest("/bpmn/loops/", basename, 3);
	}

	@Test
	public void eventBasedGatewayExclusive() throws Exception {
		String basename = "event-based-gateway-exclusive";
		runTest("/bpmn/eventBasedGateway/", basename, 2);
	}

	@Test
	public void eventBasedGatewayParallel() throws Exception {
		String basename = "event-based-gateway-parallel";
		runTest("/bpmn/eventBasedGateway/", basename, 2);
	}

	private void runTest(String pathname, String basename,
			int expectedTracesSize) throws Exception {
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		Process process = DefinitionsUtil.getProcess(definitions);

		System.out.println("Now testing " + basename);
		ReachabilityGraph rg = new ReachabilityGraph();
		File file = new File(getClass().getResource(
				"/pnml/" + basename + ".pnml").toURI());
		rg.createFromPnml(file);
		System.out.println("|V| = " + rg.getVertices().size());
		System.out.println("|E| = " + rg.getEdges().size());
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		traceProfile.getTraces().forEach(t -> System.out.println(t));
		assertEquals(expectedTracesSize, traceProfile.getTraces().size());
	}

}
