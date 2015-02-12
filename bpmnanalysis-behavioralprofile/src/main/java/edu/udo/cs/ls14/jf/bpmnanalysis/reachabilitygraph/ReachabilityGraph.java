package edu.udo.cs.ls14.jf.bpmnanalysis.reachabilitygraph;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;
import fr.lip6.move.pnml.framework.utils.PNMLUtils;
import fr.lip6.move.pnml.ptnet.Arc;
import fr.lip6.move.pnml.ptnet.Page;
import fr.lip6.move.pnml.ptnet.PetriNet;
import fr.lip6.move.pnml.ptnet.Place;
import fr.lip6.move.pnml.ptnet.PnObject;
import fr.lip6.move.pnml.ptnet.Transition;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;

/**
 * A Reachability Graph of a Petri net.
 * 
 * @author Julian Flake
 *
 */
public class ReachabilityGraph extends DirectedSparseMultigraph<Marking, Edge> {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = 4381590121431152940L;

	/**
	 * Creates reachability graph from PNML file
	 * 
	 * @param file
	 *            PNML file
	 * @throws Exception
	 *             if an error occurs
	 */
	public void createFromPnml(File file) throws Exception {
		HLAPIRootClass rc = PNMLUtils.importPnmlDocument(file, false);
		if (!PNMLUtils.isPTNetDocument(rc)) {
			throw new NotImplementedException(
					"only fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI type pnml files implemented");
		}
		PetriNetDocHLAPI ptDoc = (PetriNetDocHLAPI) rc;

		List<PetriNet> nets = ptDoc.getNets();
		if (nets.size() != 1) {
			throw new NotImplementedException(
					"only pnml files with one single net supported");
		}
		createFromPTNet(nets.get(0));

	}

	/**
	 * Creates reachability graph from PetriNet object.
	 * 
	 * @param ptnet
	 *            PetriNet object
	 * @throws Exception
	 *             if an error occurs
	 */
	public void createFromPTNet(PetriNet ptnet) throws Exception {
		List<Page> pages = ptnet.getPages();
		if (pages.size() != 1) {
			throw new NotImplementedException(
					"only nets with one single page supported");
		}
		Page page = pages.get(0);
		// Algo
		Map<String, Transition> transitions = new HashMap<String, Transition>();
		Map<String, Place> places = new HashMap<String, Place>();
		Map<String, Arc> arcs = new HashMap<String, Arc>();
		Marking m0 = new Marking();
		for (PnObject obj : page.getObjects()) {
			if (obj instanceof Place) {
				Place p = (Place) obj;
				places.put(p.getId(), p);
				if (p.getInitialMarking() != null) {
					m0.add(p);
				}
			}
			if (obj instanceof Transition) {
				transitions.put(obj.getId(), (Transition) obj);
			}
			if (obj instanceof Arc) {
				arcs.put(obj.getId(), (Arc) obj);
			}
		}

		// V := {}
		// E := {}
		// pending = {m0}
		Set<Marking> pending = new LinkedHashSet<Marking>();
		Set<Marking> visited = new HashSet<Marking>();
		pending.add(m0);
		// while pending is not empty
		while (!pending.isEmpty()) {
			// choose m from pending
			Marking m = pending.iterator().next();
			// pending := pending \ {m}
			pending.remove(m);
			// if m not in V
			if (!visited.contains(m)) {
				visited.add(m);
				// V = V u m
				addVertex(m);
				// foreach transition t activated in m do
				for (Transition t : getActiveTransitions(m, transitions)) {
					// calculate m' such that m -t-> m'
					Marking mPrime = getMPrime(m, t);
					// E := E \cup {(m, t, m')}
					addEdge(new Edge(t.getId()), m, mPrime);
					// pending := pending \cup {m'}
					pending.add(mPrime);
				}
			}
		}
	}

	private Marking getMPrime(Marking m, Transition t) throws Exception {
		Marking mPrime = new Marking();
		mPrime.addAll(m);
		// 1. Vorbereich aus Markierung entfernen
		for (Arc arc : t.getInArcs()) {
			if (!(arc.getSource() instanceof Place)) {
				throw new Exception(
						"Transition's inArc has not a Place as source!");
			}
			Place p = (Place) arc.getSource();
			if (!m.contains(p)) {
				return m;
			}
			mPrime.remove(p);
		}
		// 2. Nachbereich in Markierung aufnehmen
		for (Arc arc : t.getOutArcs()) {
			if (!(arc.getTarget() instanceof Place)) {
				throw new Exception(
						"Transition's outArc has not a Place as target!");
			}
			mPrime.add((Place) arc.getTarget());
		}
		return mPrime;
	}

	private List<Transition> getActiveTransitions(Marking m,
			Map<String, Transition> transitions) throws Exception {
		List<Transition> activeTransitions = new ArrayList<Transition>();
		for (Transition t : transitions.values()) {
			boolean active = true;
			for (Arc arc : t.getInArcs()) {
				if (!(arc.getSource() instanceof Place)) {
					throw new Exception(
							"Transition's inArc has not a Place as source!");
				}
				Place p = (Place) arc.getSource();
				if (!m.contains(p)) {
					active = false;
				}
			}
			if (active) {
				activeTransitions.add(t);
			}
		}
		return activeTransitions;

	}

	/**
	 * Returns a DOT representation of this reachability graph
	 * 
	 * @param process
	 *            Process of this reachability graph (to remap edges to
	 *            FlowNodes)
	 * @return DOT representation
	 */
	public String toDot(Process process) {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		sb.append("digraph {" + nl);
		sb.append("margin=0;");
		sb.append("pad=0;");
		sb.append("node[shape=oval, fixedsize=false; fontsize=24];" + nl);
		sb.append("edge[fontsize=24];" + nl);
		for (Marking marking : getVertices()) {
			if (marking.iterator().next().getInitialMarking() != null) {
				sb.append("\"" + marking.getId() + "\" [label=\""
						+ (marking.getLabel()) + "\"];");
				sb.append(nl);
			}
		}
		for (Marking marking : getVertices()) {
			if (marking.iterator().next().getInitialMarking() == null) {
				sb.append("\"" + marking.getId() + "\" [label=\""
						+ (marking.getLabel()) + "\"];");
				sb.append(nl);
			}
		}
		for (Edge edge : getEdges()) {
			sb.append("\"" + getSource(edge).getId() + "\"");
			sb.append(" -> ");
			sb.append("\"" + getDest(edge).getId() + "\"");
			FlowNode flowNode = DefinitionsUtil.getFlowNode(process,
					edge.getT());
			if (DefinitionsUtil.isAE(flowNode)) {
				sb.append(" [label=\"" + flowNode.getName() + "\"]");
			} else {
				sb.append(" [label=\"\"]");
			}
			sb.append(";");
			sb.append(nl);
		}
		sb.append("}");
		sb.append(nl);
		return sb.toString();
	}
}
