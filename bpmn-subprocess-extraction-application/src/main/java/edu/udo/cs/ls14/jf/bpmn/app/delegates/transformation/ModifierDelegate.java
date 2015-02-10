package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.ModifierImpl;

public class ModifierDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessTransformation transformation = (ProcessTransformation) execution
				.getVariable("transformation");
		transformation = new ModifierImpl().modify(transformation);
		execution.setVariable("transformation", transformation);
	}

}
