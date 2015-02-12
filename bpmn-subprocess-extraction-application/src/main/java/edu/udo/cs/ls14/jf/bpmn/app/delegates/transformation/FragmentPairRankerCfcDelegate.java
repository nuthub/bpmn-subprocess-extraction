package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentPairRankerCFCImpl;

/**
 * Delegates work to FragmentPairRankerCFC service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentPairRankerCFCSEI
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairRankerCfcDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentPairRankerCFCImpl().rank(matching);
		execution.setVariable("matching", matching);
	}

}
