package edu.udo.cs.ls14.jf.bpmn.extraction.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;

public class FragmentExtractorDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		ProcessExtraction extraction = FragmentExtractor.extract(matching);
		execution.setVariable("extraction", extraction);
	}

}
