package edu.udo.cs.ls14.jf.bpmn.matching.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.nodematching.NodePairFilter;

public class NodePairFilterDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching.setNodeMatching(NodePairFilter.filter(matching
				.getNodeMatching()));
		execution.setVariable("matching", matching);
	}
}
