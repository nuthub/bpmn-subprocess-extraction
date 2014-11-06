package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TracerOld {

	private static final Logger LOG = LoggerFactory.getLogger(TracerOld.class);

	public static Set<TraceOld> getTraces(Process process, ReachabilityGraph rg)
			throws Exception {
		Set<Marking> startNodes = new HashSet<Marking>();
		Set<Marking> endNodes = new HashSet<Marking>();
		for (Marking m : rg.getVertices()) {
			if (rg.inDegree(m) == 0) {
				startNodes.add(m);
			}
			if (rg.outDegree(m) == 0) {
				endNodes.add(m);
			}
		}
		if (startNodes.size() != 1) {
			LOG.error(startNodes.size()
					+ " start nodes found, only processes with exactly one start node supported!");
			throw new Exception("expected exactly 1 start node, found "
					+ startNodes.size());
		}
		if (endNodes.size() != 1) {
			LOG.error(endNodes.size()
					+ " end nodes found, only processes with exactly one end node supported!");
			throw new Exception("expected exactly 1 end node, found "
					+ endNodes.size());
		}
		Marking start = startNodes.iterator().next();
		Marking end = endNodes.iterator().next();
		return getTraces(process, rg, start, end, new TraceOld(),
				new HashSet<Edge>());
	}

	private static Set<TraceOld> getTraces(Process process,
			ReachabilityGraph graph, Marking start, Marking end, TraceOld prefix,
			Set<Edge> visited) {
		Set<TraceOld> traces = new HashSet<TraceOld>();
		if (start == end) {
			prefix.setFinished(true);
			traces.add(prefix);
			return traces;
		}
		for (Edge edge : graph.getOutEdges(start)) {
			// erstelle neuen Trace aus Prefix
			TraceOld trace = new TraceOld();
			trace.addAll(prefix);
			// Wenn kante keine stille Transition repräsentiert
			if (!isSilentTransition(process, edge.getT())) {
				// Füge die Kante dem Trace hinzu
				trace.add(edge.getT());
			}
			// Wenn Kante noch nicht durchlaufen wurde, Rekursion
			if (!visited.contains(edge)) {
				// Vorher merken, dass Kante bereits durchlaufen wurde (nur,
				// wenn keine Stille Kante
				// TODO: isSilent
				if (edge.getT() != null && !edge.getT().equals("")) {
					visited.add(edge);
				}
				// Rekursion
				traces.addAll(getTraces(process, graph, graph.getDest(edge),
						end, trace, visited));
			} else {
				trace.setFinished(false);
				traces.add(trace);
			}
		}
		return traces;
	}

	private static boolean isSilentTransition(Process process, String id) {
		if (id == null) {
			return true;
		}
		if (id.equals("")) {
			return true;
		}
		for (FlowElement elem : process.getFlowElements()) {
			if (elem.getId().equals(id)) {
				return false;
			}
		}
		return true;
	}
}
