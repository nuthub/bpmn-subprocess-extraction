package edu.udo.cs.ls14.jf.bpmn.extraction.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnapplication.ProcessTransformer;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

@Deprecated
public class ProcessExtractorDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		ProcessTransformer transformer = new ProcessTransformerImpl();
		ProcessExtraction extraction = transformer.transform(matching);
		execution.setVariable("extraction", extraction);
	}

}
