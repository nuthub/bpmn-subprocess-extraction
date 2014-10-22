package edu.udo.cs.ls14.jf.behaviorprofile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.reachabilitygraph.Trace;

public class BehavioralProfile {

	private static final Logger LOG = LoggerFactory.getLogger(BehavioralProfile.class);
	
	private Matrix<FlowNode, Boolean> m;
	private Map<String, FlowNode> nodes;

	public BehavioralProfile() {
		m = new Matrix<FlowNode, Boolean>(false);
		nodes = new HashMap<String, FlowNode>();
	}

	public void generateFromTraces(Process process, Set<Trace> traces) {
		for (FlowElement elem : process.getFlowElements()) {
			if (elem instanceof FlowNode) {
				nodes.put(elem.getId(), (FlowNode) elem);
			}
		}
		for (Trace t : traces) {
			for (int i = 0; i < t.size() - 1; i++) {
				if(!nodes.containsKey(t.get(i))) {
					LOG.debug(t.get(i) + " not contained in " + nodes);
				}
				if(!nodes.containsKey(t.get(i+1))) {
					LOG.debug(t.get(i+1) + " not contained in " + nodes);
				}
				m.put(nodes.get(t.get(i)), nodes.get(t.get(i + 1)), true);
			}
		}
	}

	public RelationType get(FlowNode a, FlowNode b) {
		if (m.get(a, b) && m.get(b, a)) {
			return RelationType.PARALLEL;
		}
		if (m.get(a, b) && !m.get(b, a)) {
			return RelationType.DIRECT_SUCCESSOR;
		}
		if (!m.get(a, b) && m.get(b, a)) {
			return RelationType.DIRECT_PREDECESSOR;
		}
		return RelationType.NO_SUCCESSION;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (FlowNode x : m.getKeys()) {
			for (FlowNode y : m.getKeys()) {
				sb.append(x.getName() + " ");
				RelationType r = get(x, y);
				switch (r) {
				case DIRECT_SUCCESSOR:
					sb.append("→");
					break;
				case DIRECT_PREDECESSOR:
					sb.append("←");
					break;
				case PARALLEL:
					sb.append("∥");
					break;
				case NO_SUCCESSION:
					sb.append("#");
					break;
				}
				sb.append(" " + y.getName());
				sb.append(System.getProperty("line.separator"));
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
