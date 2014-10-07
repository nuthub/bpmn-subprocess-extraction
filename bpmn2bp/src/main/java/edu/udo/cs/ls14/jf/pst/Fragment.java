package edu.udo.cs.ls14.jf.pst;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;

public class Fragment {

	private Process process;
	private SequenceFlow entry;
	private SequenceFlow exit;
	private Fragment parent;

	public Fragment(Process process, SequenceFlow entry, SequenceFlow exit) {
		this.process = process;
		this.entry = entry;
		this.exit = exit;
	}

	public Process getProcess() {
		return process;
	}

	public SequenceFlow getEntry() {
		return entry;
	}

	public SequenceFlow getExit() {
		return exit;
	}

	public boolean contains(Fragment fragment) {
		return getContainedFlowNodes(
				n -> n instanceof Event || n instanceof Activity).containsAll(
				fragment.getContainedFlowNodes(n -> n instanceof Event
						|| n instanceof Activity));
	}

	public Set<FlowNode> getContainedFlowNodes(Predicate<FlowNode> filter) {
		return getContainedFlowNodesAcc(entry, new HashSet<FlowNode>(), filter);
	}

	private Set<FlowNode> getContainedFlowNodesAcc(SequenceFlow entry,
			Set<FlowNode> nodes, Predicate<FlowNode> filter) {
		FlowNode target = entry.getTargetRef();
		if (nodes.contains(target) || entry.equals(exit)) {
			return nodes;
		}
		if (filter.test(target)) {
			nodes.add(target);
		}
		for (SequenceFlow newEntry : target.getOutgoing()) {
			nodes.addAll(getContainedFlowNodesAcc(newEntry, nodes, filter));
		}
		return nodes;
	}

	public void setParent(Fragment parent) {
		this.parent = parent;
	}

	public Fragment getParent() {
		return parent;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Fragment (");
		if (entry == null) {
			sb.append("null");
		} else {
			sb.append(entry.getName());
		}
		sb.append(", ");
		if (exit == null) {
			sb.append("null");
		} else {
			sb.append(exit.getName());
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((entry == null) ? 0 : entry.hashCode());
		result = prime * result + ((exit == null) ? 0 : exit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fragment other = (Fragment) obj;
		if (entry == null) {
			if (other.entry != null)
				return false;
		} else if (!entry.equals(other.entry))
			return false;
		if (exit == null) {
			if (other.exit != null)
				return false;
		} else if (!exit.equals(other.exit))
			return false;
		return true;
	}

}
