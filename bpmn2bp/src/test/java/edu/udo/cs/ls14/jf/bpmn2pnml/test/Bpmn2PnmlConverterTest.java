package edu.udo.cs.ls14.jf.bpmn2pnml.test;

import static org.junit.Assert.*;

import java.net.URL;

import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn2pnml.Bpmn2PnmlConverter;
import edu.udo.cs.ls14.jf.bpmn2pnml.ProcessLoader;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class Bpmn2PnmlConverterTest {

	private Bpmn2PnmlConverter converter;

	@Before
	public void setUp() {
		converter = new Bpmn2PnmlConverter();
	}

	@Test
	public void testLoopTransformation() throws Exception {
		PetriNetHLAPI net = runConversionToPTNet("looping-events-example");
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(9, page.getObjects_PlaceHLAPI().size());
		assertEquals(9, page.getObjects_TransitionHLAPI().size());
		assertEquals(18, page.getObjects_ArcHLAPI().size());
	}

	@Test
	public void testXorWEventsTransformation() throws Exception {
		PetriNetHLAPI net = runConversionToPTNet("xor-example");
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(8, page.getObjects_PlaceHLAPI().size());
		assertEquals(8, page.getObjects_TransitionHLAPI().size());
		assertEquals(16, page.getObjects_ArcHLAPI().size());
	}

	@Test
	public void testLoopingXorTransformation() throws Exception {
		PetriNetHLAPI net = runConversionToPTNet("looping-xor");
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(12, page.getObjects_PlaceHLAPI().size());
		assertEquals(13, page.getObjects_TransitionHLAPI().size());
		assertEquals(26, page.getObjects_ArcHLAPI().size());
	}

	@Test
	public void testPM1Transformation() throws Exception {
		PetriNetHLAPI net = runConversionToPTNet("PM1-mit-Fragment1");
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(12, page.getObjects_PlaceHLAPI().size());
		assertEquals(9, page.getObjects_TransitionHLAPI().size());
		assertEquals(22, page.getObjects_ArcHLAPI().size());
	}

	@Test
	public void testPM2Transformation() throws Exception {
		PetriNetHLAPI net = runConversionToPTNet("PM2-mit-Fragment1");
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(14, page.getObjects_PlaceHLAPI().size());
		assertEquals(11, page.getObjects_TransitionHLAPI().size());
		assertEquals(26, page.getObjects_ArcHLAPI().size());
	}

	private PetriNetHLAPI runConversionToPTNet(String basename)
			throws Exception {
		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		Process process = ProcessLoader.loadFirstProcessFromResource(url);
		return converter.convertToPTNet(process);
	}

	private void runConversionToFile(String basename) throws Exception {
		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		Process process = ProcessLoader.loadFirstProcessFromResource(url);
		converter.convertToPnmlFile(process, "/tmp/" + basename + ".pnml");
	}
}
