package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairFilterConditions;

public class FragmentPairFilterConditionsDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching.setFragmentMatching(FragmentPairFilterConditions.filter(
				matching.getFragmentMatching(), matching.getNodeMatching(),
				matching.getAnalysis1(), matching.getAnalysis2()));
		execution.setVariable("matching", matching);
	}

}
