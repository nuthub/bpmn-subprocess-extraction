package edu.udo.cs.ls14.jf.bpmn.utils;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public class ProcessAnalysisUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessAnalysisUtil.class);

	public static void writeToFile(String filename, ProcessAnalysis analysis)
			throws IOException {
		Resource res = ((Resource.Factory) Resource.Factory.Registry.INSTANCE
				.getExtensionToFactoryMap().get("bpmnanalysis"))
				.createResource(URI.createFileURI(filename));
		res.getContents().add(analysis);
		res.save(null);
		LOG.info("Written analysis result to " + filename);

	}

}
