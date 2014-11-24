package edu.udo.cs.ls14.jf.bpmn.matching.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

public class FullFragmentMatchingDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching.setFragmentMatching(ProcessMatchingFactory
				.getFullFragmentMatching(matching.getAnalysis1(),
						matching.getAnalysis2()));
		System.out.println(matching.getFragmentMatching().getPairs().size()
				+ " possible fragment pairs.");
		execution.setVariable("matching", matching);
	}

}
