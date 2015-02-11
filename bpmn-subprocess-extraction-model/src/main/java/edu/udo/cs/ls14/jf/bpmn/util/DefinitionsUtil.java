package edu.udo.cs.ls14.jf.bpmn.util;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

/**
 * Helper methods for Definitions objects.
 * 
 * @author Julian Flake
 *
 */
public class DefinitionsUtil {

	/**
	 * Return first contained Diagram in Definitions object.
	 * 
	 * @param definitions
	 *            Definitions object
	 * @return first contained Diagram
	 * @throws Exception
	 *             is thrown if no or more than one diagram is contained in
	 *             Definitions object.
	 */
	public static BPMNDiagram getDiagram(Definitions definitions)
			throws Exception {
		List<BPMNDiagram> diagrams = definitions.getDiagrams();
		if (diagrams.size() < 1) {
			throw new Exception("Definitions object contains no Diagram");
		} else if (diagrams.size() > 1) {
			throw new Exception(
					"Definitions object contains more than one Diagram");
		}
		return diagrams.get(0);
	}

	/**
	 * Returns first Process in Definitions object.
	 * 
	 * @param definitions
	 *            Definitions object
	 * @return first contained Process
	 * @throws Exception
	 *             if no or more than one Process object is contained in
	 *             Definitions object.
	 */
	public static Process getProcess(Definitions definitions) throws Exception {
		List<Process> processes = definitions.getRootElements().stream()
				.filter(r -> r instanceof Process).map(p -> (Process) p)
				.collect(Collectors.toList());
		if (processes.size() < 1) {
			throw new Exception("Definitions object contains no Process");
		} else if (processes.size() > 1) {
			throw new Exception(
					"Definitions object contains more than one Process");
		}
		return processes.get(0);
	}

	/**
	 * Returns a processes FlowNode with given ID
	 * 
	 * @param process
	 *            Process with FlowNodes
	 * @param id
	 *            the id to search for
	 * @return FlowNode with given id, null if no FlowNode with given id is
	 *         found
	 */
	public static FlowNode getFlowNode(Process process, String id) {
		for (FlowElement e : process.getFlowElements()) {
			if (e instanceof FlowNode && e.getId().equals(id)) {
				return (FlowNode) e;
			}
		}
		return null;
	}

	/**
	 * Returns a processes SequenceFlow with given ID
	 * 
	 * @param process
	 *            Process with SequenceFlows
	 * @param id
	 *            the id to search for
	 * @return SequenceFlow with given id, null if no SequenceFlow with given id
	 *         is found
	 */
	public static SequenceFlow getSequenceFlow(Definitions definitions,
			String id) throws Exception {
		Process process = getProcess(definitions);
		for (FlowElement e : process.getFlowElements()) {
			if (e instanceof SequenceFlow && e.getId().equals(id)) {
				return (SequenceFlow) e;
			}
		}
		return null;
	}

	/**
	 * checks if node is an Activity or Event.
	 * 
	 * @param node
	 *            node to check
	 * @return true, if node is Activity or Event.
	 */
	public static boolean isAE(FlowNode node) {
		return (node instanceof Activity || node instanceof Event);
	}

	/**
	 * Returns a self-contained copy of the eObject with replaced IDs.
	 * 
	 * @param eObject
	 *            the object to copy.
	 * @return the copy.
	 * @see Copier
	 */
	public static <T extends EObject> T copy(T eObject) {
		Copier copier = new DefinitionsCopier();
		EObject result = copier.copy(eObject);
		copier.copyReferences();

		@SuppressWarnings("unchecked")
		T t = (T) result;
		return t;
	}
}
