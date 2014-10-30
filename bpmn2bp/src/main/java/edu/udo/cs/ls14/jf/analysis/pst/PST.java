package edu.udo.cs.ls14.jf.analysis.pst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.utils.bpmn.NodeFinder;

public class PST {

	private static final Logger LOG = LoggerFactory.getLogger(PST.class);

	private Resource resource;
	private Process process;
	private UndirectedGraph<FlowNode, SequenceFlow> graph;
	private DirectedGraph<FlowNode, SequenceFlow> spanningTree;
	private Map<SequenceFlow, EdgeState> edgeStates;
	private Map<SequenceFlow, Set<SequenceFlow>> bracketSets;
	private Set<Fragment> fragments;
	private Set<Fragment> canonicalFragments;
	private DirectedGraph<Fragment, Object> structureTree;
	private StartEvent start;
	private EndEvent end;
	private SequenceFlow insertEdge;

	public void createFromProcess(Resource resource) throws Exception {
		this.resource = resource;
		this.process = ProcessLoader.getProcessFromResource(resource);
		// initialize
		edgeStates = new HashMap<SequenceFlow, EdgeState>();
		bracketSets = new HashMap<SequenceFlow, Set<SequenceFlow>>();
		spanningTree = new DirectedSparseMultigraph<FlowNode, SequenceFlow>();
		start = NodeFinder.getStartNode(process);
		end = NodeFinder.getEndNode(process);

		// create undirected graph from process
		graph = buildGraph(process);
		// add end->start edge
		insertEdge = completeGraph();

		// create (directed) spanning tree and seperate edges into set of tree
		// edges and back edges, determine bracket sets per edge
		LOG.debug("creating spanning tree and computing bracket sets ...");
		undirectedDFS(graph, graph.getVertices().iterator().next(),
				new ArrayList<FlowNode>());
		bracketSets.entrySet().forEach(
				e -> LOG.debug(e.getKey().getName()
						+ ": "
						+ e.getValue().stream().map(b -> b.getName())
								.collect(Collectors.toSet())));

		// yield all sese-fragments from edgestates
		LOG.debug("yielding Fragments ...");
		fragments = yieldFragments(start, end);
		fragments.forEach(f -> LOG.debug(f.toString()));

		// filter canonical sese-fragments from all sese-fragments
		LOG.debug("Filtering canonical fragments...");
		canonicalFragments = canoncialFragments(start, end, fragments);
		canonicalFragments.forEach(f -> LOG.debug(f.toString()));

		// create pst from canonical sese-fragments
		LOG.debug("building structure tree ...");
		structureTree = buildStructureTree(canonicalFragments);

		// remove end->start edge
		LOG.debug("decompleting graph");
		decompleteGraph(insertEdge);

		LOG.debug("done ...");
	}

	public Set<Fragment> getFragments() {
		return canonicalFragments;
	}

	/**
	 * DFS through process, when entering a fragment, put on stack
	 * 
	 * TODO: respect multiple start / end nodes
	 * 
	 * @param canonicalFragments2
	 * @return
	 */
	private DirectedGraph<Fragment, Object> buildStructureTree(
			Set<Fragment> canonicalFragments) {
		structureTree = new DirectedSparseGraph<Fragment, Object>();
		Stack<Fragment> parents = new Stack<Fragment>();
		buildStructureTreeAcc(canonicalFragments, start.getOutgoing().get(0),
				new Fragment(resource, process, null, null), parents,
				new HashSet<SequenceFlow>());
		return structureTree;
	}

	private void buildStructureTreeAcc(Set<Fragment> canonicalFragments,
			SequenceFlow edge, Fragment root, Stack<Fragment> parents,
			Set<SequenceFlow> visited) {
		if (visited.contains(edge)) {
			return;
		}
		visited.add(edge);
		for (Fragment f : canonicalFragments) {
			if (edge.equals(f.getExit())) {
				parents.pop();
			}
		}

		for (Fragment f : canonicalFragments) {
			if (edge.equals(f.getEntry())) {
				if (parents.empty()) {
					structureTree.addEdge(new Object(), root, f);
				} else {
					f.setParent(parents.peek());
					structureTree.addEdge(new Object(), parents.peek(), f);
				}
				parents.push(f);
			}
		}

		for (SequenceFlow f : edge.getTargetRef().getOutgoing()) {
			Stack<Fragment> childParents = new Stack<Fragment>();
			childParents.addAll(parents);
			buildStructureTreeAcc(canonicalFragments, f, root, childParents,
					visited);
		}
		if (!parents.empty()) {
			parents.pop();
		}
	}

	/**
	 * Inserts an edge from end to start
	 * 
	 * @return
	 */
	private SequenceFlow completeGraph() {
		FlowNode start = this.start;
		FlowNode end = this.end;
		SequenceFlow edge = Bpmn2Factory.eINSTANCE.createSequenceFlow();
		edge.setId("inserted-end-start-flow");
		edge.setName(edge.getId());
		edge.setSourceRef(end);
		edge.setTargetRef(start);
		graph.addEdge(edge, start, end);
		return edge;
	}

	/**
	 * Removes edge from end to start
	 * 
	 * @param insertEdge
	 * 
	 * @return
	 */
	private void decompleteGraph(SequenceFlow insertEdge) {
		insertEdge.setSourceRef(null);
		insertEdge.setTargetRef(null);
		graph.removeEdge(insertEdge);
	}

	/**
	 * During an undirected depth-first traversal, we can compute the set of
	 * brackets for each tree edge. When retreating out of a node, we form the
	 * union of bracket sets from the node's children, together with the set of
	 * backedges from the node to an ancestor, minus the set of backedges from a
	 * descendant to the node; the result is the bracket set for the tree edge
	 * into the current node. Intuitively, the set of brackets of a tree edge is
	 * a name for the edge's cycle equivalence class; by comparing these sets,
	 * we find cycle equivalent edges. However, building and comparing sets is
	 * expensive, so the algorithm is inefficient.
	 * 
	 * @param g
	 * @param v
	 * @param visited
	 * @return
	 */
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
					spanningTree.addEdge(edge, v, w);
					LOG.debug("adding tree edge from " + v.getName() + " to "
							+ w.getName());
					// save childrens' bracket sets
					Set<SequenceFlow> childBrackets = undirectedDFS(g, w,
							visited);
					bracketSets.put(edge, childBrackets);
					brackets.addAll(childBrackets);
				} else {
					// add edge to back edges
					edgeStates.put(edge, EdgeState.BACK);
					spanningTree.addEdge(edge, v, w);
					LOG.debug("adding back edge from " + v.getName() + " to "
							+ w.getName());
					bracketSets.put(edge, new HashSet<SequenceFlow>());
					// save backedge from v to ancestor(v)
					brackets.add(edge);
				}
			}
		}

		// remove brackets from descendant to v
		spanningTree
				.getEdges()
				.stream()
				.filter(e -> edgeStates.get(e) == EdgeState.BACK
						&& spanningTree.getDest(e).equals(v))
				.forEach(e -> brackets.remove(e));
		return brackets;
	}

	/**
	 * 
	 * Now consider whether two edges in U are cycle equivalent. Two backedges
	 * cannot be cycle equivalent since the cycle formed from a backedge
	 * together with the tree path connecting its endpoints contains no other
	 * backedges. On the other hand, a tree edge and a backedge or two tree
	 * edges may be cycle equivalent. The following theorems establish
	 * conditions for detecting these equivalences.
	 * 
	 * Theorem 4: A backedge b and a tree edge t are cycle equivalent if and
	 * only if b is the only bracket of t.
	 * 
	 * Theorem 5: Tree edges s and t are cycle equivalent in U if and only if
	 * they have the same set of brackets in any depth-first spanning tree of U.
	 * 
	 * @param start
	 * @param end
	 */
	private Set<Fragment> yieldFragments(FlowNode start, FlowNode end) {
		fragments = new HashSet<Fragment>();
		for (SequenceFlow e : spanningTree.getEdges()) {
			for (SequenceFlow f : spanningTree.getEdges()) {

				if (!e.equals(f) && !f.equals(insertEdge)) {
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
						// is fragment, if bracket sets of e and f are the
						// same
						if (bracketSets.get(e).equals(bracketSets.get(f))) {
							isFragment = true;
						}
					} else {
						// no chance to get here
					}
					if (isFragment) {
						if (dominates(start, e, f)
								&& !fragments.contains(new Fragment(resource,
										process, e, f))) {
							fragments
									.add(new Fragment(resource, process, e, f));
						}
					}
				}
			}
		}
		return fragments;
	}

	/**
	 * Definition 5: A SESE region (a,b) is canonical provided
	 * 
	 * 1. b dominates b' for any SESE region (a,b'), and
	 * 
	 * 2. a postdominates a' for any SESE region (a',b) .
	 * 
	 * @param fragments
	 * @return
	 */
	private Set<Fragment> canoncialFragments(FlowNode start, FlowNode end,
			Set<Fragment> fragments) {
		Set<Fragment> canonicalFragments = new HashSet<Fragment>();
		for (Fragment e : fragments) {
			boolean isCanonical = true;
			for (Fragment f : fragments) {
				if (!e.equals(f)) {
					// b dominates b'
					if (e.getEntry().equals(f.getEntry())
							&& !dominates(start, e.getExit(), f.getExit())) {
						LOG.debug(e.getExit().getName() + " does not dominate "
								+ f.getExit().getName() + ", so " + e
								+ " is not canonical, because of " + f);
						isCanonical = false;
					}
					// // a postdominates a'
					if (e.getExit().equals(f.getExit())
							&& !postDominates(end, e.getEntry(), f.getEntry())) {
						LOG.debug(e.getEntry().getName()
								+ " does not postdominate "
								+ f.getEntry().getName() + ", so " + e
								+ " is not canonical, because of " + f);
						isCanonical = false;
					}
				}
			}
			if (isCanonical) {
				canonicalFragments.add(e);
			}
		}
		return canonicalFragments;
	}

	/**
	 * Definition 2
	 * 
	 * A node x is said to dominate node y in a directed graph if every path
	 * from start to y includes x .
	 * 
	 * A node x is said to postdominate a node y if every path from y to end
	 * includes x."
	 * 
	 * The notions of dominance and postdominance can be extended to edges in
	 * the obvious way.
	 * 
	 * [The program structure tree: computing control regions in linear time]
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean dominates(FlowNode start, SequenceFlow x, SequenceFlow y) {
		for (SequenceFlow from : start.getOutgoing()) {
			Set<List<SequenceFlow>> paths = getPaths(from, y,
					new ArrayList<SequenceFlow>());
			for (List<SequenceFlow> path : paths) {
				if (!path.contains(x)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Definition 2
	 * 
	 * A node x is said to dominate node y in a directed graph if every path
	 * from start to y includes x .
	 * 
	 * A node x is said to postdominate a node y if every path from y to end
	 * includes x."
	 * 
	 * The notions of dominance and postdominance can be extended to edges in
	 * the obvious way.
	 * 
	 * [The program structure tree: computing control regions in linear time]
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean postDominates(FlowNode end, SequenceFlow x, SequenceFlow y) {
		for (SequenceFlow to : end.getIncoming()) {
			Set<List<SequenceFlow>> paths = getPaths(y, to,
					new ArrayList<SequenceFlow>());
			for (List<SequenceFlow> path : paths) {
				if (!path.contains(x)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns all paths between two sequence flows
	 */
	private Set<List<SequenceFlow>> getPaths(SequenceFlow from,
			SequenceFlow to, List<SequenceFlow> path) {
		Set<List<SequenceFlow>> paths = new HashSet<List<SequenceFlow>>();
		if (from.equals(to)) {
			path.add(to);
			paths.add(path);
			return paths;
		}
		if (!path.contains(from)) {
			path.add(from);
			for (SequenceFlow flow : from.getTargetRef().getOutgoing()) {
				List<SequenceFlow> newPath = new ArrayList<SequenceFlow>(path);
				paths.addAll(getPaths(flow, to, newPath));
			}
			return paths;
		} else {
			return paths;
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
		for (SequenceFlow edge : spanningTree.getEdges()) {

			sb.append("\"" + spanningTree.getSource(edge).getName() + "\"");
			sb.append(" -> ");
			sb.append("\"" + spanningTree.getDest(edge).getName() + "\"");
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

	public String getStructureTreeAsDot() {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph {");
		sb.append(System.getProperty("line.separator"));
		for (Object edge : structureTree.getEdges()) {
			if (structureTree.getSource(edge).getEntry() == null) {
				sb.append("\"" + process.getName() + "\"");
			} else {
				sb.append("\"("
						+ structureTree.getSource(edge).getEntry().getName()
						+ ","
						+ structureTree.getSource(edge).getExit().getName()
						+ ")\"");
			}
			sb.append(" -> ");
			sb.append("\"(" + structureTree.getDest(edge).getEntry().getName()
					+ "," + structureTree.getDest(edge).getExit().getName()
					+ ")\"");
			sb.append(";");
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("}");
		sb.append(System.getProperty("line.separator"));
		return sb.toString();

	}

	private UndirectedGraph<FlowNode, SequenceFlow> buildGraph(Process process) {
		UndirectedGraph<FlowNode, SequenceFlow> graph = new UndirectedSparseMultigraph<FlowNode, SequenceFlow>();
		process.getFlowElements()
				.stream()
				.filter(f -> f instanceof SequenceFlow)
				.map(f -> (SequenceFlow) f)
				.forEach(
						f -> graph.addEdge(f, f.getSourceRef(),
								f.getTargetRef()));
		return graph;
	}

}
