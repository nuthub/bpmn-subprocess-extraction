package edu.udo.cs.ls14.jf.bpmn.metrics.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.metrics.BpmnMetrics;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.registry.Registries;

public class BpmnMetricsTest {

	@Test
	public void testCFC() throws Exception {
		Registries.registerAll();
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource("/bpmn/completeLabelled/complete1labelled.bpmn")
						.getPath());
		Process process = DefinitionsUtil.getProcess(def1);
		BpmnMetrics metrics = new BpmnMetrics();
		System.out.println(metrics.evaluate(process));
	}
}
