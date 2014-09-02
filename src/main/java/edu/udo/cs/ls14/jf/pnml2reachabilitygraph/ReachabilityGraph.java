package edu.udo.cs.ls14.jf.pnml2reachabilitygraph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.lang3.NotImplementedException;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.io.GraphMLWriter;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model.Edge;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model.Marking;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model.Trace;
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
		PetriNet net = nets.get(0);
		List<Page> pages = net.getPages();
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
			for (Transition t : getActiveTransitions(m, transitions)) {
				// calculate m' such that m \xrightarrow{t} m'
				Marking mPrime = getMPrime(m, t);
				// V := V \cup {m, m'}
				// E := E \cup {(m, t, m')}
				addEdge(new Edge(t.getName().getText()), m, mPrime);
				// pending := pending \cup {m'}
				if (!visited.contains(mPrime)) {
					pending.add(mPrime);
				}
			}
		}
	}

	public File saveGraphML(String filename) throws IOException {
		// Save as GraphML
		GraphMLWriter<Marking, Edge> gw = new GraphMLWriter<Marking, Edge>();
		gw.addEdgeData("label", "The edge's label", "",
				new Transformer<Edge, String>() {
					public String transform(Edge e) {
						return e.getT();
					}
				});
		gw.addVertexData("initial", "true, if marking is initial", "false",
				new Transformer<Marking, String>() {
					public String transform(Marking m) {
						return m.isInitialMarking() ? "true" : "false";
					}
				});
		File file = new File(filename);
		gw.save(this, new FileWriter(file));
		return file;

	}

	public Set<Trace> getTraces() throws Exception {
		Set<Marking> startNodes = new HashSet<Marking>();
		Set<Marking> endNodes = new HashSet<Marking>();
		for (Marking m : getVertices()) {
			if (inDegree(m) == 0) {
				startNodes.add(m);
			}
			if (outDegree(m) == 0) {
				endNodes.add(m);
			}
		}
		if (startNodes.size() != 1) {
			throw new Exception("expected exactly 1 start node, found "
					+ startNodes.size());
		}
		if (endNodes.size() != 1) {
			throw new Exception("expected exactly 1 end node, found "
					+ endNodes.size());
		}
		Marking start = startNodes.iterator().next();
		Marking end = endNodes.iterator().next();
		return getTraces(this, start, end, new Trace(),
				new HashMap<String, Integer>());
	}

	private Set<Trace> getTraces(ReachabilityGraph graph, Marking start,
			Marking end, Trace prefix, Map<String, Integer> visited) {
		Set<Trace> traces = new HashSet<Trace>();
		if (start == end) {
			traces.add(prefix);
			return traces;
		}
		for (Edge edge : graph.getOutEdges(start)) {
			// Wenn die Kante schon mehr als einmal durchlaufen wurde, ignoriere
			// die Kante
			if (!visited.containsKey(edge.getT())
					|| visited.get(edge.getT()) <= 1) {
				if (edge.getT() != null && !edge.getT().equals("")) {
					if(!visited.containsKey(edge.getT())) {
					visited.put(edge.getT(), 0);
					}
					// Erhöhe den Zähler der Kante um 1
					visited.put(edge.getT(), visited.get(edge.getT()) + 1);
				}
				// erstelle neuen Trace aus Prefix
				Trace trace = new Trace();
				trace.addAll(prefix);
				// Wenn kante keine stille Transition repräsentiert
				if (edge.getT() != null && !edge.getT().equals("")) {
					// Füge die Kante dem Trace hinzu
					trace.add(edge.getT());
				}
				traces.addAll(getTraces(graph, graph.getDest(edge), end, trace,
						visited));
			}
		}
		return traces;
	}

	private Marking getMPrime(Marking m, Transition t) throws Exception {
		Marking mPrime = new Marking();
		mPrime.addAll(m);
		// TODO: Prüfen, ob t überhaupt aktiviert ist
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

}
