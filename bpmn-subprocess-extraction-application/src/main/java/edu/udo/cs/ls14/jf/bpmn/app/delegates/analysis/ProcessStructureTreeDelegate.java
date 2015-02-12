package edu.udo.cs.ls14.jf.bpmn.app.delegates.analysis;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl.ProcessStructureTreeImpl;

/**
 * Delegates work to ProcessStructureTree service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ws.ProcessAnalysisFactorySEI
 * 
 * @author Julian Flake
 *
 */
public class ProcessStructureTreeDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis = (ProcessAnalysis) execution
				.getVariable("analysis");
		analysis = new ProcessStructureTreeImpl().getPst(analysis);
		execution.setVariable("analysis", analysis);
	}
}
