package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairJointerSequentialImpl;

/**
 * Delegates work to FragmentPairJointerSequential service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairJointerSequentialSEI
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairJointerSequentialDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentPairJointerSequentialImpl().join(matching);
		execution.setVariable("matching", matching);
	}
}
