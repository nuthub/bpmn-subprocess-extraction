package edu.udo.cs.ls14.jf.analysis.rpst.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.jbpt.algo.tree.rpst.IRPSTNode;
import org.jbpt.algo.tree.rpst.RPST;
import org.jbpt.algo.tree.tctree.TCType;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.rpst.Bpmn2Rpst;
import edu.udo.cs.ls14.jf.analysis.rpst.BpmnPathEdge;
import edu.udo.cs.ls14.jf.analysis.rpst.BpmnPathVertex;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;

public class Bpmn2RpstTest {

	@Test
	public void testEventBasedGatewayExclusive() throws Exception {
		getRPST("event-based-gateway-exclusive", 10, 3, 1, 0, 6);
	}

	@Test
	public void testEventBasedGatewayParallel() throws Exception {
		getRPST("event-based-gateway-parallel", 10, 3, 1, 0, 6);
	}

	@Test
	public void testLoopingEventsExample() throws Exception {
		getRPST("looping-events-example", 11, 3, 1, 0, 7);
	}

	@Test
	public void testLoopingXor() throws Exception {
		getRPST("looping-xor", 17, 5, 2, 0, 10);
	}

	@Test
	@Ignore
	public void testOverlapping() throws Exception {
		getRPST("overlapping", 10, 3, 1, 0, 6);
	}

	@Test
	@Ignore
	public void testPM1() throws Exception {
		getRPST("PM1-mit-Fragment1", 13, 4, 1, 0, 8);
	}

	@Test
	public void testPM2() throws Exception {
		getRPST("PM2-mit-Fragment1", 19, 5, 2, 0, 12);
	}

	@Test
	@Ignore
	public void testSequence() throws Exception {
		String basename = "sequence";
		getRPST(basename, 4, 1, 0, 0, 3);
	}

	@Test
	public void testXorExample() throws Exception {
		getRPST("xor-example", 11, 3, 1, 0, 7);
	}

	private RPST<BpmnPathEdge, BpmnPathVertex> getRPST(String basename,
			int total, int polygon, int bond, int rigid, int trivial)
			throws Exception {
		System.out.println("----------------------------");
		System.out.println("Generating RPST for " + basename + ".bpmn");
		URL url = getClass().getResource("/edu/udo/cs/ls14/jf/bpmn/" + basename + ".bpmn");
		assertNotNull(url);
		Resource resource = ProcessLoader.getBpmnResource(url);
		Process process = ProcessLoader.getProcessFromResource(resource);
		RPST<BpmnPathEdge, BpmnPathVertex> rpst = Bpmn2Rpst.getRPST(process,
				true);
		// output
		outputRPSTNodes(rpst);
		outputSequences(rpst);
		// assertions
		assertEquals(total, rpst.getRPSTNodes().size());
		assertEquals(polygon, rpst.getRPSTNodes(TCType.POLYGON).size());
		assertEquals(bond, rpst.getRPSTNodes(TCType.BOND).size());
		assertEquals(rigid, rpst.getRPSTNodes(TCType.RIGID).size());
		assertEquals(trivial, rpst.getRPSTNodes(TCType.TRIVIAL).size());
		assertEquals(0, rpst.getRPSTNodes(TCType.UNDEFINED).size());
		return rpst;
	}

	private void outputSequences(RPST<BpmnPathEdge, BpmnPathVertex> rpst) {
		for (IRPSTNode<BpmnPathEdge, BpmnPathVertex> node1 : rpst
				.getRPSTNodes()) {
			if (node1.getType() != TCType.TRIVIAL
					&& node1.getType() != TCType.UNDEFINED) {
				System.out.println(node1);
				findSuccessorFragments(rpst, node1).forEach(
						f -> System.out.println("  successor: " + f));
			}
		}
	}

	public Set<IRPSTNode<BpmnPathEdge, BpmnPathVertex>> findSuccessorFragments(
			RPST<BpmnPathEdge, BpmnPathVertex> rpst,
			IRPSTNode<BpmnPathEdge, BpmnPathVertex> node1) {
		Set<IRPSTNode<BpmnPathEdge, BpmnPathVertex>> successors = new HashSet<IRPSTNode<BpmnPathEdge, BpmnPathVertex>>();
		for (IRPSTNode<BpmnPathEdge, BpmnPathVertex> node2 : rpst
				.getRPSTNodes()) {
			if (node1.getType() != TCType.TRIVIAL
					&& node1.getType() != TCType.UNDEFINED
					&& node1.getExit().getFlowNode()
							.equals(node2.getEntry().getFlowNode())) {
				successors.add(node2);
			}
		}
		return successors;
	}

	private void outputRPSTNodes(RPST<BpmnPathEdge, BpmnPathVertex> rpst) {
		// output rpst nodes
		for (IRPSTNode<BpmnPathEdge, BpmnPathVertex> n : rpst.getRPSTNodes()) {
			Set<String> nodes = new HashSet<String>();
			nodes.addAll(n.getFragment().stream()
					.map(e -> e.getSource().getFlowNode().getName())
					.collect(Collectors.toSet()));
			nodes.addAll(n.getFragment().stream()
					.map(e -> e.getTarget().getFlowNode().getName())
					.collect(Collectors.toSet()));
			System.out.println("---");
			System.out.println(n.getType() + ": " + n);
			System.out.println(nodes);
		}
		System.out.println("---");
	}

}
