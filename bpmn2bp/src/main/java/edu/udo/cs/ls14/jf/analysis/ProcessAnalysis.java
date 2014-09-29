package edu.udo.cs.ls14.jf.analysis;

import java.util.Set;

import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.behaviorprofile.BehavioralProfile;
import edu.udo.cs.ls14.jf.pst.PST;
import edu.udo.cs.ls14.jf.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.reachabilitygraph.Trace;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class ProcessAnalysis {

	private Process process;
	private PetriNetHLAPI ptnet;
	private ReachabilityGraph reachabilityGraph;
	private Set<Trace> traces;
	private BehavioralProfile behavioralProfile;
	private PST pst;

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public PetriNetHLAPI getPtnet() {
		return ptnet;
	}

	public void setPtnet(PetriNetHLAPI ptnet) {
		this.ptnet = ptnet;
	}

	public ReachabilityGraph getReachabilityGraph() {
		return reachabilityGraph;
	}

	public void setReachabilityGraph(ReachabilityGraph reachabilityGraph) {
		this.reachabilityGraph = reachabilityGraph;
	}

	public Set<Trace> getTraces() {
		return traces;
	}

	public void setTraces(Set<Trace> traces) {
		this.traces = traces;
	}

	public BehavioralProfile getBehavioralProfile() {
		return behavioralProfile;
	}

	public void setBehavioralProfile(BehavioralProfile behavioralProfile) {
		this.behavioralProfile = behavioralProfile;
	}

	public PST getPst() {
		return pst;
	}

	public void setPst(PST pst) {
		this.pst = pst;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ProcessAnalysis [");
		sb.append(System.getProperty("line.separator"));
		sb.append("  process=" + process + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append("  ptnet=" + ptnet + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append("  reachabilityGraph=" + reachabilityGraph + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append("  traces=" + traces + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append("  behavioralProfile=" + behavioralProfile + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append("  pst=" + pst + "");
		sb.append(System.getProperty("line.separator"));
		sb.append("]");
		return sb.toString();
	}
}
