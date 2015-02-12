package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.ProcessTransformationFactoryImpl;

/**
 * Delegates work to ProcessTransformationFactory service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmntransformation.ws.ProcessTransformationFactorySEI
 * 
 * @author Julian Flake
 *
 */
public class ProcessTransformationFactoryDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		ProcessTransformation transformation = new ProcessTransformationFactoryImpl()
				.create(matching);
		execution.setVariable("transformation", transformation);
	}

}
