package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.pst.Fragment;

public class ProcessMatching {

	private ProcessAnalysis analysis1;
	private ProcessAnalysis analysis2;
	private Set<Pair<FlowNode, FlowNode>> nodeCorrespondences;
	private Set<Pair<Fragment, Fragment>> fragmentCorrespondences;

	public ProcessAnalysis getAnalysis1() {
		return analysis1;
	}

	public void setAnalysis1(ProcessAnalysis analysis1) {
		this.analysis1 = analysis1;
	}

	public ProcessAnalysis getAnalysis2() {
		return analysis2;
	}

	public void setAnalysis2(ProcessAnalysis analysis2) {
		this.analysis2 = analysis2;
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
		clone.setAnalysis1(analysis1);
		clone.setAnalysis2(analysis2);
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
		sb.append(" analysis1=" + analysis1 + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append(" analysis2=" + analysis2 + ", ");
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
