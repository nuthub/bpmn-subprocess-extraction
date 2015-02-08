package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.transformation.Modifier;

public class ModifierDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessTransformation transformation = (ProcessTransformation) execution
				.getVariable("transformation");
		transformation = Modifier.modify(transformation);
		execution.setVariable("transformation", transformation);
	}

}
