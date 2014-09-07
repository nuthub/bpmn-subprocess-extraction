package edu.udo.cs.ls14.jf.bpmn2pnml.test;

import static org.junit.Assert.*;

import java.net.URL;

import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn2pnml.Bpmn2PnmlConverter;
import edu.udo.cs.ls14.jf.bpmn2pnml.ProcessLoader;

public class Bpmn2PnmlConverterTest {

	private Bpmn2PnmlConverter converter;

	@Before
	public void setUp() {
		converter = new Bpmn2PnmlConverter();
	}

	@Test
	public void testLoopTransformation() throws Exception {
		runConversion("looping-events-example");
	}

	@Test
	public void testXorWEventsTransformation() throws Exception {
		runConversion("xor-example");
	}
	
	@Test
	public void testLoopingXorTransformation() throws Exception {
		runConversion("looping-xor");
	}

	@Test
	public void testPM1Transformation() throws Exception {
		runConversion("PM1-mit-Fragment1");
	}

	@Test
	public void testPM2Transformation() throws Exception {
		runConversion("PM2-mit-Fragment1");
	}

	private void runConversion(String basename) throws Exception {
		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		Process process = ProcessLoader.loadFirstProcessFromResource(url);
		converter.convertToPnmlFile(process, "/tmp/" + basename + ".pnml");
	}
}
