package edu.udo.cs.ls14.jf.transformation;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.analysis.pst.Fragment;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class CoordinateCalculator {
	

	public static Pair<Float, Float> getCoords(Resource res, FlowNode node)
			throws Exception {
		BPMNDiagram diagram = ProcessLoader.getDiagramFromResource(res);
		float x = 0;
		float y = 0;
		for (DiagramElement element : diagram.getPlane().getPlaneElement()) {
			if (element instanceof BPMNShape) {
				BPMNShape shape = (BPMNShape) element;
				if (shape.getBpmnElement().getId().equals(node.getId())) {
					Bounds bounds = shape.getBounds();
					x = shape.getBounds().getX() + bounds.getWidth() / 2;
					y = shape.getBounds().getY() + bounds.getHeight() / 2;
				}
			}
		}
		return Pair.with(x, y);
	}

	public static Pair<Float, Float> getCoords(Resource res, Fragment fragment)
			throws Exception {
		Set<FlowNode> nodes = fragment.getContainedFlowNodes(n -> true);
		Set<String> nodeIds = nodes.stream().map(n -> n.getId())
				.collect(Collectors.toSet());
		BPMNDiagram diagram = ProcessLoader.getDiagramFromResource(res);
		float minX = -1;
		float maxX = 0;
		float minY = -1;
		float maxY = 0;
		for (DiagramElement element : diagram.getPlane().getPlaneElement()) {
			if (!(element instanceof BPMNShape)) {
				continue;
			}
			BPMNShape shape = (BPMNShape) element;
			if (!nodeIds.contains(shape.getBpmnElement().getId())) {
				continue;
			}
			Bounds bounds = shape.getBounds();

			float centerX = bounds.getX() + bounds.getWidth() / 2;
			minX = minX == -1 ? centerX : Math.min(minX, centerX);
			maxX = Math.max(maxX, centerX);

			float centerY = bounds.getY() + bounds.getHeight() / 2;
			minY = minY == -1 ? centerY : Math.min(minY, centerY);
			maxY = Math.max(maxY, centerY);
		}
		return Pair
				.with(minX + ((maxX - minX) / 2), minY + ((maxY - minY) / 2));
	}

	
}
