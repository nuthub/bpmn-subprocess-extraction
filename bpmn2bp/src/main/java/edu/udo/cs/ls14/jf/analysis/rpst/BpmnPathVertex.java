package edu.udo.cs.ls14.jf.analysis.rpst;

import org.eclipse.bpmn2.FlowNode;
import org.jbpt.hypergraph.abs.Vertex;

public class BpmnPathVertex extends Vertex {

	private FlowNode flowNode;
	
	public BpmnPathVertex(FlowNode flowNode) {
		super(flowNode.getName());
		this.flowNode = flowNode;
	}

	public FlowNode getFlowNode() {
		return flowNode;
	}

	public void setFlowNode(FlowNode element) {
		this.flowNode = element;
	}
	
	

}
