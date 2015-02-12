package edu.udo.cs.ls14.jf.bpmntransformation;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.dd.dc.Point;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;

/**
 * Calls Henshin rules.
 * 
 * @author Julian Flake
 *
 */
public class HenshinAdapter {

	private static final Logger LOG = LoggerFactory
			.getLogger(HenshinAdapter.class);

	private static final String RESOURCEPATH = "henshin";
	private static final String RULEFILE = "bpmnModifier";

	private Engine engine = null;
	private Module module = null;

	private void init() throws Exception {
		Registries.registerAll();
		engine = new EngineImpl();
		HenshinResourceSet resourceSet = new HenshinResourceSet();
		resourceSet.registerXMIResourceFactories("bpmn");
		resourceSet.getPackageRegistry().put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("bpmn", Registries.getResourceFactory("bpmn"));
		// Load rule, build rule URI to avoid henshin's createFileURI
		URL ruleUrl = Thread.currentThread().getContextClassLoader()
				.getResource(RESOURCEPATH + "/" + RULEFILE + ".henshin");
		// URL ruleUrl = getClass().getResource(
		// "/" + RESOURCEPATH + "/" + RULEFILE + ".henshin");
		URI uri = URI.createURI(ruleUrl.toExternalForm());
		module = resourceSet.getModule(uri, true);
	}

	/**
	 * Create a new process model from given fragment.
	 * 
	 * @param graph
	 *            outside created EGraph of the Definitions object
	 * @param fragment
	 *            fragment to extract
	 * @param template
	 *            Definitions to use as template
	 * @throws Exception
	 *             if a rule could not be applied or any other error occurs
	 */
	public void cropFragment(EGraph graph, Fragment fragment,
			Definitions template) throws Exception {
		Process process = DefinitionsUtil.getProcess(template);
		Point startEventCoords = CoordinateCalculator.getCenter(fragment
				.getEntry().getSourceRef(), template);
		Point endEventCoords = CoordinateCalculator.getCenter(fragment
				.getExit().getTargetRef(), template);

		Set<String> preserveElementIds = FragmentUtil
				.getFlowElements(fragment, e -> true).stream()
				.map(e -> e.getId()).collect(Collectors.toSet());
		preserveElementIds.add(fragment.getEntry().getId());
		preserveElementIds.add(fragment.getExit().getId());
		Set<FlowElement> deleteElements = process.getFlowElements().stream()
				.filter(e -> !preserveElementIds.contains(e.getId()))
				.collect(Collectors.toSet());
		Set<String> deleteNodeIds = deleteElements.stream()
				.filter(e -> e instanceof FlowNode).map(e -> e.getId())
				.collect(Collectors.toSet());
		Set<String> deleteEdgeIds = deleteElements.stream()
				.filter(e -> e instanceof SequenceFlow).map(e -> e.getId())
				.collect(Collectors.toSet());

		Map<String, Object> parameters = new HashMap<String, Object>();
		// create startevent
		String startEventId = UUID.randomUUID().toString();
		parameters.clear();
		parameters.put("id", startEventId);
		parameters.put("name", "Start");
		parameters.put("shapeId", startEventId + "_gui");
		parameters.put("x", startEventCoords.getX());
		parameters.put("y", startEventCoords.getY());
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
		String endEventId = UUID.randomUUID().toString();
		parameters.clear();
		parameters.put("id", endEventId);
		parameters.put("name", "End");
		parameters.put("shapeId", endEventId + "_gui");
		parameters.put("x", endEventCoords.getX());
		parameters.put("y", endEventCoords.getY());
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
		for (String id : deleteEdgeIds) {
			parameters.put("id", id);
			applyRule(graph, RULEFILE, "deleteSequenceFlow", parameters);
		}
		// remove flownodes
		parameters.clear();
		for (String id : deleteNodeIds) {
			parameters.put("id", id);
			applyRule(graph, RULEFILE, "deleteFlowNode", parameters);
		}
	}

	/**
	 * Replaces a Fragment by a call activity.
	 * 
	 * @param graph
	 *            outside created and reusable EGraph of the Definitions object
	 * @param fragment
	 *            fragment to replace
	 * @param calledElement
	 *            Reference to called element (extracted process model)
	 * @param name
	 *            Name of the new call activity
	 * @throws Exception
	 *             if a rule could not be applied or any other error occurs
	 */
	public void replaceFragmentByCallActivity(EGraph graph, Fragment fragment,
			CallableElement calledElement, String name) throws Exception {
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
		String callActivityUuid = "_" + UUID.randomUUID().toString();
		parameters.put("id", callActivityUuid);
		parameters.put("name", name);
		parameters.put("x", fragment.getCenter().getX());
		parameters.put("y", fragment.getCenter().getY());
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

	private void applyRule(EGraph graph, String rulefileBaseName,
			String ruleName, Map<String, Object> parameters) throws Exception {
		if (module == null || engine == null) {
			init();
		}
		Unit unit = module.getUnit(ruleName);
		if (unit == null) {
			throw new Exception("Could not get Unit: " + rulefileBaseName
					+ " / " + ruleName);
		}
		UnitApplication app = new UnitApplicationImpl(engine, graph, unit, null);
		// Basically a copy of parameters :( no better way with ruleapplication
		// api)
		for (Map.Entry<String, Object> p : parameters.entrySet()) {
			app.setParameterValue(p.getKey(), p.getValue());
		}
		if (!app.execute(new HenshinApplicationMonitor())) {
			LOG.error("Rule execution failed: " + ruleName + " / " + parameters);
			throw new Exception("Could not apply rule: " + rulefileBaseName
					+ "->" + ruleName + " with " + parameters);
		}
	}
}
