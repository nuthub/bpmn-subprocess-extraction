package edu.udo.cs.ls14.jf.bpmn.app.delegates.matching;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;

public class ProcessMatchingFactoryDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis1 = (ProcessAnalysis) execution
				.getVariable("analysis1");
		ProcessAnalysis analysis2 = (ProcessAnalysis) execution
				.getVariable("analysis2");

		ProcessMatching matching = ProcessMatchingFactory.createEmptyMatching(
				analysis1, analysis2);
		execution.setVariable("matching", matching);
	}

}
