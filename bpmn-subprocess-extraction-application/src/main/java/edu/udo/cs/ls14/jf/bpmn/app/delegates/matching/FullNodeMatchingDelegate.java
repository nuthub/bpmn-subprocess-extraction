package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FullNodeMatchingImpl;

/**
 * Delegates work to FullNodeMatching service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnmatching.ws.FullNodeMatchingSEI
 * 
 * @author Julian Flake
 *
 */
public class FullNodeMatchingDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FullNodeMatchingImpl().createFullNodeMatching(matching);
		execution.setVariable("matching", matching);
	}

}
