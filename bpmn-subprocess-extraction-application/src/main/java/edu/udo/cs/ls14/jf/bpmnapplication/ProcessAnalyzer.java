package edu.udo.cs.ls14.jf.bpmnapplication;

import java.util.List;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public interface ProcessAnalyzer {

	ProcessAnalysis analyze(Definitions definitions) throws Exception;
	
	ProcessAnalysis analyzeAndDebug(Definitions definitions,
			String pathname, String basename, String debugFilesDir,
			List<String> nodes) throws Exception;
}