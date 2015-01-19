package edu.udo.cs.ls14.jf.analysis.pst;

import edu.udo.cs.ls14.jf.bpmn.utils.IOUtil;

public class PSTDebugUtil {

	public static void writeDebugFiles(String path, String basename,
			PSTBuilder builder, int fontsizeGraph, int fontsizeSpTree,
			int fontsizePst) throws Exception {
		// dot output
		IOUtil.writeDot(path, basename + "-undirectedgraph",
				builder.undirectedGraphToDot(fontsizeGraph));
		IOUtil.writeDot(path, basename + "-spanningtree",
				builder.spanningTreeToDot(fontsizeSpTree));
		IOUtil.writeTxtFile(builder.cycleEqClsToTex(), path + basename
				+ "-ceClasses.tex");
		IOUtil.writeTxtFile(builder.sortedCycleEqClsToTex(), path + basename
				+ "-sortedCeClasses.tex");
		IOUtil.writeTxtFile(builder.canonicalFragmentsToTex(), path + basename
				+ "-canonicalFragments.tex");
		IOUtil.writeDot(path, basename + "-pst",
				builder.structureTreeToDot(fontsizePst));
	}
}
