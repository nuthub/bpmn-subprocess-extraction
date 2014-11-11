package edu.udo.cs.ls14.jf.bpmn.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;

import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

public class FragmentUtil {

	public static boolean contains(Fragment fragment1, Fragment fragment2) {
		return getFlowElements(fragment1,
				e -> e instanceof Event || e instanceof Activity).containsAll(
				getFlowElements(fragment2, n -> n instanceof Event
						|| n instanceof Activity));
	}


	// Just a convenience method
	public static Set<SequenceFlow> getEdges(Fragment fragment) {
		return FragmentUtil
				.getFlowElements(
						fragment,
						flow -> flow instanceof SequenceFlow
								&& !flow.equals(fragment.getEntry())
								&& !flow.equals(fragment.getExit())).stream()
				.map(flow -> (SequenceFlow) flow).collect(Collectors.toSet());
	}
	// Just a convenience method
	public static Set<FlowNode> getEventsAndActivites(Fragment fragment) {
		return getFlowElements(fragment,
				e -> e instanceof Event || e instanceof Activity).stream()
				.map(e -> (FlowNode) e).collect(Collectors.toSet());

	}

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

}
