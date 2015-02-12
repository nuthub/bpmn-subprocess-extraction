package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmntransformation.Extractor;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

/**
 * Delegates work to Extractor service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmntransformation.ws.ExtractorSEI
 * 
 * @author Julian Flake
 *
 */
public class ExtractorDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessTransformation transformation = (ProcessTransformation) execution
				.getVariable("transformation");
		transformation = Extractor.extract(transformation);
		execution.setVariable("transformation", transformation);
	}

}
