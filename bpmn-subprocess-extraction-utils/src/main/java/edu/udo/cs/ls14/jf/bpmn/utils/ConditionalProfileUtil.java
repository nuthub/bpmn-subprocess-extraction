package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;

import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;

/**
 * Utility methods for ConditionalProfile objects. At the moment methods for
 * debug output are the only content of this class.
 * 
 * @author Julian Flake
 *
 */
public class ConditionalProfileUtil {

	private static final String NL = System.getProperty("line.separator");

	public static String toTex(ConditionalProfile cp, List<String> nodes) {
		Map<String, String> conditions = new HashMap<String, String>();
		for (FlowNode key : cp.getRelations().keySet()) {
			List<String> expressions = new ArrayList<String>();
			for (FormalExpression exp : cp.getRelations().get(key)) {
				expressions.add(exp.getBody());
			}
			Collections.sort(expressions);
			String c = "";
			c += StringUtils.join(expressions, ",");
			conditions.put(key.getName(), c);
		}

		List<String> tuples = new ArrayList<String>();
		for (String name : nodes) {
			String c = " (";
			c += "\\texttt{" + name.replaceAll("\\_", "\\\\_") + "}";
			c += ", ";
			if (!conditions.containsKey(name)
					|| conditions.get(name).equals("")) {
				c += "\\emptyset ";
			} else {
				c += "\\{" + conditions.get(name) + "\\}";
			}
			c += ")";
			tuples.add(c);
		}

		StringBuffer sb = new StringBuffer();
		sb.append("\\{" + NL);
		sb.append(StringUtils.join(tuples, ", " + NL));
		sb.append(NL + "\\}");

		return sb.toString();

	}

	public static void writeDebugFiles(String debugFilesDir, String basename,
			List<String> nodes, ConditionalProfile cp, String suffix)
			throws IOException {
		IOUtil.writeTxtFile(toTex(cp, nodes), debugFilesDir + basename + "-"
				+ suffix + ".tex");

	}
}
