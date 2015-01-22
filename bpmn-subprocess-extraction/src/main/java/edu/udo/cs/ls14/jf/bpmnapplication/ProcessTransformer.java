package edu.udo.cs.ls14.jf.bpmnapplication;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

public interface ProcessTransformer {

	ProcessExtraction transform(ProcessMatching matching)
			throws Exception;

}