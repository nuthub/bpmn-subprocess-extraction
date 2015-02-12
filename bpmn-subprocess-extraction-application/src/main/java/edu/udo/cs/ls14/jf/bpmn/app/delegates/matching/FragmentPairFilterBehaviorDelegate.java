package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterBehaviorImpl;

/**
 * Delegates work to FragmentPairFilterBehavior service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterBehaviorSEI
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairFilterBehaviorDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentPairFilterBehaviorImpl().filter(matching);
		execution.setVariable("matching", matching);
	}

}
