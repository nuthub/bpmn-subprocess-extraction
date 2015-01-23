package edu.udo.cs.ls14.jf.bpmnapplication;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public interface ProcessTransformer {

	ProcessTransformation transform(ProcessMatching matching)
			throws Exception;

}