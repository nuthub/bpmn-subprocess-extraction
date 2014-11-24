package edu.udo.cs.ls14.jf.bpmnapplication;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.transformation.ProcessExtractor;

public class FullFlow {

	public ProcessExtraction run(Definitions definitions1,
			Definitions definitions2) throws Exception {
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(definitions1);
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(definitions2);
		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		ProcessExtraction extraction = ProcessExtractor.extract(matching);
		return extraction;
	}
}
