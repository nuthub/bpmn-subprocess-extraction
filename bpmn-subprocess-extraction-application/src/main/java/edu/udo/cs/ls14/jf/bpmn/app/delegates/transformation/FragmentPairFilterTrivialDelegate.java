package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentPairFilterTrivialImpl;

/**
 * Delegates work to FragmentPairFilterTrivial service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentPairFilterTrivialSEI
 * 
 * @author Julian Flake
 *
 */
public class FragmentPairFilterTrivialDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentPairFilterTrivialImpl().filter(matching);
		execution.setVariable("matching", matching);
	}
}
