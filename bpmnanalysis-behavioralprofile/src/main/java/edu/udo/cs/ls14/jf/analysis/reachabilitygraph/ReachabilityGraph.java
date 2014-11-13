package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import fr.lip6.move.pnml.framework.hlapi.HLAPIRootClass;
import fr.lip6.move.pnml.framework.utils.PNMLUtils;
import fr.lip6.move.pnml.ptnet.Arc;
import fr.lip6.move.pnml.ptnet.Page;
import fr.lip6.move.pnml.ptnet.PetriNet;
import fr.lip6.move.pnml.ptnet.Place;
import fr.lip6.move.pnml.ptnet.PnObject;
import fr.lip6.move.pnml.ptnet.Transition;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI;

public class ReachabilityGraph extends DirectedSparseMultigraph<Marking, Edge> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4381590121431152940L;
	private static final Logger LOG = LoggerFactory
			.getLogger(ReachabilityGraph.class);


	public void createFromPnml(File file) throws Exception {
		HLAPIRootClass rc = PNMLUtils.importPnmlDocument(file, false);
		if (!PNMLUtils.isPTNetDocument(rc)) {
			String msg = "only fr.lip6.move.pnml.ptnet.hlapi.PetriNetDocHLAPI type pnml files implemented";
			LOG.error(msg);
			throw new NotImplementedException(msg);
		}
		PetriNetDocHLAPI ptDoc = (PetriNetDocHLAPI) rc;

		List<PetriNet> nets = ptDoc.getNets();
		if (nets.size() != 1) {
			String msg = "only pnml files with one single net supported";
			LOG.error(msg);
			throw new NotImplementedException(msg);
		}
		createFromPTNet(nets.get(0));

	}

	public void createFromPTNet(PetriNet ptnet) throws Exception {
		List<Page> pages = ptnet.getPages();
		if (pages.size() != 1) {
			String msg = "only nets with one single page supported";
			LOG.error(msg);
			throw new NotImplementedException(msg);
		}
		Page page = pages.get(0);
		// Algo
		Map<String, Transition> transitions = new HashMap<String, Transition>();
		Map<String, Place> places = new HashMap<String, Place>();
		Map<String, Arc> arcs = new HashMap<String, Arc>();
		Marking m0 = new Marking();
		m0.setInitialMarking(true);
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
		createFromTPFM(transitions, places, arcs, m0);

	}

	public void createFromTPFM(Map<String, Transition> transitions,
			Map<String, Place> places, Map<String, Arc> arcs,
			Marking initialMarking) throws Exception {
		// V := {}
		// E := {}
		// pending = {m0}
		Set<Marking> pending = new LinkedHashSet<Marking>();
		Set<Marking> visited = new HashSet<Marking>();
		pending.add(initialMarking);
		// while pending is not empty
		while (!pending.isEmpty()) {
			// choose m from pending
			Marking m = pending.iterator().next();
			// pending := pending \ {m}
			pending.remove(m);
			visited.add(m);
			// foreach transition t activated in m do
			// TODO: Check, ob durch das feuern einer Transition eine andere
			// deaktiviert werden könnte (Stellen-Kapazitäten beachten?)
			for (Transition t : getActiveTransitions(m, transitions)) {
				// calculate m' such that m \xrightarrow{t} m'
				Marking mPrime = getMPrime(m, t);
				// V := V \cup {m, m'}
				// E := E \cup {(m, t, m')}
				addEdge(new Edge(t.getId()), m, mPrime);
				// pending := pending \cup {m'}
				if (!visited.contains(mPrime)) {
					pending.add(mPrime);
				}
			}
		}
	}

	/*
	 * public File saveGraphML(String filename) throws IOException { // Save as
	 * GraphML GraphMLWriter<Marking, Edge> gw = new GraphMLWriter<Marking,
	 * Edge>(); gw.addEdgeData("label", "The edge's label", "", new
	 * Transformer<Edge, String>() { public String transform(Edge e) { return
	 * e.getT(); } }); gw.addVertexData("initial",
	 * "true, if marking is initial", "false", new Transformer<Marking,
	 * String>() { public String transform(Marking m) { return
	 * m.isInitialMarking() ? "true" : "false"; } }); File file = new
	 * File(filename); gw.save(this, new FileWriter(file)); return file; }
	 */

	private Marking getMPrime(Marking m, Transition t) throws Exception {
		Marking mPrime = new Marking();
		mPrime.addAll(m);
		// TODO: Prüfen, ob t überhaupt aktiviert ist
		// 1. Vorbereich aus Markierung entfernen
		for (Arc arc : t.getInArcs()) {
			if (!(arc.getSource() instanceof Place)) {
				String msg = "Transition's inArc has not a Place as source!";
				LOG.error(msg);
				throw new Exception(msg);
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
				String msg = "Transition's outArc has not a Place as target!";
				LOG.error(msg);
				throw new Exception(msg);
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
					String msg = "Transition's inArc has not a Place as source!";
					LOG.error(msg);
					throw new Exception(msg);
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

	public String toDot() {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph {");
		sb.append(System.getProperty("line.separator"));

		for (Edge edge : getEdges()) {
			sb.append("\"" + getSource(edge).getLabel() + "\"");
			sb.append(" -> ");
			sb.append("\"" + getDest(edge).getLabel() + "\"");
			sb.append(" [label=\"" + edge.getT() + "\"]");
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("}");
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

}
