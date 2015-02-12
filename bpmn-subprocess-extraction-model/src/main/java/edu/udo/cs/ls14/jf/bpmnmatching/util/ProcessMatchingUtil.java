package edu.udo.cs.ls14.jf.bpmnmatching.util;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

/**
 * Helper methods for ProcessMatching objects.
 * 
 * @author Julian Flake
 *
 */
public class ProcessMatchingUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingUtil.class);

	/**
	 * Save a given ProcessMatching object to an XML file.
	 * 
	 * @param filename
	 *            where to write the XML file
	 * @param given
	 *            matching ProcessMatching object
	 * @throws IOException
	 *             if file could not be written
	 */
	public static void writeToFile(String filename, ProcessMatching matching)
			throws IOException {
		Resource res = new BpmnMatchingResourceFactoryImpl().createResource(URI
				.createFileURI(filename));
		res.getContents().add(matching);
		res.save(null);
		LOG.info("Written analysis result to " + filename);
	}

	/**
	 * Load a ProcessMatching object from a given XML file
	 * 
	 * @param filename
	 *            where to load the object from
	 * @return loaded ProcessMatching object
	 * @throws IOException
	 *             if file could not be read
	 */
	public static ProcessMatching loadFromFile(String filename)
			throws IOException {
		Resource res = Bpmn2ResourceSet.getInstance().createResource(
				URI.createFileURI(filename));
		res.load(null);
		return (ProcessMatching) res.getContents().get(0);
	}

}
