package edu.udo.cs.ls14.jf.analysis.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;

public class ProcessAnalyzerTest {

	private int getPstSize(ProcessAnalysis analysis) {
		return ((ProcessStructureTree) analysis.getResults().get("pst"))
				.getFragments().size();
	}

	private int getCpSize(ProcessAnalysis analysis) {
		return ((ConditionalProfile) analysis.getResults().get(
				"conditionalProfile")).getRelations().size();
	}

	@Test
	public void testAnalysisPM1() throws Exception {
		Resource res = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadResource("PM1-mit-Fragment1.bpmn");
		Definitions def = ((DocumentRoot) res.getContents().get(0))
				.getDefinitions();
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(def);
		assertEquals(6, getPstSize(analysis));
		assertEquals(7, getCpSize(analysis));
	}

	@Test
	public void testAnalysisConditionSequence() throws Exception {
		Resource res = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadResource("conditionalFlow/conditionSequence.bpmn");
		Definitions def = ((DocumentRoot) res.getContents().get(0))
				.getDefinitions();
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(def);
		assertEquals(5, getPstSize(analysis));
		assertEquals(5, getCpSize(analysis));
	}

	@Test
	public void testAnalysisConditionSequence2() throws Exception {
		Resource res = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadResource("conditionalFlow/conditionSequence2.bpmn");
		Definitions def = ((DocumentRoot) res.getContents().get(0))
				.getDefinitions();
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(def);
		assertEquals(5, getPstSize(analysis));
		assertEquals(5, getCpSize(analysis));
	}
}
