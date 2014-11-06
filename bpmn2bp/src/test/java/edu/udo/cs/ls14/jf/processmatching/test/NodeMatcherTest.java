package edu.udo.cs.ls14.jf.processmatching.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnanalysis.NodePair;
import edu.udo.cs.ls14.jf.processmatching.NodeMatcher;

public class NodeMatcherTest {

	@Test
	public void testNodeMatcher() throws Exception {
		URL url1 = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/PM1-mit-Fragment1.bpmn");
		URL url2 = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/PM3-mit-Fragment2.bpmn");
		Resource res1 = ProcessLoader.getBpmnResource(url1);
		Resource res2 = ProcessLoader.getBpmnResource(url2);
		Process process1 = ProcessLoader.getProcessFromResource(res1);
		Process process2 = ProcessLoader.getProcessFromResource(res2);
		NodeMatching nodeMatching = NodeMatcher.getCorrespondences(process1,
				process2);
		nodeMatching.getPairs().forEach(
				p -> System.out.println(p.getA().getId() + " , "
						+ p.getB().getId()));

		// make some assertions
		Set<FlowNode> nodes = new HashSet<FlowNode>();
		nodes.add((FlowNode) getElement(process1,
				"sid-0EC98739-28B8-4098-9212-D6FCCD59E1DB"));
		nodes.add((FlowNode) getElement(process1,
				"sid-29EB2FC1-7050-4603-BF12-D0CD6B6116F1"));
		assertTrue(getValues0(nodeMatching).containsAll(nodes));
		assertFalse(getValues1(nodeMatching).containsAll(nodes));
		nodes.add((FlowNode) getElement(process2, "Task_1"));
		assertFalse(getValues1(nodeMatching).containsAll(nodes));
	}

	private Set<FlowNode> getValues0(NodeMatching nodeMatching) {
		return getValues(nodeMatching, (NodePair p) -> p.getA());
	}

	private Set<FlowNode> getValues1(NodeMatching nodeMatching) {
		return getValues(nodeMatching, (NodePair p) -> p.getB());
	}

	private Set<FlowNode> getValues(NodeMatching nodeMatching,
			Function<NodePair, FlowNode> mapper) {
		return nodeMatching.getPairs().stream().map(mapper)
				.collect(Collectors.toSet());
	}

	private FlowElement getElement(Process process, String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}
		for (FlowElement f : process.getFlowElements()) {
			if (f.getId().equals(id)) {
				return f;
			}
		}
		return null;
	}
}
