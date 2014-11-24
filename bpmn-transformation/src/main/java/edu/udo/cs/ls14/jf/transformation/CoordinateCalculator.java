package edu.udo.cs.ls14.jf.transformation;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.dc.DcFactory;
import org.eclipse.dd.dc.Point;
import org.eclipse.dd.di.DiagramElement;
import org.javatuples.Pair;

import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;

public class CoordinateCalculator {

	public static Point getCenter(FlowNode node, Definitions definitions)
			throws Exception {
		BPMNDiagram diagram = ProcessUtil
				.getDiagramFromDefinitions(definitions);
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
		Point point = DcFactory.eINSTANCE.createPoint();
		point.setX(x);
		point.setY(y);
		return point;
	}

	public static Point getCenter(Fragment fragment) throws Exception {
		Predicate<FlowElement> filter = e -> e instanceof FlowNode;
		Set<FlowElement> nodes = FragmentUtil.getFlowElements(fragment, filter);
		Set<String> nodeIds = nodes.stream().map(n -> n.getId())
				.collect(Collectors.toSet());
		BPMNDiagram diagram = ProcessUtil.getDiagramFromDefinitions(fragment
				.getDefinitions());
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
		Point point = DcFactory.eINSTANCE.createPoint();
		point.setX(minX + ((maxX - minX) / 2));
		point.setY(minY + ((maxY - minY) / 2));
		return point;
	}

}
