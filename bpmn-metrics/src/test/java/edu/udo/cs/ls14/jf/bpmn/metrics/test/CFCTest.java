package edu.udo.cs.ls14.jf.bpmn.metrics.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.metrics.CFC;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.registry.Registries;

public class CFCTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testSequences() throws Exception {
		assertEquals(0, getCFC("/bpmn/sequences/sequence1.bpmn"));
		assertEquals(0, getCFC("/bpmn/sequences/sequence2.bpmn"));
	}

	@Test
	public void testExclusiveGateway() throws Exception {
		assertEquals(2, getCFC("/bpmn/exclusiveGateway/xor-example.bpmn"));
		assertEquals(4, getCFC("/bpmn/exclusiveGateway/looping-xor.bpmn"));
	}

	@Test
	public void testParallelGateway() throws Exception {
		assertEquals(1, getCFC("/bpmn/parallelGateway/parallelism1.bpmn"));
		assertEquals(2, getCFC("/bpmn/parallelGateway/parallelism2.bpmn"));
	}

	@Test
	public void testCompleteLabelled() throws Exception {
		assertEquals(7, getCFC("/bpmn/completeLabelled/complete1labelled.bpmn"));
		assertEquals(8, getCFC("/bpmn/completeLabelled/complete2labelled.bpmn"));
	}

	private int getCFC(String resourcename) throws Exception {
		Process process = DefinitionsUtil.getProcess(Bpmn2ResourceSet
				.getInstance().loadDefinitions(
						getClass().getResource(resourcename).getPath()));
		return CFC.getComplexity(process.getFlowElements());
	}
}
