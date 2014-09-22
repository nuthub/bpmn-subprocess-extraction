package edu.udo.cs.ls14.jf.pst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

public class PST {

	private Process process;
	private UndirectedGraph<FlowNode, SequenceFlow> graph;
	private DirectedGraph<FlowNode, SequenceFlow> tree;
	private Map<SequenceFlow, EdgeState> edgeStates;
	private Map<SequenceFlow, Set<SequenceFlow>> bracketSets;
	private HashSet<Fragment> fragments;

	public Set<Fragment> createFromProcess(Process process) {
		this.process = process;
		graph = new UndirectedSparseMultigraph<FlowNode, SequenceFlow>();
		process.getFlowElements()
				.stream()
				.filter(f -> f instanceof SequenceFlow)
				.map(f -> (SequenceFlow) f)
				.forEach(
						f -> graph.addEdge(f, f.getSourceRef(),
								f.getTargetRef()));
//		completeGraph();
		return createFromGraph(graph);
	}

	// TODO: If really not required
	@SuppressWarnings("unused")
	private void completeGraph() {
		StartEvent start = null;
		EndEvent end = null;
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof StartEvent) {
				start = (StartEvent) elem;
			}
			if (elem instanceof EndEvent) {
				end = (EndEvent) elem;
			}
		}
		SequenceFlow sflow = Bpmn2Factory.eINSTANCE.createSequenceFlow();
		sflow.setId("inserted-end-start-flow");
		sflow.setName(sflow.getId());
		sflow.setSourceRef(end);
		sflow.setTargetRef(start);
		graph.addEdge(sflow, start, end);
	}

	private Set<Fragment> createFromGraph(
			UndirectedGraph<FlowNode, SequenceFlow> graph) {
		// initialize
		edgeStates = new HashMap<SequenceFlow, EdgeState>();
		bracketSets = new HashMap<SequenceFlow, Set<SequenceFlow>>();
		tree = new DirectedSparseMultigraph<FlowNode, SequenceFlow>();
		// run
		System.out.println("--- creating spanning tree ... ---");
		undirectedDFS(graph, graph.getVertices().iterator().next(),
				new ArrayList<FlowNode>());
		// output
		System.out.println("--- bracket sets are ... ---");
		for (SequenceFlow f : tree.getEdges()) {
			System.out.println(f.getName()
					+ ": "
					+ bracketSets.get(f).stream().map(e -> e.getName())
							.collect(Collectors.toSet()));
		}
		System.out.println("--- yielding Fragments ... ---");
		yieldFragments();
		fragments.stream().forEach(
				f -> System.out.println(f.stream().map(a -> a.getName())
						.collect(Collectors.toSet())));
		return fragments;
	}

	private Set<SequenceFlow> undirectedDFS(
			UndirectedGraph<FlowNode, SequenceFlow> g, FlowNode v,
			List<FlowNode> visited) {
		visited.add(v);
		// create set of brackets
		Set<SequenceFlow> brackets = new HashSet<SequenceFlow>();
		for (SequenceFlow edge : g.getIncidentEdges(v)) {
			if (!edgeStates.containsKey(edge)) {
				FlowNode w = g.getOpposite(v, edge);
				if (!visited.contains(w)) {
					// add edge to tree edges
					edgeStates.put(edge, EdgeState.TREE);
					tree.addEdge(edge, v, w);
					// save childrens' bracket sets
					Set<SequenceFlow> childBrackets = undirectedDFS(g, w,
							visited);
					bracketSets.put(edge, childBrackets);
					brackets.addAll(childBrackets);
				} else {
					// add edge to back edges
					edgeStates.put(edge, EdgeState.BACK);
					tree.addEdge(edge, v, w);
					bracketSets.put(edge, new HashSet<SequenceFlow>());
					// save backedge from v to ancestor(v)
					brackets.add(edge);
				}
			}
		}

		// remove brackets from descendant to v
		tree.getEdges()
				.stream()
				.filter(e -> edgeStates.get(e) == EdgeState.BACK
						&& tree.getDest(e).equals(v))
				.forEach(e -> brackets.remove(e));
		return brackets;
	}

	private void yieldFragments() {
		fragments = new HashSet<Fragment>();
		for (SequenceFlow e : tree.getEdges()) {
			for (SequenceFlow f : tree.getEdges()) {
				if (!e.equals(f)) {
					boolean isFragment = false;
					if (edgeStates.get(e) == EdgeState.BACK
							&& edgeStates.get(f) == EdgeState.BACK) {
						// can't be a fragment
					} else if (edgeStates.get(e) == EdgeState.BACK
							&& edgeStates.get(f) == EdgeState.TREE) {
						// is fragment, if e is only bracket of f
						if (bracketSets.get(f).size() == 1
								&& bracketSets.get(f).contains(e)) {
							isFragment = true;
						}
					} else if (edgeStates.get(e) == EdgeState.TREE
							&& edgeStates.get(f) == EdgeState.BACK) {
						// is fragment, if f is only bracket of e
						if (bracketSets.get(e).size() == 1
								&& bracketSets.get(e).contains(f)) {
							isFragment = true;
						}
					} else if (edgeStates.get(e) == EdgeState.TREE
							&& edgeStates.get(f) == EdgeState.TREE) {
						// is fragment, if bracket sets of e and f are the same
						if (bracketSets.get(e).equals(bracketSets.get(f))) {
							isFragment = true;
						}
					} else {
						// no chance to get here
					}
					// TODO: Determine domination and postdomination
					if (isFragment && !fragments.contains(new Fragment(e, f))
							&& !fragments.contains(new Fragment(f, e))) {
						fragments.add(new Fragment(e, f));
					}
				}
			}
		}
	}

	public String getGraphAsDot() {
		StringBuffer sb = new StringBuffer();
		sb.append("graph {");
		sb.append(System.getProperty("line.separator"));
		for (SequenceFlow edge : graph.getEdges()) {
			sb.append("\"" + edge.getSourceRef().getName() + "\"");
			sb.append(" -- ");
			sb.append("\"" + edge.getTargetRef().getName() + "\"");
			sb.append(";");
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("}");
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

	public String getSpanningTreeAsDot() {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph {");
		sb.append(System.getProperty("line.separator"));
		for (SequenceFlow edge : tree.getEdges()) {

			sb.append("\"" + tree.getSource(edge).getName() + "\"");
			sb.append(" -> ");
			sb.append("\"" + tree.getDest(edge).getName() + "\"");
			sb.append("[");
			sb.append("label=\"");
			sb.append(edge.getName());
			sb.append(" {");
			sb.append(StringUtils.join(
					bracketSets.get(edge).stream().map(b -> b.getName())
							.collect(Collectors.toSet()), ","));
			sb.append("}");
			sb.append("\"");
			if (edgeStates.get(edge) == EdgeState.BACK) {
				sb.append(", style=\"dotted\"");
			}
			sb.append("]");
			sb.append(";");
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("}");
		sb.append(System.getProperty("line.separator"));
		return sb.toString();

	}
}
