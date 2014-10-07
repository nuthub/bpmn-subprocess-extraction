package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.pst.Fragment;

public class ProcessMatching {
	private Process process1;
	private Process process2;
	private Set<Pair<FlowNode, FlowNode>> nodeCorrespondences;
	private Set<Pair<Fragment, Fragment>> fragmentCorrespondences;

	public Process getProcess1() {
		return process1;
	}

	public void setProcess1(Process process1) {
		this.process1 = process1;
	}

	public Process getProcess2() {
		return process2;
	}

	public void setProcess2(Process process2) {
		this.process2 = process2;
	}

	public Set<Pair<FlowNode, FlowNode>> getNodeCorrespondences() {
		return nodeCorrespondences;
	}

	public void setNodeCorrespondences(
			Set<Pair<FlowNode, FlowNode>> nodeCorrespondences) {
		this.nodeCorrespondences = nodeCorrespondences;
	}

	public void setFragmentCorrespondences(
			Set<Pair<Fragment, Fragment>> fragmentCorrespondences) {
		this.fragmentCorrespondences = fragmentCorrespondences;
	}

	public Set<Pair<Fragment, Fragment>> getFragmentCorrespondences() {
		return fragmentCorrespondences;
	}

	public ProcessMatching clone() {
		ProcessMatching clone = new ProcessMatching();
		clone.setProcess1(process1);
		clone.setProcess2(process2);
		clone.setNodeCorrespondences(new HashSet<Pair<FlowNode, FlowNode>>(
				nodeCorrespondences));
		clone.setFragmentCorrespondences(new HashSet<Pair<Fragment, Fragment>>(
				fragmentCorrespondences));
		return clone;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ProcessMatching [");
		sb.append(System.getProperty("line.separator"));
		sb.append(" process1=" + process1.getName() + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append(" process2=" + process2.getName() + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append(" nodeCorrespondences=" + nodeCorrespondences + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append(" fragmentCorrespondences=" + fragmentCorrespondences);
		sb.append(System.getProperty("line.separator"));
		sb.append("]");
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
}
