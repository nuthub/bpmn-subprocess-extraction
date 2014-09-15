package edu.udo.cs.ls14.jf.utils.graph;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

public class GraphVisualizer<V, E> {

	public JPanel visualize(Graph<V, E> graph,
			Transformer<V, String> vertexLabelTransformer,
			Transformer<E, String> edgeLabelTransformer) {
		// Layout<V, E>, VisualizationComponent<V,E>
		// Layout<Marking, Edge> layout = new FRLayout<Marking, Edge>(g);
		Layout<V, E> layout = new ISOMLayout<V, E>(graph);
		// Layout<Marking, Edge> layout = new KKLayout<Marking, Edge>(g);

		layout.setSize(new Dimension(750, 550));
		VisualizationViewer<V, E> vv = new VisualizationViewer<V, E>(layout);
		vv.setPreferredSize(new Dimension(800, 600));
		// Show vertex and edge labels
		vv.getRenderContext().setVertexLabelTransformer(vertexLabelTransformer);
		vv.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
		// Create a graph mouse and add it to the visualization component
		DefaultModalGraphMouse<Object, Object> gm = new DefaultModalGraphMouse<Object, Object>();
		gm.setMode(ModalGraphMouse.Mode.PICKING);
		vv.setGraphMouse(gm);
		return vv;
	}

}
