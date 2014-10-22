package edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class Bpmn2PtnetConverterTest {

	private Bpmn2PtnetConverter converter;

	@Before
	public void setUp() {
		converter = new Bpmn2PtnetConverter();
	}

	@Test
	public void testLoopTransformation() throws Exception {
		String basename = "looping-events-example";
		runTest(basename, 9, 9, 18);
	}

	@Test
	public void testXorWEventsTransformation() throws Exception {
		String basename = "xor-example";
		runTest(basename, 9, 9, 18);
	}

	@Test
	public void testLoopingXorTransformation() throws Exception {
		String basename = "looping-xor";
		runTest(basename, 12, 13, 26);
	}

	@Test
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		runTest(basename, 12, 9, 22);
	}

	@Test
	public void testPM2() throws Exception {
		String basename = "PM2-mit-Fragment1";
		runTest(basename, 14, 11, 26);
	}

	@Test
	public void testEventBasedGatewayExclusive() throws Exception {
		String basename = "event-based-gateway-exclusive";
		runTest(basename, 8, 8, 16);
	}

	@Test
	public void testEventBasedGatewayParallel() throws Exception {
		String basename = "event-based-gateway-parallel";
		runTest(basename, 8, 6, 14);
	}

	private void runTest(String basename, int expectedPlaceCount,
			int expectedTransitionCount, int expectedArcCount) throws Exception {
		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		Process process = ProcessLoader.loadFirstProcessFromResource(url);
		PetriNetHLAPI net = converter.convertToPetriNet(process);
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(expectedPlaceCount, page.getObjects_PlaceHLAPI().size());
		assertEquals(expectedTransitionCount, page.getObjects_TransitionHLAPI()
				.size());
		assertEquals(expectedArcCount, page.getObjects_ArcHLAPI().size());
		converter.saveToPnmlFile("/tmp/" + basename + ".pnml");

	}

}
