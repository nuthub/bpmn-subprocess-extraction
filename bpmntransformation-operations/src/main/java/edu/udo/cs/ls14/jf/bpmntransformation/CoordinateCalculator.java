package edu.udo.cs.ls14.jf.bpmntransformation;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.dc.Bounds;
import org.eclipse.dd.dc.DcFactory;
import org.eclipse.dd.dc.Point;
import org.eclipse.dd.di.DiagramElement;

import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;

/**
 * Calculates center coordinates of fragments and nodes.
 * 
 * @author Julian Flake
 *
 */
public class CoordinateCalculator {

	/**
	 * Returns the center of a flow node in a given BPMN Diagram.
	 * 
	 * @param node
	 *            given FlowNode
	 * @param definitions
	 *            Definitions with contained BPMN Diagram
	 * @return Point object with coordinates of flow node's center
	 * @throws Exception
	 *             if Definitions does not contain a DiagramF
	 */
	public static Point getCenter(FlowNode node, Definitions definitions)
			throws Exception {
		BPMNDiagram diagram = DefinitionsUtil.getDiagram(definitions);
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

	/**
	 * Returns the center of a fragment in a given BPMN Diagram.
	 * 
	 * @param fragment
	 *            given fragment
	 * @return Point object with coordinates of fragment's center
	 * @throws Exception
	 *             if Fragment is not contained in a Definitions object or
	 *             Definitions object containing the fragment does not contain a
	 *             BPMN Diagram
	 */
	public static Point getCenter(Fragment fragment) throws Exception {
		BPMNDiagram diagram = DefinitionsUtil.getDiagram(FragmentUtil
				.getDefinitions(fragment));
		float minX = -1;
		float maxX = 0;
		float minY = -1;
		float maxY = 0;
		for (DiagramElement element : diagram.getPlane().getPlaneElement()) {
			if (!(element instanceof BPMNShape)) {
				continue;
			}
			BPMNShape shape = (BPMNShape) element;
			if (!(shape.getBpmnElement() instanceof FlowElement)
					|| !FragmentUtil.contains(fragment,
							(FlowElement) shape.getBpmnElement())) {
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
