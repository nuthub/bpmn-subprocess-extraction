package edu.udo.cs.ls14.jf.bpmn.app.delegates.analysis;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl.BehavioralProfilerImpl;

/**
 * Delegates work to BehavioralProfiler service.
 * 
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.ws.BehavioralProfilerSEI
 * 
 * @author Julian Flake
 *
 */
public class BehavioralProfileDelegate implements JavaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis = (ProcessAnalysis) execution
				.getVariable("analysis");
		analysis = new BehavioralProfilerImpl().profile(analysis);
		execution.setVariable("analysis", analysis);
	}
}
