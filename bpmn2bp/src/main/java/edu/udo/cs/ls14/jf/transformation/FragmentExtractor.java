package edu.udo.cs.ls14.jf.transformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

public class FragmentExtractor extends HenshinTransformation {

	private static final String RULEFILE = "bpmnModifier";
	private static final String RULEPATH = "src/main/resources/edu/udo/cs/ls14/jf/henshin/";

	@Override
	protected String getRulePath() {
		return RULEPATH;
	}

	public void replaceId(Resource resource, String oldId, String newId)
			throws Exception {
		EGraph graph = new EGraphImpl(resource);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("oldId", oldId);
		parameters.put("newId", newId);
		applyRule(graph, RULEFILE, "setId", parameters);
	}

	public void cropFragment(Resource resource, Definitions definitions,
			Fragment fragment) throws Exception {
		Process process = ProcessLoader.getProcessFromDefinitions(definitions);
		Pair<Float, Float> startEventCoords = CoordinateCalculator.getCoords(
				fragment.getEntry().getSourceRef(), definitions);
		Pair<Float, Float> endEventCoords = CoordinateCalculator.getCoords(
				fragment.getExit().getTargetRef(), definitions);

		Set<String> preserveElementIds = FragmentUtil
				.getFlowElements(fragment, e -> true).stream()
				.map(e -> e.getId()).collect(Collectors.toSet());
		preserveElementIds.add(fragment.getEntry().getId());
		preserveElementIds.add(fragment.getExit().getId());
		Set<FlowElement> deleteElements = process.getFlowElements().stream()
				.filter(e -> !preserveElementIds.contains(e.getId()))
				.collect(Collectors.toSet());
		Set<FlowElement> deleteNodes = deleteElements.stream()
				.filter(e -> e instanceof FlowNode).collect(Collectors.toSet());
		Set<FlowElement> deleteEdges = deleteElements.stream()
				.filter(e -> e instanceof SequenceFlow)
				.collect(Collectors.toSet());
		EGraph graph = new EGraphImpl(resource);
		Map<String, Object> parameters = new HashMap<String, Object>();
		// create startevent
		String startEventId = EcoreUtil.generateUUID();
		parameters.clear();
		parameters.put("id", startEventId);
		parameters.put("name", "Start");
		parameters.put("shapeId", startEventId + "_gui");
		parameters.put("x", startEventCoords.getValue0());
		parameters.put("y", startEventCoords.getValue1());
		applyRule(graph, RULEFILE, "createStartEvent", parameters);
		// set sourceRef of entry edge
		parameters.clear();
		parameters.put("id", fragment.getEntry().getId());
		parameters.put("sourceId", startEventId);
		applyRule(graph, RULEFILE, "setSourceRef", parameters);
		// set name of entry edge
		parameters.clear();
		parameters.put("id", fragment.getEntry().getId());
		parameters.put("oldName", fragment.getEntry().getName());
		parameters.put("newName", "");
		applyRule(graph, RULEFILE, "setName", parameters);
		// remove conditions of entry edge
		parameters.clear();
		parameters.put("id", fragment.getEntry().getId());
		applyRule(graph, RULEFILE, "deleteExpression", parameters);
		// create endevent
		String endEventId = EcoreUtil.generateUUID();
		parameters.clear();
		parameters.put("id", endEventId);
		parameters.put("name", "End");
		parameters.put("shapeId", endEventId + "_gui");
		parameters.put("x", endEventCoords.getValue0());
		parameters.put("y", endEventCoords.getValue1());
		applyRule(graph, RULEFILE, "createEndEvent", parameters);
		// set targetRef of exit edge
		parameters.clear();
		parameters.put("id", fragment.getExit().getId());
		parameters.put("targetId", endEventId);
		applyRule(graph, RULEFILE, "setTargetRef", parameters);
		// remove conditions of exit edge
		parameters.clear();
		parameters.put("id", fragment.getExit().getId());
		applyRule(graph, RULEFILE, "deleteExpression", parameters);
		// set name of exit edge
		parameters.clear();
		parameters.put("id", fragment.getExit().getId());
		parameters.put("oldName", fragment.getExit().getName());
		parameters.put("newName", "");
		applyRule(graph, RULEFILE, "setName", parameters);
		// remove sequenceflows
		parameters.clear();
		for (FlowElement flow : deleteEdges) {
			parameters.put("id", flow.getId());
			applyRule(graph, RULEFILE, "deleteSequenceFlow", parameters);
		}
		// remove flownodes
		parameters.clear();
		for (FlowElement node : deleteNodes) {
			parameters.put("id", node.getId());
			applyRule(graph, RULEFILE, "deleteFlowNode", parameters);
		}
	}

	public void replaceFragmentByCallActivity(Resource resource,
			Fragment fragment, String name, CallableElement calledElement)
			throws Exception {
		Pair<Float, Float> coords = CoordinateCalculator.getCoords(fragment);
		EGraph graph = new EGraphImpl(resource);

		Set<String> deleteNodeIds = new HashSet<String>();
		Set<String> deleteEdgeIds = new HashSet<String>();
		for (FlowElement e : FragmentUtil.getFlowElements(fragment, f -> true)) {
			if (e instanceof FlowNode) {
				deleteNodeIds.add(e.getId());
			} else if (e instanceof SequenceFlow) {
				deleteEdgeIds.add(e.getId());
			}
		}

		Map<String, Object> parameters = new HashMap<String, Object>();

		// create call activity
		parameters.clear();
		// TODO: use object instead of ID
		String callActivityUuid = EcoreUtil.generateUUID();
		parameters.put("id", callActivityUuid);
		parameters.put("name", name);
		parameters.put("x", coords.getValue0());
		parameters.put("y", coords.getValue1());
		parameters.put("calledElement", calledElement);
		applyRule(graph, RULEFILE, "createCallActivity", parameters);

		// modify entry's target ref
		parameters.clear();
		parameters.put("id", fragment.getEntry().getId());
		parameters.put("targetId", callActivityUuid);
		applyRule(graph, RULEFILE, "setTargetRef", parameters);

		// modify exit's source ref
		parameters.clear();
		parameters.put("id", fragment.getExit().getId());
		parameters.put("sourceId", callActivityUuid);
		applyRule(graph, RULEFILE, "setSourceRef", parameters);

		// delete sequenceflows
		parameters.clear();
		for (String edgeId : deleteEdgeIds) {
			parameters.put("id", edgeId);
			applyRule(graph, RULEFILE, "deleteSequenceFlow", parameters);
		}
		// delete flownodes
		parameters.clear();
		for (String nodeId : deleteNodeIds) {
			parameters.put("id", nodeId);
			applyRule(graph, RULEFILE, "deleteFlowNode", parameters);
		}

	}
}
