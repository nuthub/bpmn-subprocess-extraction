package edu.udo.cs.ls14.jf.bpmn.matching;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

public interface ProcessMatcher {
	ProcessMatching match(ProcessAnalysis analysis1,
			ProcessAnalysis analysis2) throws Exception;
}