package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FullFragmentMatchingImpl;

/**
 * Delegates work to FullFragmentMatching service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnmatching.ws.FullFragmentMatchingSEI
 * 
 * @author Julian Flake
 *
 */
public class FullFragmentMatchingDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FullFragmentMatchingImpl()
				.createFullFragmentMatching(matching);
		execution.setVariable("matching", matching);
	}

}
