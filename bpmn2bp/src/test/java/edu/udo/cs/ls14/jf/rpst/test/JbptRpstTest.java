package edu.udo.cs.ls14.jf.rpst.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.eclipse.bpmn2.Process;
import org.jbpt.algo.tree.rpst.RPST;
import org.jbpt.algo.tree.tctree.TCType;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn2pnml.ProcessLoader;
import edu.udo.cs.ls14.jf.rpst.Bpmn2Rpst;
import edu.udo.cs.ls14.jf.rpst.BpmnPathEdge;
import edu.udo.cs.ls14.jf.rpst.BpmnPathVertex;

public class JbptRpstTest {

	@Test
	public void testEventBasedGatewayExclusive() throws Exception {
		runTest("event-based-gateway-exclusive", 10, 3, 1, 0, 6);
	}

	@Test
	public void testEventBasedGatewayParallel() throws Exception {
		runTest("event-based-gateway-parallel", 10, 3, 1, 0, 6);
	}

	@Test
	public void testLoopingEventsExample() throws Exception {
		runTest("looping-events-example", 11, 3, 1, 0, 7);
	}

	@Test
	public void testLoopingXor() throws Exception {
		runTest("looping-xor", 17, 5, 2, 0, 10);
	}

	@Test
	public void testPM1() throws Exception {
		runTest("PM1-mit-Fragment1", 15, 4, 1, 0, 10);
	}

	@Test
	public void testPM2() throws Exception {
		runTest("PM2-mit-Fragment1", 19, 5, 2, 0, 12);
	}

	@Test
	public void testXorExample() throws Exception {
		runTest("xor-example", 10, 3, 1, 0, 6);
	}

	private void runTest(String basename, int total, int polygon, int bond,
			int rigid, int trivial) throws Exception {
		System.out.println("Generating RPST for " + basename + ".bpmn");
		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		Process process = ProcessLoader.loadFirstProcessFromResource(url);
		RPST<BpmnPathEdge, BpmnPathVertex> rpst = Bpmn2Rpst.getRPST(process,
				true);

		assertEquals(total, rpst.getRPSTNodes().size());
		assertEquals(polygon, rpst.getRPSTNodes(TCType.POLYGON).size());
		assertEquals(bond, rpst.getRPSTNodes(TCType.BOND).size());
		assertEquals(rigid, rpst.getRPSTNodes(TCType.RIGID).size());
		assertEquals(trivial, rpst.getRPSTNodes(TCType.TRIVIAL).size());
		assertEquals(0, rpst.getRPSTNodes(TCType.UNDEFINED).size());

	}

}
