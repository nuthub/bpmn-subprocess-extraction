package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterNodesImpl;

public class FragmentPairFilterNodesDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentPairFilterNodesImpl().filter(matching);
		execution.setVariable("matching", matching);

	}

}