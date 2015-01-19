package edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.registry.Registries;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class Bpmn2PtnetConverterTest {

	private Bpmn2PtnetConverter converter;

	@Before
	public void setUp() {
		Registries.registerAll();
		converter = new Bpmn2PtnetConverter();
	}

	@Test
	public void testLoopTransformation() throws Exception {
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

	private void runTest(String pathname, String basename, int expectedPlaceCount,
			int expectedTransitionCount, int expectedArcCount) throws Exception {
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(getClass().getResource(
						pathname + basename + ".bpmn").getPath());
		Process process = DefinitionsUtil.getProcess(definitions);
		PetriNetHLAPI net = converter.convertToPetriNet(process);
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(expectedPlaceCount, page.getObjects_PlaceHLAPI().size());
		assertEquals(expectedTransitionCount, page.getObjects_TransitionHLAPI()
				.size());
		assertEquals(expectedArcCount, page.getObjects_ArcHLAPI().size());
		new File("/tmp/" + pathname).mkdirs();
		converter.saveToPnmlFile("/tmp/" + pathname + basename + ".pnml");

	}

}
