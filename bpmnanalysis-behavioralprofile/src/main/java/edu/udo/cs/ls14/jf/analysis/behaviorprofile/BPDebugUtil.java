package edu.udo.cs.ls14.jf.analysis.behaviorprofile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelationType;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

/**
 * Helper method to write analysis artifacts of behavioral profiling.
 * 
 * @author Julian Flake
 *
 */
public class BPDebugUtil {

	/**
	 * Get and write debug artifacts of all steps for behavioral profiling.
	 * 
	 * @param pathname
	 *            target directory
	 * @param basename
	 *            prefix for all artifacts
	 * @param process
	 *            process model
	 * @param converter
	 *            BPMN / PTNet converter
	 * @param ptnet
	 *            converted PTNet
	 * @param rg
	 *            reachability Graph of PTNet
	 * @param nodes
	 *            List of node names (relevant ordering)
	 * @param traceProfile
	 *            resulting TraceProfile
	 * @param bp
	 *            resulting Behavioral Profile
	 * @throws Exception
	 *             if an error occurs
	 */
	public static void writeDebugFiles(String pathname, String basename,
			Process process, Bpmn2PtnetConverter converter,
			PetriNetHLAPI ptnet, ReachabilityGraph rg, List<String> nodes,
			TraceProfile traceProfile, BehavioralProfile bp) throws Exception {
		// Create output directory
		new File(pathname).mkdirs();

		// output petrinet
		converter.saveToPnmlFile(pathname + basename + "-ptnet.pnml");
		writeDot(pathname, basename + "-ptnet", converter.toDot());
		// output reachability graph
		String dot = rg.toDot(process);
		writeDot(pathname, basename + "-reachabilityGraph", dot);
		// output traces
		writeTxtFile(BPDebugUtil.tracesToString(traceProfile), pathname
				+ basename + "-traces.txt");
		System.out.println("Wrote traces to " + pathname + basename
				+ "-traces.txt");
		// output direct succession relation
		writeTxtFile(BPDebugUtil.bpToSuccRelTabular(nodes, bp), pathname
				+ basename + "-nachfolgerelation.tex");
		System.out.println("Wrote succession relation to " + pathname
				+ basename + "-nachfolgerelation.tex");
		// output behavioral profile
		writeTxtFile(BPDebugUtil.bpToTabular(nodes, bp), pathname
				+ basename + "-verhaltensprofil.tex");
		System.out.println("Wrote Behavioral Profile to " + pathname + basename
				+ "-verhaltensprofil.tex");
	}

	private static String bpToSuccRelTabular(List<String> nodes,
			BehavioralProfile bp) {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		// begin table
		sb.append("\\begin{tabular}{|r||");
		for (int i = 0; i < nodes.size(); i++) {
			sb.append("c|");
		}
		sb.append("}" + nl);
		// Header
		sb.append("\\hline" + nl);
		sb.append("$\\succ_M$ ");
		for (String node : nodes) {
			sb.append(" & " + node.replaceAll("\\_", "\\\\_"));
		}
		// rows
		sb.append("\\\\\\hline\\hline" + nl);
		for (String row : nodes) {
			sb.append(row.replaceAll("\\_", "\\\\_"));
			for (String col : nodes) {
				sb.append(" & ");
				for (BehavioralRelation rel : bp.getRelations()) {
					if (rel.getLeft().getName().equals(row)
							&& rel.getRight().getName().equals(col)
							&& (rel.getRelation() == BehavioralRelationType.PARALLEL || rel
									.getRelation() == BehavioralRelationType.DIRECT_SUCCESSOR)) {
						sb.append("$\\bullet$");
					}
				}
			}
			sb.append(" \\\\\\hline" + nl);
		}
		// end table
		sb.append("\\end{tabular}" + nl);
		return sb.toString();
	}

	private static String bpToTabular(List<String> nodes, BehavioralProfile bp) {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		// begin table
		sb.append("\\begin{tabular}{|r||");
		for (int i = 0; i < nodes.size(); i++) {
			sb.append("c|");
		}
		sb.append("}" + nl);
		// Header
		sb.append("\\hline" + nl);
		sb.append("$BP_M$ ");
		for (String node : nodes) {
			sb.append(" & " + node.replaceAll("\\_", "\\\\_"));
		}
		// rows
		sb.append("\\\\\\hline\\hline" + nl);
		for (String row : nodes) {
			sb.append(row.replaceAll("\\_", "\\\\_"));
			for (String col : nodes) {
				sb.append(" & ");
				for (BehavioralRelation rel : bp.getRelations()) {
					if (rel.getLeft().getName().equals(row)
							&& rel.getRight().getName().equals(col)) {
						switch (rel.getRelation()) {
						case NO_SUCCESSION:
							sb.append("\\#");
							break;
						case DIRECT_SUCCESSOR:
							sb.append("$\\rightarrow$");
							break;
						case DIRECT_PREDECESSOR:
							sb.append("$\\leftarrow$");
							break;
						case PARALLEL:
							sb.append("$\\parallel$");
							break;
						}
					}
				}
			}
			sb.append(" \\\\\\hline" + nl);
		}
		// end table
		sb.append("\\end{tabular}" + nl);
		return sb.toString();
	}

	/*
	 * TODO: move elsewhere
	 */
	private static String tracesToString(TraceProfile traceProfile) {
		String nl = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer();
		for (Trace trace : traceProfile.getTraces()) {
			List<String> nodeList = new ArrayList<String>();
			for (FlowNode node : trace.getNodes()) {
				nodeList.add(node.getName() != null
						&& !node.getName().equals("") ? node.getName() : node
						.getId());
			}
			sb.append(StringUtils.join(nodeList, ","));
			if (trace.isFinished()) {
				sb.append(" .");
			} else {
				sb.append(" ...");
			}
			sb.append(nl);
		}
		return sb.toString();
	}
	private static void writeDot(String path, String basename, String dot)
			throws IOException {
		new File(path).mkdirs();
		String filePrefix = path + (path.endsWith("/") ? "" : "/") + basename;
		Files.write(Paths.get(filePrefix + ".dot"), dot.getBytes());
	}

	private static void writeTxtFile(String string, String filename)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		out.write(string);
		out.flush();
		out.close();
	}
	
}
