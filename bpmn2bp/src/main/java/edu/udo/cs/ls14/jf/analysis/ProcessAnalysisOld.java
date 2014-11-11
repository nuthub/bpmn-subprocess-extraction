package edu.udo.cs.ls14.jf.analysis;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.analysis.pst.PST;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

@XmlRootElement
@Deprecated
public class ProcessAnalysisOld implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1580226751012270993L;
	private Resource resource;
	private Process process;
	private PetriNetHLAPI ptnet;
	private ReachabilityGraph reachabilityGraph;
	private EList<Trace> traces;
	private BehavioralProfile behavioralProfile;
	private ConditionalProfile conditionalProfile;
	private PST pst;

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Resource getResource() {
		return resource;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Process getProcess() {
		return process;
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

	public EList<Trace> getTraces() {
		return traces;
	}

	public void setTraces(EList<Trace> traces) {
		this.traces = traces;
	}

	public BehavioralProfile getBehavioralProfile() {
		return behavioralProfile;
	}

	public void setBehavioralProfile(BehavioralProfile behavioralProfile) {
		this.behavioralProfile = behavioralProfile;
	}

	public ConditionalProfile getConditionalProfile() {
		return conditionalProfile;
	}

	public void setConditionalProfile(ConditionalProfile conditionalProfile) {
		this.conditionalProfile = conditionalProfile;
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
		sb.append("  resource=" + resource + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append("  ptnet=" + ptnet + ", ");
		sb.append(System.getProperty("line.separator"));
		sb.append("  reachabilityGraph=" + reachabilityGraph + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append("  traces=" + traces + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append("  behavioralProfile=" + behavioralProfile + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append("  conditionalProfile=" + conditionalProfile + ",");
		sb.append(System.getProperty("line.separator"));
		sb.append("  pst=" + pst + "");
		sb.append(System.getProperty("line.separator"));
		sb.append("]");
		return sb.toString();
	}
}
