package edu.udo.cs.ls14.jf.bpmnmatching.nodematching.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.NodeMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.NodePair;
import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodePairFilter;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class NodeMatcherTest {

	@Before
	public void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnanalysis",
						new BpmnAnalysisResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnmatching",
						new BpmnMatchingResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnextraction",
						new BpmnTransformationResourceFactoryImpl());

		EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		EPackage.Registry.INSTANCE.put(BpmnAnalysisPackage.eNS_URI,
				BpmnAnalysisPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(BpmnMatchingPackage.eNS_URI,
				BpmnMatchingPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(BpmnTransformationPackage.eNS_URI,
				BpmnTransformationPackage.eINSTANCE);

	}

	@Test
	public void testNodeMatcher() throws Exception {
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath());
		Definitions def1 = resSet.loadDefinitions("PM1-mit-Fragment1.bpmn");
		Definitions def2 = resSet.loadDefinitions("PM3-mit-Fragment2.bpmn");
		NodeMatching nodeMatching = ProcessMatchingFactory.getFullNodeMatching(
				def1, def2);
		nodeMatching = NodePairFilter.filter(nodeMatching);
		nodeMatching.getPairs().forEach(
				p -> System.out.println(p.getA().getId() + " , "
						+ p.getB().getId()));

		// make some assertions
		Process process1 = ProcessUtil.getProcessFromDefinitions(def1);
		Process process2 = ProcessUtil.getProcessFromDefinitions(def2);
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
