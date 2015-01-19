package edu.udo.cs.ls14.jf.bpmnmatching.nodematching.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;
import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodePairFilter;
import edu.udo.cs.ls14.jf.registry.Registries;

public class NodeMatcherTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testNodeMatcher() throws Exception {
		Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();
		String pathname = "/bpmn/parallelGateway/";
		Definitions def1 = resSet.loadDefinitions(getClass().getResource(
				pathname + "parallelism1.bpmn").getPath());
		Definitions def2 = resSet.loadDefinitions(getClass().getResource(
				pathname + "parallelism2.bpmn").getPath());
		NodeMatching nodeMatching = ProcessMatchingFactory.getFullNodeMatching(
				def1, def2);
		nodeMatching = NodePairFilter.filter(nodeMatching);
		nodeMatching.getPairs().forEach(
				p -> System.out.println(p.getA().getId() + " , "
						+ p.getB().getId()));

		// make some assertions
		Process process1 = DefinitionsUtil.getProcess(def1);
		Process process2 = DefinitionsUtil.getProcess(def2);
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
