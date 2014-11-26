package edu.udo.cs.ls14.jf.bpmn.analysis.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

public class ConditionalProfileDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis = (ProcessAnalysis) execution
				.getVariable("processanalysis");
		Process process = ProcessUtil.getProcessFromDefinitions(analysis
				.getDefinitions());
		ConditionalProfile profile = ConditionalProfiler
				.generateProfile(process);
		analysis.getResults().put(ProcessAnalysis.CONDITIONALPROFILE, profile);
		execution.setVariable("processanalysis", analysis);
	}
}