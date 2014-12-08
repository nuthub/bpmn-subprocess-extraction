package edu.udo.cs.ls14.jf.bpmn.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DotUtil {

	private static final String DOT_PATH = "/usr/bin/dot";

	private static Runtime rt = Runtime.getRuntime();

	public static void writeDot(String path, String basename, String dot,
			String type) throws Exception {
		String filePrefix = path + (path.endsWith("/") ? "" : "/") + basename;
		Files.write(Paths.get(path + basename + ".dot"), dot.getBytes());
		if (type.equals("ps") && type.equals("png")) {
			throw new Exception("Only png and ps types are supported");
		}
		String[] args = { DOT_PATH, "-T" + type, "/tmp/" + basename + ".dot",
				"-o", filePrefix + "." + type };
		java.lang.Process p = rt.exec(args);
		p.waitFor();

	}

}
