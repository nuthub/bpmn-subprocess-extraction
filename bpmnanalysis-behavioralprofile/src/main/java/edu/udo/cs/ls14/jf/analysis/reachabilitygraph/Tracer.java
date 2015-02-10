package edu.udo.cs.ls14.jf.analysis.reachabilitygraph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;

/**
 * Traverses a reachability graph and returns a TraceProfile (Set of Traces and
 * Trace prefixes).
 * 
 * @author Julian Flake
 *
 */
public class Tracer {

	/**
	 * Traverse reachability graph and return all traces and trace prefixes.
	 * 
	 * @param process
	 *            for mapping reachibility graph edges back to FlowNodes.
	 * @param rg
	 *            the reachability graph to traverse
	 * @return TraceProfile object
	 * @throws Exception
	 *             of an error occurs
	 */
	public static TraceProfile getTraceProfile(Process process,
			ReachabilityGraph rg) throws Exception {
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
			throw new Exception("expected exactly 1 start node, found "
					+ startNodes.size());
		}
		if (endNodes.size() != 1) {
			throw new Exception("expected exactly 1 end node, found "
					+ endNodes.size());
		}
		Marking start = startNodes.iterator().next();
		Marking end = endNodes.iterator().next();
		List<Trace> traces = getTraces(process, rg, start, end,
				BpmnAnalysisFactory.eINSTANCE.createTrace(),
				new HashSet<Edge>());
		TraceProfile profile = BpmnAnalysisFactory.eINSTANCE
				.createTraceProfile();
		profile.getTraces().addAll(traces);
		return profile;

	}

	private static EList<Trace> getTraces(Process process,
			ReachabilityGraph graph, Marking start, Marking end, Trace prefix,
			Set<Edge> visited) {
		EList<Trace> prefixes = new BasicEList<Trace>();
		if (start == end) {
			prefix.setFinished(true);
			prefixes.add(prefix);
			return prefixes;
		}
		for (Edge edge : graph.getOutEdges(start)) {
			// Kante repräsentiert eine Transition
			// Transition repräsentiert evtl FlowNode
			FlowNode currentNode = DefinitionsUtil.getFlowNode(process,
					edge.getT());
			// erstelle neuen Präfix aus altem Prefix
			Trace newPrefix = BpmnAnalysisFactory.eINSTANCE.createTrace();
			newPrefix.getNodes().addAll(prefix.getNodes());
			// Wenn node FlowNode ist (!=null) und eine Aktivität oder ein
			// Ereignis ist (also keine stille Transition)
			if (DefinitionsUtil.isAE(currentNode)) {
				// Füge FlowNode dem neuen Präfix hinzu, auch wenn schon
				// enthalten
				newPrefix.getNodes().add(currentNode);
			}
			// Wenn node bereits in altem präfix enthalten, keine Rekursion
			if (prefix.getNodes().contains(currentNode)) {
				newPrefix.setFinished(false);
				prefixes.add(newPrefix);
			} else {
				// Ansonsten Rekursion
				prefixes.addAll(getTraces(process, graph, graph.getDest(edge),
						end, newPrefix, visited));
			}
		}
		return prefixes;
	}

}
