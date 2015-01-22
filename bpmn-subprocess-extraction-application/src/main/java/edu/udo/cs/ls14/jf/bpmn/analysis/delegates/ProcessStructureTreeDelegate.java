package edu.udo.cs.ls14.jf.bpmn.analysis.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;

public class ProcessStructureTreeDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessAnalysis analysis = (ProcessAnalysis) execution
				.getVariable("analysis");
		ProcessStructureTree pst = new PSTBuilder().getTree(analysis
				.getDefinitions());
		analysis.getResults().put(ProcessAnalysis.PROCESSTRUCTURETREE, pst);
		execution.setVariable("analysis", analysis);
	}
}
