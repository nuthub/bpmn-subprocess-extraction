package edu.udo.cs.ls14.jf.processmatching;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.pst.Fragment;

public class ProcessMatching {

	private Resource resource1;
	private Resource resource2;
	private Process process1;
	private Process process2;
	private ProcessAnalysis analysis1;
	private ProcessAnalysis analysis2;
	private Set<Pair<FlowNode, FlowNode>> nodeCorrespondences;
	private Set<Pair<Fragment, Fragment>> fragmentCorrespondences;

	public Resource getResource1() {
		return resource1;
	}

	public void setResource1(Resource resource1) {
		this.resource1 = resource1;
	}

	public Resource getResource2() {
		return resource2;
	}

	public void setResource2(Resource resource2) {
		this.resource2 = resource2;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public Process getProcess1() {
		return process1;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public void setProcess1(Process process1) {
		this.process1 = process1;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public Process getProcess2() {
		return process2;
	}

	/**
	 * @deprecated
	 * @return
	 */
	public void setProcess2(Process process2) {
		this.process2 = process2;
	}

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
		clone.setResource1(resource1);
		clone.setResource2(resource2);
		clone.setProcess1(process1);
		clone.setProcess2(process2);
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
		sb.append(" resource1=" + resource1.getURI() + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append(" resource2=" + resource2.getURI() + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append(" process1=" + process1.getName() + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append(" process2=" + process2.getName() + ", ");
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
