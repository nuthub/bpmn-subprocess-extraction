package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Import;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

public class ProcessExtractionUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessExtractionUtil.class);

	/**
	 * Also fixes the import locations
	 * 
	 * @param targetDir
	 * @param extraction
	 * @throws IOException
	 */
	public static void writeResults(String targetDir,
			ProcessExtraction extraction) throws IOException {
		new File(targetDir).mkdirs();
		Bpmn2ResourceSet resSetOut = new Bpmn2ResourceSet(targetDir);

		// Create resources for all definitions
		Map<String, Resource> resMap = new HashMap<String, Resource>();
		for (Map.Entry<String, Definitions> entry : extraction.getResults()
				.entrySet()) {
			resMap.put(entry.getKey(),
					resSetOut.createResource(entry.getKey(), entry.getValue()));
		}

		// Write files, fix locations of imports, write files
		for (Map.Entry<String, Resource> entry : resMap.entrySet()) {
			entry.getValue().save(null);
		}
		for (Map.Entry<String, Resource> entry : resMap.entrySet()) {
			Definitions defs = (Definitions) entry.getValue().getContents()
					.get(0);
			defs.getImports()
					.stream()
					.filter(i -> extraction.getResults().keySet()
							.contains(i.getLocation()))
					.forEach(
							i -> i.setLocation("file://" + targetDir
									+ i.getLocation()));
			entry.getValue().save(null);
			defs.getImports().stream().forEach(i -> System.out.println(i.eResource()));
			entry.getValue().save(null);
//			entry.getValue().save(null);
			LOG.info("Written " + entry.getValue().getURI());
		}
	}
}
