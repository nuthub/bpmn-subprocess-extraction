package edu.udo.cs.ls14.jf.bpmn.app.delegates.analysis;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl.ProcessAnalysisFactoryImpl;

/**
 * Delegates work to ProcessAnalysisFactory service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ws.ProcessAnalysisFactorySEI
 * 
 * @author Julian Flake
 *
 */
public class ProcessAnalysisFactoryDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Definitions definitions = (Definitions) execution
				.getVariable("definitions");
		ProcessAnalysis analysis = new ProcessAnalysisFactoryImpl()
				.createProcessAnalysis(definitions);
		execution.setVariable("analysis", analysis);
	}

}
