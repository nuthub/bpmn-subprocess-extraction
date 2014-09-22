package edu.udo.cs.ls14.jf.rpst;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.jbpt.algo.tree.rpst.RPST;
import org.jbpt.utils.IOUtils;

public class Bpmn2Rpst {

	public static RPST<BpmnPathEdge, BpmnPathVertex> getRPST(Process process)
			throws IOException {
		return getRPST(process, false);
	}

	public static RPST<BpmnPathEdge, BpmnPathVertex> getRPST(Process process,
			boolean writeFiles) throws IOException {
		BpmnPathGraph g = getGraph(process);

		// Get RPST
		RPST<BpmnPathEdge, BpmnPathVertex> rpst = new RPST<BpmnPathEdge, BpmnPathVertex>(
				g);
		// Write files?
		if (writeFiles) {
			IOUtils.invokeDOT("/tmp", process.getName() + "-graph.png",
					g.toDOT());
			IOUtils.invokeDOT("/tmp/", process.getName() + "-rpst.png",
					rpst.toDOT());
		}
		return rpst;
	}

	private static BpmnPathGraph getGraph(Process process) {
		BpmnPathGraph g = new BpmnPathGraph();

		Map<String, BpmnPathVertex> vertices = process
				.getFlowElements()
				.stream()
				.filter(e -> false 
						|| e instanceof Activity
//				 || e instanceof IntermediateCatchEvent
//				 || e instanceof IntermediateThrowEvent
						|| e instanceof Event 
						|| e instanceof Gateway)
				.map(e -> (FlowNode) e)
				.collect(
						Collectors.toMap(n -> n.getId(),
								n -> new BpmnPathVertex(n)));

		process.getFlowElements()
				.stream()
				.filter(f -> f instanceof SequenceFlow)
				.map(f -> (SequenceFlow) f)
				.forEach(
						f -> g.addEdge(vertices.get(f.getSourceRef().getId()),
								vertices.get(f.getTargetRef().getId())));
		return g;
	}
}
