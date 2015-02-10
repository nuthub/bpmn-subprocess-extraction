package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * IO related helper methods. Including DOT invoker.
 * 
 * @author Julian Flake
 *
 */
public class IOUtil {

	private static final String DOT_PATH = "/usr/bin/dot";

	private static Runtime rt = Runtime.getRuntime();

	public static void writeAndRunDot(String path, String basename, String dot,
			String type) throws Exception {
		writeDot(path, basename, dot);
		if (type.equals("ps") && type.equals("png")) {
			throw new Exception("Only png and ps types are supported");
		}
		String filePrefix = path + (path.endsWith("/") ? "" : "/") + basename;
		String[] args = { DOT_PATH, "-T" + type, filePrefix + ".dot", "-o",
				filePrefix + "." + type };
		java.lang.Process p = rt.exec(args);
		p.waitFor();
	}

	public static void writeDot(String path, String basename, String dot)
			throws Exception {
		new File(path).mkdirs();
		String filePrefix = path + (path.endsWith("/") ? "" : "/") + basename;
		Files.write(Paths.get(filePrefix + ".dot"), dot.getBytes());
	}

	public static void writeTxtFile(String string, String filename)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		out.write(string);
		out.flush();
		out.close();
	}

}
