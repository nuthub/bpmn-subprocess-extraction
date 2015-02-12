package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentLabellerImpl;

/**
 * Delegates work to FragmentLabeller service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmntransformation.ws.FragmentLabellerSEI
 * 
 * @author Julian Flake
 *
 */
public class LabelGeneratorDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		matching = new FragmentLabellerImpl().generateLabels(matching);
		execution.setVariable("matching", matching);
	}

}
