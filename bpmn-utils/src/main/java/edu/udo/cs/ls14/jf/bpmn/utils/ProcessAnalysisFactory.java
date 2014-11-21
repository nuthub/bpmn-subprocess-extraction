package edu.udo.cs.ls14.jf.bpmn.utils;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public class ProcessAnalysisFactory {

	public static ProcessAnalysis createAnalysis(Definitions definitions) {
		ProcessAnalysis analysis = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		analysis.setDefinitions(definitions);
		return analysis;
	}
}
