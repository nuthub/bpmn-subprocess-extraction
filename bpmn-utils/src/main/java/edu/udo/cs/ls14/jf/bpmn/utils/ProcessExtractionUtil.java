package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

public class ProcessExtractionUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessExtractionUtil.class);

	/**
	 * Also fixes the import locations
	 * @param targetDir
	 * @param extraction
	 * @throws IOException
	 */
	public static void writeResults(String targetDir,
			ProcessExtraction extraction) throws IOException {

		new File(targetDir).mkdirs();
		Bpmn2ResourceSet resSetOut = new Bpmn2ResourceSet(targetDir);
		for (Entry<String, Definitions> entry : extraction.getResults()
				.entrySet()) {
			Resource res = resSetOut.createResource(entry.getKey(),
					entry.getValue());
			res.save(null);
			((Definitions) res.getContents().get(0))
					.getImports()
					.stream()
					.filter(i -> extraction.getResults().keySet()
							.contains(i.getLocation()))
					.forEach(
							i -> i.setLocation(targetDir + "/"
									+ i.getLocation()));
			res.save(null);
			LOG.info("Written " + res.getURI());
		}
	}

}
