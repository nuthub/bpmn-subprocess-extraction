package edu.udo.cs.ls14.jf.analysis.behaviorprofile;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.bpmn2.FlowNode;
import org.javatuples.Pair;

@XmlRootElement
@Deprecated
public class BehavioralProfileOld extends
		HashMap<Pair<FlowNode, FlowNode>, RelationType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6859323470574181105L;

	public RelationType get(FlowNode a, FlowNode b) {
		return get(Pair.with(a, b));
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<Pair<FlowNode, FlowNode>, RelationType> entry : entrySet()) {
			FlowNode a = entry.getKey().getValue0();
			FlowNode b = entry.getKey().getValue1();
			RelationType r = entry.getValue();
			sb.append(a.getName() + " ");
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
			sb.append(" " + b.getName());
			sb.append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}
}
