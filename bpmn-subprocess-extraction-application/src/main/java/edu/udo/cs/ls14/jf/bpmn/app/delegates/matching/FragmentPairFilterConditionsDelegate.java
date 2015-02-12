package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterConditionsImpl;

/**
 * Delegates work to FragmentPairFilterConditions service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterConditionsSEI
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairFilterConditionsDelegate implements JavaDelegate {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentPairFilterConditionsImpl().filter(matching);
		execution.setVariable("matching", matching);
	}

}
