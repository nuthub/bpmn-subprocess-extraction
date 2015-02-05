package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.transformation.FragmentPairFilterTrivial;

public class FragmentPairFilterTrivialDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching.setFragmentMatching(FragmentPairFilterTrivial.filter(matching
				.getFragmentMatching()));
		execution.setVariable("matching", matching);
	}
}