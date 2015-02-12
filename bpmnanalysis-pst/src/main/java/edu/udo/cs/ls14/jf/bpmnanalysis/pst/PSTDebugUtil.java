package edu.udo.cs.ls14.jf.bpmnanalysis.pst;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Helper method to write debug files.
 * 
 * @author Julian Flake
 *
 */
public class PSTDebugUtil {

	/**
	 * write all intermediate artifacts to a given path. Make sure path ends
	 * with "/".
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
	 * @throws IOException
	 *             if an IO error occurs
	 */
	public static void writeDebugFiles(String path, String basename,
			PSTBuilder builder, int fontsizeGraph, int fontsizeSpTree,
			int fontsizePst) throws IOException {
		// dot output
		writeDot(path, basename + "-undirectedgraph",
				builder.undirectedGraphToDot(fontsizeGraph));
		writeDot(path, basename + "-spanningtree",
				builder.spanningTreeToDot(fontsizeSpTree));
		writeTxtFile(builder.cycleEqClsToTex(), path + basename
				+ "-ceClasses.tex");
		writeTxtFile(builder.sortedCycleEqClsToTex(), path + basename
				+ "-sortedCeClasses.tex");
		writeTxtFile(builder.canonicalFragmentsToTex(), path + basename
				+ "-canonicalFragments.tex");
		writeDot(path, basename + "-pst",
				builder.structureTreeToDot(fontsizePst));
	}

	/*
	 * Path has to end with /.
	 */
	private static void writeDot(String path, String basename, String dot)
			throws IOException {
		new File(path).mkdirs();
		String filePrefix = path + basename;
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
