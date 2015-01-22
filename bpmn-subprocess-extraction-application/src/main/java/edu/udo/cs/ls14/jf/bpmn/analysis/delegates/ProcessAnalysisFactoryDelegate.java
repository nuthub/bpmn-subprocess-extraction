package edu.udo.cs.ls14.jf.bpmn.analysis.delegates;

import java.util.UUID;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public class ProcessAnalysisFactoryDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Definitions definitions = (Definitions) execution
				.getVariable("definitions");
//		definitions.setTargetNamespace(definitions.getTargetNamespace() + "/"
//				+ UUID.randomUUID());
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);
		execution.setVariable("analysis", analysis);
	}

}
