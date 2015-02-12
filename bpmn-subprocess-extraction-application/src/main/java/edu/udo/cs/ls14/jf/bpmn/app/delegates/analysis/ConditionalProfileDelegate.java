package edu.udo.cs.ls14.jf.bpmn.app.delegates.analysis;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl.ConditionalProfilerImpl;

/**
 * Delegates work to ConditionalProfilerService.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ws.ConditionalProfilerSEI
 * @author flake
 *
 */
public class ConditionalProfileDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis = (ProcessAnalysis) execution
				.getVariable("analysis");
		analysis = new ConditionalProfilerImpl().profile(analysis);
		execution.setVariable("analysis", analysis);
	}
}
