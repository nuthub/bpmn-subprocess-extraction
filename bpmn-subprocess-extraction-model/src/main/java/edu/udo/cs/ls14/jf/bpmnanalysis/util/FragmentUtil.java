package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

/**
 * Helper methods for Fragments.
 * 
 * TODO: Move to generated model implementations.
 * 
 * @author Julian Flake
 *
 */
public class FragmentUtil {

	/**
	 * Get containing Definitions object.
	 * 
	 * @param fragment
	 *            the containing definitions should be searched for
	 * @return containing Definitions object
	 */
	public static Definitions getDefinitions(Fragment fragment) {
		return (Definitions) getContainer(fragment,
				Bpmn2Package.eINSTANCE.getDefinitions());
	}

	/**
	 * Get a fragments closest container of given type.
	 * 
	 * @param fragment
	 *            the contained fragment
	 * @param eClass
	 *            given type
	 * @return closest container of given type, null if no container of given
	 *         type found
	 */
	public static EObject getContainer(Fragment fragment, EClass eClass) {
		EObject eObject = fragment.getEntry();
		for (EObject parent = eObject; parent != null; parent = parent
				.eContainer()) {
			if (eClass.isInstance(parent)) {
				return parent;
			}
		}
		return null;
	}

	/**
	 * Checks if a fragment is contained in another fragment.
	 * 
	 * @param fragment1
	 *            possibly containing fragment
	 * @param fragment2
	 *            possibly contained fragment
	 * @return true, if fragment1 contains fragment2
	 */
	public static boolean contains(Fragment fragment1, Fragment fragment2) {
		return getFlowElements(fragment1,
				e -> e instanceof Event || e instanceof Activity).containsAll(
				getFlowElements(fragment2, n -> n instanceof Event
						|| n instanceof Activity));
	}

	/**
	 * Checks if a given FlowElement is contained in given Fragment.
	 * 
	 * @param fragment
	 *            given Fragment
	 * @param flowElement
	 *            FlowElement to check for containment
	 * 
	 * @return true if given FlowElement is contained in given Fragment
	 */
	public static boolean contains(Fragment fragment, FlowElement flowElement) {
		return getFlowElements(fragment, n -> true).contains(flowElement);
	}

	/**
	 * Returns all SequenceFlows inside a fragment.
	 * 
	 * @param fragment
	 *            given Fragment
	 * @return Set of SequenceFlows contained in Fragment
	 */
	public static Set<SequenceFlow> getEdges(Fragment fragment) {
		return FragmentUtil
				.getFlowElements(
						fragment,
						flow -> flow instanceof SequenceFlow
								&& !flow.equals(fragment.getEntry())
								&& !flow.equals(fragment.getExit())).stream()
				.map(flow -> (SequenceFlow) flow).collect(Collectors.toSet());
	}

	/**
	 * Returns all Events and Activities contained in a fragment.
	 * 
	 * @param fragment
	 *            given Fragment
	 * @return Set of FlowNodes (Event / Activity) contained in the fragment
	 */
	public static Set<FlowNode> getEventsAndActivites(Fragment fragment) {
		return getFlowElements(fragment,
				e -> e instanceof Event || e instanceof Activity).stream()
				.map(e -> (FlowNode) e).collect(Collectors.toSet());

	}

	/**
	 * Returns all FlowElements contained in a fragment.
	 * 
	 * @param fragment
	 *            given Fragment
	 * @param filter
	 *            filter, provide n -> true, if no filter required
	 * @return Set of in given fragment contained FlowElements matching given
	 *         filter
	 */
	public static Set<FlowElement> getFlowElements(Fragment fragment,
			Predicate<FlowElement> filter) {
		return getContainedFlowElementsAcc(fragment, fragment.getEntry(),
				new HashSet<FlowElement>(), filter, new HashSet<SequenceFlow>());
	}

	private static Set<FlowElement> getContainedFlowElementsAcc(Fragment f,
			SequenceFlow entry, Set<FlowElement> elements,
			Predicate<FlowElement> filter, Set<SequenceFlow> visited) {
		FlowNode target = entry.getTargetRef();
		if (!entry.equals(f.getEntry()) && !entry.equals(f.getExit())
				&& filter.test(entry)) {
			elements.add(entry);
		}
		if (visited.contains(entry) || elements.contains(target)
				|| entry.equals(f.getExit())) {
			return elements;
		}
		visited.add(entry);
		if (filter.test(target)) {
			elements.add(target);
		}
		for (SequenceFlow newEntry : target.getOutgoing()) {
			elements.addAll(getContainedFlowElementsAcc(f, newEntry, elements,
					filter, visited));
		}
		return elements;
	}

	/**
	 * Return String representation of given fragment.
	 * 
	 * @param f
	 *            given Fragment
	 * @return String representation
	 */
	public static String toString(Fragment f) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(f.getEntry().getName() != null
				&& !f.getEntry().getName().equals("") ? f.getEntry().getName()
				: "[" + f.getEntry().getId() + "]");
		sb.append(",");
		sb.append(f.getExit().getName() != null
				&& !f.getExit().getName().equals("") ? f.getExit().getName()
				: "[" + f.getExit().getId() + "]");
		sb.append(")");
		return sb.toString();
	}

}
