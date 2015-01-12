package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceImpl;

public class ProcessMatchingUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingUtil.class);

	public static void writeToFile(String filename, ProcessMatching matching)
			throws IOException {
		Resource res = Bpmn2ResourceSet.getInstance().createResource(
				URI.createFileURI(filename));
		res.getContents().add(matching);
		res.save(null);
		LOG.info("Written analysis result to " + filename);
	}

	public static ProcessMatching loadFromFile(String filename)
			throws IOException {
		Resource res = Bpmn2ResourceSet.getInstance().createResource(
				URI.createFileURI(filename));
		res.load(null);
		return (ProcessMatching) res.getContents().get(0);
	}

}
