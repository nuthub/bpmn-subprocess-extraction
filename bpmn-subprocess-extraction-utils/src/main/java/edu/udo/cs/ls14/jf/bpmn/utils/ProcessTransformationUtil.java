package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

/**
 * Helper methods for ProcessTransformation objects.
 * 
 * @author Julian Flake
 *
 */
public class ProcessTransformationUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessTransformationUtil.class);

	/**
	 * Writes results to files and fixes import locations.
	 * 
	 * @param targetDir
	 *            directory to where the results shall be written
	 * @param transformation
	 *            ProcessTransformation object
	 * @throws IOException
	 *             is thrown if an IO error occurs
	 */
	public static void writeResults(String targetDir,
			ProcessTransformation transformation) throws Exception {
		Map<String, Definitions> results = transformation.getResults();

		new File(targetDir).mkdirs();
		Bpmn2ResourceSet resSetOut = Bpmn2ResourceSet.getInstance();

		// Create resources for all definitions
		Map<String, Resource> resMap = new HashMap<String, Resource>();
		for (Map.Entry<String, Definitions> entry : results.entrySet()) {
			URI uri = URI.createFileURI((targetDir + "/" + entry.getKey())
					.replaceAll("//", "/"));
			Resource res = resSetOut.createResource(uri);
			res.getContents().add(entry.getValue());
			resMap.put(entry.getKey(), res);
		}
		// Write all resources
		for (Map.Entry<String, Resource> entry : resMap.entrySet()) {
			entry.getValue().save(null);
			LOG.info("Written " + entry.getKey());
		}
		LOG.info("Fix imports.");
		fixImports(resMap, targetDir);
		LOG.info("Imports fixed.");
	}

	private static void fixImports(Map<String, Resource> resMap,
			String targetDir) throws IOException {
		// Write files, fix locations of imports, write files
		for (Map.Entry<String, Resource> entry : resMap.entrySet()) {
			Resource res = entry.getValue();
			res.save(null);
			((Definitions) res.getContents().get(0))
					.getImports()
					.stream()
					.filter(i -> resMap.keySet().contains(i.getLocation()))
					.forEach(
							i -> i.setLocation("file://" + targetDir
									+ i.getLocation()));
			res.save(null);
			LOG.info("Fixed " + res.getURI());
		}

	}

	/**
	 * Writes a bpmntransformation object to an XML file. Ensure, that factory
	 * and package are registered before.
	 * 
	 * @param transformation
	 *            ProcessTransformation object to write
	 * @throws IOException
	 *             is thrown if an IO error occurs
	 */
	public static void writeToFile(String filename,
			ProcessTransformation transformation) throws IOException {
		Resource res = new BpmnTransformationResourceFactoryImpl()
				.createResource(URI.createFileURI(filename));
		res.getContents().add(transformation);
		res.save(null);
		LOG.info("Written extraction result to " + filename);
	}

}
