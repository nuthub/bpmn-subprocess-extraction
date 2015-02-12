package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterNestingsImpl;

/**
 * Delegates work to FragmentPairFilterNestings service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNestingsSEI
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairFilterNestingsDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentPairFilterNestingsImpl().filter(matching);
		execution.setVariable("matching", matching);
	}

}
