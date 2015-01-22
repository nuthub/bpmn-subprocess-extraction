package edu.udo.cs.ls14.jf.bpmn.utils;

import java.util.UUID;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public class ProcessAnalysisFactory {

	public static ProcessAnalysis createAnalysis(Definitions definitions) {
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		Bpmn2ResourceSet
				.getInstance()
				.createResource(
						URI.createFileURI(UUID.randomUUID().toString()
								+ ".bpmnanalysis")).getContents().add(analysis);
		analysis.setDefinitions(definitions);
		return analysis;
	}
}
