package edu.udo.cs.ls14.jf.bpmn.matching.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.FragmentPairFilterNodes;

public class FragmentPairFilterNodesDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching.setFragmentMatching(FragmentPairFilterNodes.filter(
				matching.getFragmentMatching(), matching.getNodeMatching()));
		execution.setVariable("matching", matching);

	}

}
