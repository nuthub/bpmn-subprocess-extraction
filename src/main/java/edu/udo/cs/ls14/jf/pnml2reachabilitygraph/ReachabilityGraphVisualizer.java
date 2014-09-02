package edu.udo.cs.ls14.jf.pnml2reachabilitygraph;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model.Edge;
import edu.udo.cs.ls14.jf.pnml2reachabilitygraph.model.Marking;

public class ReachabilityGraphVisualizer {

	public JFrame visualize(Graph<Marking, Edge> graph) {
		// Layout<V, E>, VisualizationComponent<V,E>
		// Layout<Marking, Edge> layout = new FRLayout<Marking, Edge>(g);
		Layout<Marking, Edge> layout = new ISOMLayout<Marking, Edge>(graph);
		// Layout<Marking, Edge> layout = new KKLayout<Marking, Edge>(g);

		layout.setSize(new Dimension(750, 550));
		VisualizationViewer<Marking, Edge> vv = new VisualizationViewer<Marking, Edge>(
				layout);
		vv.setPreferredSize(new Dimension(800, 600));
		// Show vertex and edge labels
		vv.getRenderContext().setVertexLabelTransformer(
				new Transformer<Marking, String>() {
					public String transform(Marking m) {
						return null;
					}
				});
		vv.getRenderContext().setEdgeLabelTransformer(
				new Transformer<Edge, String>() {
					public String transform(Edge e) {
						return e.getT();
					}
				});
		// Create a graph mouse and add it to the visualization component
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(ModalGraphMouse.Mode.PICKING);
		vv.setGraphMouse(gm);
		// Create Window / App / JFrame
		JFrame frame = new JFrame("Interactive Graph View 1");
		frame.getContentPane().add(vv);
		frame.pack();
		return frame;
	}

}
