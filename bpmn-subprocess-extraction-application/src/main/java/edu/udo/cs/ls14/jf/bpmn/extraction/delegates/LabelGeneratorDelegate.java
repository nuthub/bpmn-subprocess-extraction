package edu.udo.cs.ls14.jf.bpmn.extraction.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;

public class LabelGeneratorDelegate implements JavaDelegate {

	// TODO: let this happen outside the dumb delegate
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessMatching matching = (ProcessMatching) execution.getVariable("matching");
		for(FragmentPair pair : matching.getFragmentMatching().getPairs()) {
			pair.getA().setLabel(LabelGenerator.getLabel(pair.getA()));
			pair.getB().setLabel(LabelGenerator.getLabel(pair.getB()));
		}
		execution.setVariable("matching", matching);
	}

}
