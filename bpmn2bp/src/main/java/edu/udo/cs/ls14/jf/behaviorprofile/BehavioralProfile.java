package edu.udo.cs.ls14.jf.behaviorprofile;

import java.util.Set;

import edu.udo.cs.ls14.jf.reachabilitygraph.Trace;

public class BehavioralProfile {

	private Matrix<String, Boolean> m;

	public BehavioralProfile() {
		m = new Matrix<String, Boolean>(false);
	}

	public void generateFromTraces(Set<Trace> traces) {
		for (Trace t : traces) {
			for (int i = 0; i < t.size() - 1; i++) {
				m.put(t.get(i), t.get(i + 1), true);
			}
		}
	}

	private Relation get(String a, String b) {
		if (m.get(a, b) && m.get(b, a)) {
			return Relation.PARALLEL;
		}
		if (m.get(a, b) && !m.get(b, a)) {
			return Relation.DIRECT_SUCCESSOR;
		}
		if (!m.get(a, b) && m.get(b, a)) {
			return Relation.DIRECT_PREDECESSOR;
		}
		return Relation.NO_SUCCESSION;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		// System.out.println("→");
		// System.out.println("←");
		// System.out.println("∥");
		// System.out.println("#");
		//
		for (String x : m.getKeys()) {
			for (String y : m.getKeys()) {
				sb.append(x + " ");
				Relation r = get(x, y);
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
				sb.append(" " + y);
				sb.append(System.getProperty("line.separator"));
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
