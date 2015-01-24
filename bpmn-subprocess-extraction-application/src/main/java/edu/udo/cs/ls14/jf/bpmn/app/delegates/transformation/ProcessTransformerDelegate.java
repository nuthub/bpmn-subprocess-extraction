package edu.udo.cs.ls14.jf.bpmn.app.delegates.transformation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformer;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

@Deprecated
public class ProcessTransformerDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution
				.getVariable("matching");
		ProcessTransformer transformer = new ProcessTransformerImpl();
		ProcessTransformation extraction = transformer.transform(matching);
		execution.setVariable("extraction", extraction);
	}

}
