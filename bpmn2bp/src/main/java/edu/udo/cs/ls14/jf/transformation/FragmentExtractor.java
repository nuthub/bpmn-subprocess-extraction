package edu.udo.cs.ls14.jf.transformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.pst.Fragment;

public class FragmentExtractor extends HenshinTransformation {

	private static final String RULEFILE = "bpmnModifier";
	private static final String RULEPATH = "src/main/resources/edu/udo/cs/ls14/jf/henshin/";

	@Override
	protected String getRulePath() {
		return RULEPATH;
	}

	public void createProcessFromFragment(Resource newResource,
			Fragment fragment) throws Exception {
		Pair<Float, Float> startEventCoords = CoordinateCalculator.getCoords(
				newResource, fragment.getEntry().getSourceRef());
		Pair<Float, Float> endEventCoords = CoordinateCalculator.getCoords(
				newResource, fragment.getExit().getTargetRef());
		Set<String> nodeIds = new HashSet<String>();
		Set<String> edgeIds = new HashSet<String>();
		for (FlowElement e : fragment.getProcess().getFlowElements()) {
			// Wenn FlowElement nicht in Fragment enthalten:
			if (!fragment.getContainedFlowElements(n -> true).contains(e)) {
				if (e instanceof SequenceFlow && !e.equals(fragment.getEntry())
						&& !e.equals(fragment.getExit())) {
					edgeIds.add(e.getId());
				} else if (e instanceof FlowNode) {
					nodeIds.add(e.getId());
				}
			}
		}
		EGraph graph = new EGraphImpl(newResource);
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
		parameters.put("newName",  "");
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
		parameters.put("newName",  "");
		applyRule(graph, RULEFILE, "setName", parameters);
		// remove sequenceflows
		parameters.clear();
		for (String id : edgeIds) {
			parameters.put("id", id);
			applyRule(graph, RULEFILE, "deleteSequenceFlow", parameters);
		}
		// remove flownodes
		parameters.clear();
		for (String id : nodeIds) {
			parameters.put("id", id);
			applyRule(graph, RULEFILE, "deleteFlowNode", parameters);
		}
	}

	public void replaceFragmentByCallActivity(Resource resource,
			Fragment fragment, String name, CallableElement calledElement)
			throws Exception {
		Pair<Float, Float> coords = CoordinateCalculator.getCoords(resource,
				fragment);
		EGraph graph = new EGraphImpl(resource);

		Set<String> nodeIds = new HashSet<String>();
		Set<String> edgeIds = new HashSet<String>();
		for (FlowElement e : fragment.getContainedFlowElements(f -> true)) {
			if (e instanceof FlowNode) {
				nodeIds.add(e.getId());
			} else if (e instanceof SequenceFlow) {
				edgeIds.add(e.getId());
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
		for (String edgeId : edgeIds) {
			parameters.put("id", edgeId);
			applyRule(graph, RULEFILE, "deleteSequenceFlow", parameters);
		}
		// delete flownodes
		parameters.clear();
		for (String nodeId : nodeIds) {
			parameters.put("id", nodeId);
			applyRule(graph, RULEFILE, "deleteFlowNode", parameters);
		}

	}

}
