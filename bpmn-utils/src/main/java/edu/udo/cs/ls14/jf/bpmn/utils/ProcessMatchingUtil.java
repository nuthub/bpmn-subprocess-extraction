package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;

public class ProcessMatchingUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessMatchingUtil.class);

	public static void writeToFile(String filename, ProcessMatching matching)
			throws IOException {
		// TODO retrieve from factory
		Resource res = new BpmnMatchingResourceFactoryImpl().createResource(URI
				.createFileURI(filename));
		res.getContents().add(matching);
		res.save(null);
		LOG.info("Written analysis result to " + filename);

	}

}
