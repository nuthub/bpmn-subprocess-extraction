package edu.udo.cs.ls14.jf.bpmn.matching.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

public class FullNodeMatchingDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching.setNodeMatching(ProcessMatchingFactory.getFullNodeMatching(
				matching.getAnalysis1().getDefinitions(), matching
						.getAnalysis2().getDefinitions()));
		execution.setVariable("matching", matching);
	}

}
