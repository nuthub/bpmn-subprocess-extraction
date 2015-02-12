package edu.udo.cs.ls14.jf.bpmntransformation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;

import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

/**
 * Generates labels of Fragments.
 * 
 * @author Julian Flake
 *
 */
public class LabelGenerator {

	/**
	 * Concatenates all labels of activites and events contained in a given
	 * fragment.
	 * 
	 * @param fragment
	 *            given Fragment
	 * @return String with joined labels separated by comma
	 */
	public static String getLabel(Fragment fragment) {
		// TODO respect flow relation order of nodes
		List<String> names = getNamesAcc(fragment, fragment.getEntry(),
				new ArrayList<String>(), new HashSet<FlowNode>());
		return StringUtils.join(names.toArray(), ", ");
	}

	private static List<String> getNamesAcc(Fragment fragment,
			SequenceFlow entry, ArrayList<String> names,
			HashSet<FlowNode> visited) {
		if (entry.equals(fragment.getExit())
				|| visited.contains(entry.getTargetRef())) {
			return names;
		}
		// Wenn Acitivity / Event / nicht-leer
		visited.add(entry.getTargetRef());
		if ((entry.getTargetRef() instanceof Activity || entry.getTargetRef() instanceof Event)
				&& entry.getTargetRef().getName() != null
				&& !entry.getTargetRef().getName().equals("")) {
			names.add(entry.getTargetRef().getName());
		}
		for (SequenceFlow flow : entry.getTargetRef().getOutgoing()) {
			getNamesAcc(fragment, flow, names, visited);
		}
		return names;
	}

}
