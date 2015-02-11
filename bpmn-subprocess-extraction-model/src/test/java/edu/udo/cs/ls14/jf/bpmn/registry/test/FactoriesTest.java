package edu.udo.cs.ls14.jf.bpmn.registry.test;

import static org.junit.Assert.assertNotNull;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.ProcessMatchingFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationFactory;

public class FactoriesTest {

	@Test
	public void testConstructors() {
		assertNotNull(new ProcessAnalysisFactory());
		assertNotNull(new ProcessMatchingFactory());
		assertNotNull(new ProcessTransformationFactory());
	}

	@Test
	public void testFactories() {
		Definitions definitions1 = Bpmn2Factory.eINSTANCE.createDefinitions();
		Definitions definitions2 = Bpmn2Factory.eINSTANCE.createDefinitions();
		ProcessAnalysis analysis1 = ProcessAnalysisFactory
				.createAnalysis(Bpmn2Factory.eINSTANCE.createDefinitions());
		assertNotNull(analysis1);
		analysis1.setDefinitions(definitions1);
		ProcessAnalysis analysis2 = ProcessAnalysisFactory
				.createAnalysis(Bpmn2Factory.eINSTANCE.createDefinitions());
		assertNotNull(analysis2);
		analysis2.setDefinitions(definitions2);
		ProcessMatching matching = ProcessMatchingFactory.createEmptyMatching(
				analysis1, analysis2);
		matching.setNodeMatching(ProcessMatchingFactory.getFullNodeMatching(
				definitions1, definitions2));
//		matching.setFragmentMatching(ProcessMatchingFactory
//				.getFullFragmentMatching(analysis1, analysis2));
		assertNotNull(matching);
		ProcessTransformation transformation = ProcessTransformationFactory
				.createProcessTransformation(matching);
		assertNotNull(transformation);

	}
}
