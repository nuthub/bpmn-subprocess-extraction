package edu.udo.cs.ls14.jf.bpmnanalysis.util;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;

/**
 * Helper methods for ProcessAnalysis objects.
 * 
 * TODO: move to generated class.
 * 
 * @author Julian Flake
 *
 */
public class ProcessAnalysisUtil {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessAnalysisUtil.class);
	public static final String TRACEPROFILE = "traceProfile";
	public static final String BEHAVIORALPROFILE = "behavioralProfile";
	public static final String PROCESSTRUCTURETREE = "pst";
	public static final String CONDITIONALPROFILE = "conditionalProfile";

	public static void writeToFile(String filename, ProcessAnalysis analysis)
			throws IOException {
		Resource res = new BpmnAnalysisResourceFactoryImpl().createResource(URI
				.createFileURI(filename));
		res.getContents().add(analysis);
		res.save(null);
		LOG.info("Written analysis result to " + filename);

	}

	public static ProcessStructureTree getProcessStructureTree(
			ProcessAnalysis analysis) {
		return (ProcessStructureTree) analysis.getResults().get(
				PROCESSTRUCTURETREE);
	}

	public static TraceProfile getTraceProfile(ProcessAnalysis analysis) {
		return (TraceProfile) analysis.getResults().get(TRACEPROFILE);
	}

	public static BehavioralProfile getBehavioralProfile(
			ProcessAnalysis analysis) {
		return (BehavioralProfile) analysis.getResults().get(BEHAVIORALPROFILE);
	}

	public static ConditionalProfile getConditionalProfile(
			ProcessAnalysis analysis) {
		return (ConditionalProfile) analysis.getResults().get(
				CONDITIONALPROFILE);
	}


}
