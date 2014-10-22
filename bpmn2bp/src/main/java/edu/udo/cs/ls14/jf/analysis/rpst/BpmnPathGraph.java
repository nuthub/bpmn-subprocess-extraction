package edu.udo.cs.ls14.jf.analysis.rpst;

import org.jbpt.graph.abs.AbstractMultiDirectedGraph;

public class BpmnPathGraph extends
		AbstractMultiDirectedGraph<BpmnPathEdge, BpmnPathVertex> {

	@Override
	public BpmnPathEdge addEdge(BpmnPathVertex s, BpmnPathVertex t) {
		BpmnPathEdge e = new BpmnPathEdge(this, s, t);
		return e;
	}

}
