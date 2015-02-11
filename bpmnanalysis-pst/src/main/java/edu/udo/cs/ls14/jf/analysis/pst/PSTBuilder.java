package edu.udo.cs.ls14.jf.analysis.pst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;

/**
 * Creates a ProcessStructureTree object for a given process model and
 * optionally returns intermediate artifacts.
 * 
 * @author Julian Flake
 *
 */
public class PSTBuilder {

	private static final Logger LOG = LoggerFactory.getLogger(PSTBuilder.class);

	private Process process;
	private StartEvent start;
	private EndEvent end;
	private SequenceFlow insertEdge;

	private UndirectedGraph<FlowNode, SequenceFlow> graph;
	private DirectedGraph<FlowNode, SequenceFlow> spanningTree;
	private Map<SequenceFlow, EdgeState> edgeStates;
	private Map<SequenceFlow, Set<SequenceFlow>> bracketSets;
	private Map<Set<SequenceFlow>, Set<SequenceFlow>> cycleEqCls;
	private Map<Set<SequenceFlow>, List<SequenceFlow>> sortedCycleEqCls;
	private List<Fragment> canonicalFragments;
	private DirectedGraph<Fragment, Object> structureTree;

	String newline = System.getProperty("line.separator");

	/**
	 * Definitions with process model a PST should be generated for.
	 * 
	 * @param definitions
	 *            Definitions with process model
	 * @return resulting ProcessStructureTree
	 * @throws Exception
	 *             if an error occurs
	 */
	public ProcessStructureTree getTree(Definitions definitions)
			throws Exception {
		this.process = DefinitionsUtil.getProcess(definitions);

		// initialize
		spanningTree = new DirectedSparseMultigraph<FlowNode, SequenceFlow>();
		edgeStates = new HashMap<SequenceFlow, EdgeState>();
		bracketSets = new HashMap<SequenceFlow, Set<SequenceFlow>>();
		start = getStartNode(process);
		end = getEndNode(process);

		// create undirected graph from process
		graph = buildGraph(process);
		// add end->start edge
		insertEdge = completeGraph();

		// create (directed) spanning tree and seperate edges into set of tree
		// edges and back edges, determine bracket sets per edge
		LOG.debug("creating spanning tree and computing bracket sets ...");
		// start with startevent, although it is irrelevant which node is start
		// node
		undirectedDFS(graph, start, new ArrayList<FlowNode>());
		bracketSets.entrySet().forEach(
				e -> LOG.debug(getLabel(e.getKey())
						+ ": "
						+ e.getValue().stream().map(b -> getLabel(b))
								.collect(Collectors.toSet())));

		// create cycle equivalent classes
		LOG.debug("creating cycle equivalent classes ...");
		cycleEqCls = createCycleEqCls(bracketSets);
		for (Entry<Set<SequenceFlow>, Set<SequenceFlow>> entry : cycleEqCls
				.entrySet()) {
			List<String> brackets = entry.getKey().stream()
					.map(e -> getLabel(e)).collect(Collectors.toList());
			List<String> members = entry.getValue().stream()
					.map(e -> getLabel(e)).collect(Collectors.toList());
			LOG.debug("{" + StringUtils.join(brackets, ", ") + "} => {"
					+ StringUtils.join(members, ", ") + "}");
		}

		// sort cycle equivalent classes
		LOG.debug("sorting cycle equivalent classes ...");
		sortedCycleEqCls = sortCycleEqCls(cycleEqCls);
		for (Entry<Set<SequenceFlow>, List<SequenceFlow>> entry : sortedCycleEqCls
				.entrySet()) {
			List<String> brackets = entry.getKey().stream()
					.map(e -> getLabel(e)).collect(Collectors.toList());
			List<String> members = entry.getValue().stream()
					.map(e -> getLabel(e)).collect(Collectors.toList());
			LOG.debug("{" + StringUtils.join(brackets, ", ") + "} => {"
					+ StringUtils.join(members, ", ") + "}");
		}

		// get canonicalFragments From sorted cycle equivalent classes
		LOG.debug("yielding canonical fragments from sorted cycle equivalent classes ...");
		canonicalFragments = yieldCanonicalFragmentsFromSortedCycleEqCls(sortedCycleEqCls);
		canonicalFragments.forEach(f -> LOG.debug(FragmentUtil.toString(f)));

		// create pst from canonical sese-fragments
		LOG.debug("building structure tree ...");
		structureTree = buildStructureTree(canonicalFragments);

		// remove end->start edge
		LOG.debug("decompleting graph");
		decompleteGraph(insertEdge);

		LOG.debug("done ...");

		ProcessStructureTree tree = BpmnAnalysisFactory.eINSTANCE
				.createProcessStructureTree();
		tree.getFragments().addAll(canonicalFragments);
		return tree;

	}

	/**
	 * Definition 5: A SESE region (a,b) is canonical provided
	 * 
	 * 1. b dominates b' for any SESE region (a,b'), and
	 * 
	 * 2. a postdominates a' for any SESE region (a',b) .
	 * 
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
	 * @param fragments
	 * @return
	 */
	private List<Fragment> yieldCanonicalFragmentsFromSortedCycleEqCls(
			Map<Set<SequenceFlow>, List<SequenceFlow>> classes) {
		List<Fragment> canonicalFragments = new ArrayList<Fragment>();
		for (List<SequenceFlow> ceClass : classes.values()) {
			if (ceClass.size() < 2) {
				continue;
			}
			for (int i = 0; i < ceClass.size() - 1; i++) {
				Fragment f = BpmnAnalysisFactory.eINSTANCE.createFragment();
				f.setEntry(ceClass.get(i));
				f.setExit(ceClass.get(i + 1));
				canonicalFragments.add(f);
			}
		}
		return canonicalFragments;
	}

	private Map<Set<SequenceFlow>, List<SequenceFlow>> sortCycleEqCls(
			Map<Set<SequenceFlow>, Set<SequenceFlow>> classes) {
		Map<Set<SequenceFlow>, List<SequenceFlow>> sorted = new HashMap<Set<SequenceFlow>, List<SequenceFlow>>();
		for (Entry<Set<SequenceFlow>, Set<SequenceFlow>> entry : classes
				.entrySet()) {
			List<SequenceFlow> currentCls = new ArrayList<SequenceFlow>(
					entry.getValue());
			for (int i = 0; i < currentCls.size() - 1; i++) {
				for (int j = i + 1; j < currentCls.size(); j++) {
					if (dominates(start, currentCls.get(j), currentCls.get(i))) {
						SequenceFlow dominator = currentCls.get(j);
						currentCls.set(j, currentCls.get(i));
						currentCls.set(i, dominator);
					}
				}
			}
			sorted.put(entry.getKey(), currentCls);
		}
		return sorted;
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
	private Map<Set<SequenceFlow>, Set<SequenceFlow>> createCycleEqCls(
			Map<SequenceFlow, Set<SequenceFlow>> bracketSets) {
		Map<Set<SequenceFlow>, Set<SequenceFlow>> classes = new HashMap<Set<SequenceFlow>, Set<SequenceFlow>>();
		for (Entry<SequenceFlow, Set<SequenceFlow>> bracketSet : bracketSets
				.entrySet()) {
			if (!classes.containsKey(bracketSet.getValue())) {
				classes.put(bracketSet.getValue(), new HashSet<SequenceFlow>());
			}
			if (bracketSet.getKey() != insertEdge) {
				classes.get(bracketSet.getValue()).add(bracketSet.getKey());
			}
		}
		for (Entry<Set<SequenceFlow>, Set<SequenceFlow>> cls : classes
				.entrySet()) {
			if (cls.getKey().size() == 1) {
				for (SequenceFlow flow : cls.getKey()) {
					if (flow != insertEdge) {
						cls.getValue().add(flow);
					}
				}
			}
		}
		return classes;
	}

	/**
	 * DFS through process, when entering a fragment, put on stack
	 * 
	 * TODO: respect multiple start / end nodes
	 * 
	 * @param canonicalFragments
	 * @return
	 */
	private DirectedGraph<Fragment, Object> buildStructureTree(
			List<Fragment> canonicalFragments) {
		structureTree = new DirectedSparseGraph<Fragment, Object>();
		buildStructureTreeAcc(canonicalFragments, start.getOutgoing().get(0),
				BpmnAnalysisFactory.eINSTANCE.createFragment(),
				new Stack<Fragment>(), new HashSet<SequenceFlow>());
		return structureTree;
	}

	private void buildStructureTreeAcc(List<Fragment> canonicalFragments,
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
		insertEdge = Bpmn2Factory.eINSTANCE.createSequenceFlow();
		insertEdge.setId("inserted");
		insertEdge.setName(insertEdge.getId());
		insertEdge.setSourceRef(end);
		insertEdge.setTargetRef(start);
		graph.addEdge(insertEdge, start, end);
		return insertEdge;
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
					LOG.debug("adding tree edge: " + getLabel(edge));
					// save childrens' bracket sets
					Set<SequenceFlow> childBrackets = undirectedDFS(g, w,
							visited);
					bracketSets.put(edge, childBrackets);
					brackets.addAll(childBrackets);
				} else {
					// add edge to back edges
					edgeStates.put(edge, EdgeState.BACK);
					spanningTree.addEdge(edge, v, w);
					LOG.debug("adding back edge: " + getLabel(edge));
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
	// private boolean postDominates(FlowNode end, SequenceFlow x, SequenceFlow
	// y) {
	// for (SequenceFlow to : end.getIncoming()) {
	// Set<List<SequenceFlow>> paths = getPaths(y, to,
	// new ArrayList<SequenceFlow>());
	// for (List<SequenceFlow> path : paths) {
	// if (!path.contains(x)) {
	// return false;
	// }
	// }
	// }
	// return true;
	// }

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

	/*
	 * TODO: check for multiple end nodes
	 */
	private StartEvent getStartNode(Process process) {
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof StartEvent) {
				return (StartEvent) elem;
			}
		}
		return null;
	}

	/*
	 * TODO: check for multiple end nodes
	 */
	private EndEvent getEndNode(Process process) {
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof EndEvent) {
				return (EndEvent) elem;
			}
		}
		return null;
	}

	private String getLabel(FlowElement element) {
		return (element.getName() == null || element.getName().equals("") ? "["
				+ element.getId() + "]" : element.getName());
	}

	public String undirectedGraphToDot(int fontsize) {
		StringBuffer sb = new StringBuffer();
		sb.append("graph {" + newline);
		sb.append("  rankdir=LR;" + newline);
		sb.append("  node[fontsize=" + fontsize + "];" + newline);
		sb.append("  edge[fontsize=" + fontsize + "];" + newline);
		sb.append("  \"" + getLabel(start) + "\"" + newline);
		for (SequenceFlow edge : graph.getEdges()) {
			sb.append("  \"" + getLabel(edge.getSourceRef()) + "\"");
			sb.append(" -- ");
			sb.append("\"" + getLabel(edge.getTargetRef()) + "\"");
			sb.append("[label=\"" + getLabel(edge) + "\", weight=1];" + newline);
		}
		sb.append(" \"" + getLabel(start) + "\" -- \"" + getLabel(end)
				+ "\" [label=\"" + getLabel(insertEdge) + "\", weight=0];"
				+ newline);
		sb.append("}" + newline);
		return sb.toString();
	}

	public String spanningTreeToDot(int fontsize) {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph {" + newline);
		sb.append("  node[fontsize=" + fontsize + "];" + newline);
		sb.append("  edge[fontsize=" + fontsize + "];" + newline);
		sb.append("  \"" + getLabel(start) + "\";" + newline);
		for (SequenceFlow edge : spanningTree.getEdges()) {
			sb.append("  \"" + getLabel(spanningTree.getSource(edge)) + "\"");
			sb.append(" -> ");
			sb.append("\"" + getLabel(spanningTree.getDest(edge)) + "\"");
			sb.append("[");
			// edge label
			sb.append("label=\"");
			sb.append(getLabel(edge));
			// bracket sets, wenn tree edge
			if (edgeStates.get(edge) == EdgeState.TREE) {
				sb.append(" {");
				sb.append(StringUtils.join(
						bracketSets.get(edge).stream().map(b -> getLabel(b))
								.collect(Collectors.toSet()), ","));
				sb.append("}");
			}
			sb.append("\"");
			// dotted edge for back edges
			if (edgeStates.get(edge) == EdgeState.BACK) {
				sb.append(", style=\"dotted\", weight=1");
			} else {
				sb.append(", weight=2");
			}
			sb.append("];" + newline);
		}
		sb.append("}" + newline);
		return sb.toString();

	}

	public String structureTreeToDot(int fontsize) {
		StringBuffer sb = new StringBuffer();
		sb.append("digraph {" + newline);
		sb.append("node[fontsize=" + fontsize + "];" + newline);
		for (Object edge : structureTree.getEdges()) {
			if (structureTree.getSource(edge).getEntry() == null) {
				sb.append("\"" + process.getName() + "\"");
			} else {
				sb.append("\"("
						+ getLabel(structureTree.getSource(edge).getEntry())
						+ ","
						+ getLabel(structureTree.getSource(edge).getExit())
						+ ")\"");
			}
			sb.append(" -> ");
			sb.append("\"(" + getLabel(structureTree.getDest(edge).getEntry())
					+ "," + getLabel(structureTree.getDest(edge).getExit())
					+ ")\"");
			sb.append(";" + newline);
		}
		sb.append("}" + newline);
		return sb.toString();

	}

	public String canonicalFragmentsToTex() {
		List<String> fragments = new ArrayList<String>();
		for (Fragment f : canonicalFragments) {
			fragments.add("(" + getLabel(f.getEntry()) + ","
					+ getLabel(f.getExit()) + ")");
		}
		return "$" + StringUtils.join(fragments, ", \\allowbreak" + newline)
				+ "$";
	}

	public String sortedCycleEqClsToTex() {
		List<String> classes = new ArrayList<String>();
		for (List<SequenceFlow> cls : sortedCycleEqCls.values()) {
			List<String> clsSets = new ArrayList<String>();
			for (SequenceFlow flow : cls) {
				clsSets.add(getLabel(flow));
			}
			classes.add("\\langle " + StringUtils.join(clsSets, ",")
					+ "\\rangle ");
		}
		return "$" + StringUtils.join(classes, ", \\allowbreak" + newline)
				+ "$";
	}

	public String cycleEqClsToTex() {
		List<String> classes = new ArrayList<String>();
		for (Set<SequenceFlow> cls : cycleEqCls.values()) {
			List<String> clsSets = new ArrayList<String>();
			for (SequenceFlow flow : cls) {
				clsSets.add(getLabel(flow));
			}
			classes.add("\\{" + StringUtils.join(clsSets, ",") + "\\}");
		}
		return "$" + StringUtils.join(classes, ", \\allowbreak" + newline)
				+ "$";
	}
}
