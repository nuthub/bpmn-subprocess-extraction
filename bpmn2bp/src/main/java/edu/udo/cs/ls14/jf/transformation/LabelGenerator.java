package edu.udo.cs.ls14.jf.transformation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;

import edu.udo.cs.ls14.jf.analysis.pst.FragmentOld;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;

public class LabelGenerator {

	/**
	 * TODO: respect flow relation order / Use tracer?
	 * 
	 * @param fragment
	 * @return
	 * @throws Exception
	 */

	public static String getLabel(FragmentOld fragment) throws Exception {
		// TODO respect flow relation order of nodes
		Process process = ProcessLoader.getProcessFromResource(fragment
				.getResource());

		List<String> names = getNamesAcc(process, fragment,
				fragment.getEntry(), new ArrayList<String>(),
				new HashSet<FlowNode>());
		return StringUtils.join(names.toArray(), ", ");
	}

	private static List<String> getNamesAcc(Process process, FragmentOld fragment,
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
			getNamesAcc(process, fragment, flow, names, visited);
		}
		return names;
	}

}
