package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class ProcessTransformationUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessTransformationUtil.class);

	/**
	 * Also fixes the import locations
	 * 
	 * @param targetDir
	 * @param extraction
	 * @throws IOException
	 */
	public static void writeResults(String targetDir,
			ProcessTransformation extraction) throws Exception {
		writeResults(targetDir, extraction.getResults());
	}

	public static void writeResults(String targetDir,
			Map<String, Definitions> results) throws Exception {

		new File(targetDir).mkdirs();
		Bpmn2ResourceSet resSetOut = Bpmn2ResourceSet.getInstance();

		// Create resources for all definitions
		Map<String, Bpmn2Resource> resMap = new HashMap<String, Bpmn2Resource>();
		for (Map.Entry<String, Definitions> entry : results.entrySet()) {
			String uriStr = (targetDir + "/" + entry.getKey()).replaceAll("//",
					"/");
			Bpmn2Resource res = resSetOut.createResource(uriStr,
					entry.getValue());
			resMap.put(entry.getKey(), res);
		}
		// Write all resources
		for (Map.Entry<String, Bpmn2Resource> entry : resMap.entrySet()) {
			entry.getValue().save(null);
			LOG.info("Written " + entry.getKey());
		}
		LOG.info("Fix imports.");
		fixImports(resMap, targetDir);
		LOG.info("Imports fixed.");
	}

	private static void fixImports(Map<String, Bpmn2Resource> resMap,
			String targetDir) throws IOException {
		// Write files, fix locations of imports, write files
		for (Map.Entry<String, Bpmn2Resource> entry : resMap.entrySet()) {
			Bpmn2Resource res = entry.getValue();
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
	 * Ensure, that factories and packages are registered
	 * 
	 * @param extraction
	 * @return
	 * @throws Exception
	 */
	public static void writeToFile(String filename,
			ProcessTransformation extraction) throws IOException {
		Resource res = new BpmnTransformationResourceFactoryImpl()
				.createResource(URI.createFileURI(filename));
		System.out.println(res);
		res.getContents().add(extraction);
		res.save(null);
		LOG.info("Written extraction result to " + filename);
	}

	public static ProcessTransformation setAllIds(
			ProcessTransformation transformation) {

		// TODO Auto-generated method stub
		return transformation;
	}

}
