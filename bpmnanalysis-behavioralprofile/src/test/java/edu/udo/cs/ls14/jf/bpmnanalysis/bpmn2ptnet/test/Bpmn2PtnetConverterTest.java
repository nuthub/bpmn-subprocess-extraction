package edu.udo.cs.ls14.jf.bpmnanalysis.bpmn2ptnet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.bpmn2ptnet.Bpmn2PtnetConverter;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class Bpmn2PtnetConverterTest {

	private static final String TARGET_DIR = System
			.getProperty("java.io.tmpdir") + "/test-bp2ptnet/";

	private Bpmn2PtnetConverter converter;

	@Before
	public void setUp() {
		Registries.registerAll();
		converter = new Bpmn2PtnetConverter();
	}

	@Test(expected = NotImplementedException.class)
	public void testUnspecifiedGateway() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "UnspecifiedGateway";
		runTest(pathname, basename, 0, 0, 0);
	}

	@Test(expected = NotImplementedException.class)
	public void testCallActivity() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "CallActivity";
		runTest(pathname, basename, 0, 0, 0);
	}

	@Test(expected = NotImplementedException.class)
	public void testSubProcess() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "SubProcess";
		runTest(pathname, basename, 0, 0, 0);
	}

	@Test(expected = NotImplementedException.class)
	public void testAdHocSubProcess() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "AdHocSubProcess";
		runTest(pathname, basename, 0, 0, 0);
	}

	@Test(expected = NotImplementedException.class)
	public void testBoundaryEvent() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "BoundaryEvent";
		runTest(pathname, basename, 0, 0, 0);
	}

	@Test
	public void testDeadlock() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "Deadlock";
		runTest(pathname, basename, 7, 6, 13);
	}

	@Test
	public void testLackOfSync() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "LackOfSync";
		runTest(pathname, basename, 7, 6, 13);
	}

	@Test
	public void testMultiStart() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "MultipleStartEvents";
		runTest(pathname, basename, 6, 5, 10);
	}

	@Test
	public void testMultiEnd() throws Exception {
		String pathname = "/bpmn/bad/";
		String basename = "MultipleEndEvents";
		runTest(pathname, basename, 6, 4, 9);
	}

	@Test
	public void testLoops() throws Exception {
		String pathname = "/bpmn/loops/";
		String basename = "looping-events-example";
		runTest(pathname, basename, 9, 9, 18);
	}

	@Test
	public void testXorWEventsTransformation() throws Exception {
		String pathname = "/bpmn/exclusiveGateway/";
		String basename = "xor-example";
		runTest(pathname, basename, 9, 9, 18);
	}

	@Test
	public void testLoopingXorTransformation() throws Exception {
		String pathname = "/bpmn/loops/";
		String basename = "looping-xor";
		runTest(pathname, basename, 12, 13, 26);
	}

	@Test
	public void testParallelism1() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism1";
		runTest(pathname, basename, 12, 9, 22);
	}

	@Test
	public void testParallelism2() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename = "parallelism2";
		runTest(pathname, basename, 14, 11, 26);
	}

	@Test
	public void testEventBasedGatewayExclusive() throws Exception {
		String pathname = "/bpmn/eventBasedGateway/";
		String basename = "event-based-gateway-exclusive";
		runTest(pathname, basename, 8, 8, 16);
	}

	@Test
	public void testEventBasedGatewayParallel() throws Exception {
		String pathname = "/bpmn/eventBasedGateway/";
		String basename = "event-based-gateway-parallel";
		runTest(pathname, basename, 8, 6, 14);
	}

	private void runTest(String pathname, String basename,
			int expectedPlaceCount, int expectedTransitionCount,
			int expectedArcCount) throws Exception {
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		Process process = DefinitionsUtil.getProcess(definitions);
		PetriNetHLAPI net = converter.convertToPetriNet(process);
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(expectedPlaceCount, page.getObjects_PlaceHLAPI().size());
		assertEquals(expectedTransitionCount, page.getObjects_TransitionHLAPI()
				.size());
		assertEquals(expectedArcCount, page.getObjects_ArcHLAPI().size());
		new File(TARGET_DIR + pathname).mkdirs();
		converter.saveToPnmlFile(TARGET_DIR + pathname + basename + ".pnml");
		assertNotNull(converter.toString());

	}

}
