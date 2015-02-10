package edu.udo.cs.ls14.jf.analysis.pst;

import java.io.IOException;

import edu.udo.cs.ls14.jf.bpmn.utils.IOUtil;

/**
 * Helper method to write debug files.
 * 
 * @author Julian Flake
 *
 */
public class PSTDebugUtil {

	/**
	 * write all intermediate artifacts to a given path
	 * 
	 * @param path
	 *            the target directory
	 * @param basename
	 *            basename (prefix for all files)
	 * @param builder
	 *            The builder which has been used to create a PST
	 * @param fontsizeGraph
	 *            Font size of in undirected graph
	 * @param fontsizeSpTree
	 *            Font size of spanning tree
	 * @param fontsizePst
	 *            Font size of process structure tree
	 * @throws Exception
	 *             if an IO error occurs
	 */
	public static void writeDebugFiles(String path, String basename,
			PSTBuilder builder, int fontsizeGraph, int fontsizeSpTree,
			int fontsizePst) throws IOException {
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
