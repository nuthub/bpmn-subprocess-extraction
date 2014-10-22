package edu.udo.cs.ls14.jf.analysis.rpst;

import org.jbpt.graph.abs.AbstractDirectedEdge;
import org.jbpt.graph.abs.AbstractMultiDirectedGraph;

public class BpmnPathEdge extends AbstractDirectedEdge<BpmnPathVertex> {

	public BpmnPathEdge(AbstractMultiDirectedGraph<?, BpmnPathVertex> g, BpmnPathVertex source,
			BpmnPathVertex target) {
		super(g, source, target);
	}

}
