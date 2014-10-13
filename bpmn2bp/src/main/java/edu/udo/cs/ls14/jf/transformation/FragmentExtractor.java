package edu.udo.cs.ls14.jf.transformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.pst.Fragment;

public class FragmentExtractor {

	private Engine engine = null;

	private static final String RULEFILE = "sequenceRule";
	private static final String RULEPATH = "src/test/resources/edu/udo/cs/ls14/jf/henshin/";

	private void init() {
		engine = new EngineImpl();
	}

	public void createProcessFromFragment(EGraph graph, Fragment fragment,
			String startEventName, Pair<Float, Float> startEventCoords,
			String endEventName, Pair<Float, Float> endEventCoords)
			throws Exception {
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
		Map<String, Object> parameters = new HashMap<String, Object>();
		// create startevent
		String startEventId = EcoreUtil.generateUUID();
		parameters.clear();
		parameters.put("id", startEventId);
		parameters.put("name", startEventName);
		parameters.put("shapeId", startEventId + "_gui");
		parameters.put("x", startEventCoords.getValue0());
		parameters.put("y", startEventCoords.getValue1());
		applyRule(graph, RULEFILE, "createStartEvent", parameters);
		// set sourceRef of entry edge
		parameters.clear();
		parameters.put("id", fragment.getEntry().getId());
		parameters.put("sourceId", startEventId);
		applyRule(graph, RULEFILE, "setSourceRef", parameters);
		// create endevent
		String endEventId = EcoreUtil.generateUUID();
		parameters.clear();
		parameters.put("id", endEventId);
		parameters.put("name", endEventName);
		parameters.put("shapeId", endEventId + "_gui");
		parameters.put("x", endEventCoords.getValue0());
		parameters.put("y", endEventCoords.getValue1());
		applyRule(graph, RULEFILE, "createEndEvent", parameters);
		// set targetRef of exit edge
		parameters.clear();
		parameters.put("id", fragment.getExit().getId());
		parameters.put("targetId", endEventId);
		applyRule(graph, RULEFILE, "setTargetRef", parameters);
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

	public void replaceFragmentByCallActivity(EGraph graph, Fragment fragment,
			String name, Pair<Float, Float> coords)
			throws Exception {

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
		String callActivityUuid = EcoreUtil.generateUUID();
		parameters.put("id", callActivityUuid);
		parameters.put("name", name);
		parameters.put("shapeId", callActivityUuid + "_gui");
		parameters.put("x", coords.getValue0());
		parameters.put("y", coords.getValue1());
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

	private void applyRule(EGraph graph, String rulefileBaseName,
			String ruleName, Map<String, Object> parameters) throws Exception {
		if (engine == null) {
			init();
		}
		// Load rule
		HenshinResourceSet resourceSet = new HenshinResourceSet(RULEPATH);
		resourceSet.registerXMIResourceFactories("bpmn2");
		resourceSet.getPackageRegistry().put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("bpmn2", new Bpmn2ResourceFactoryImpl());
		Module module = resourceSet.getModule(rulefileBaseName + ".henshin");
		Unit unit = module.getUnit(ruleName);
		if (unit == null) {
			throw new Exception("Could not get Unit: " + rulefileBaseName
					+ " / " + ruleName);
		}
		UnitApplication app = new UnitApplicationImpl(engine, graph, unit, null);
		for (Map.Entry<String, Object> p : parameters.entrySet()) {
			app.setParameterValue(p.getKey(), p.getValue());
		}
		// app.execute(new LoggingApplicationMonitor())
		if (!app.execute(null)) {
			throw new Exception("Could not apply rule: " + rulefileBaseName
					+ " / " + ruleName + " / " + parameters);
		}
	}
}
