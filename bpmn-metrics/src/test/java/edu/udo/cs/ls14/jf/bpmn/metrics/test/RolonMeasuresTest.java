package edu.udo.cs.ls14.jf.bpmn.metrics.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.metrics.RolonMeasures;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.registry.Registries;

public class RolonMeasuresTest {

	@Test
	public void testRolonMeasures1() throws Exception {
		Registries.registerAll();
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(
						"/bpmn/completeLabelled/complete1labelled.bpmn")
						.getPath());
		Process process = DefinitionsUtil.getProcess(def1);
		RolonMeasures metrics = new RolonMeasures();
		System.out.println(metrics.evaluate(process));
	}

	@Test
	public void testRolonMeasures2() throws Exception {
		Registries.registerAll();
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource("/process_1.bpmn").getPath());
		Process process = DefinitionsUtil.getProcess(def1);
		RolonMeasures metrics = new RolonMeasures();
		System.out.println(metrics.evaluate(process));
	}
}
