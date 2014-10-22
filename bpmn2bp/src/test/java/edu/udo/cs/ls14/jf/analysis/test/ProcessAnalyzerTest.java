package edu.udo.cs.ls14.jf.analysis.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessAnalyzerTest {

	@Test
	public void testAnalysisPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(res);
		assertEquals(6, analysis.getPst().getFragments().size());
		assertEquals(7, analysis.getConditionalProfile()
				.getFlowNodeConditions().size());
	}

	@Test
	public void testAnalysisConditionSequence() throws Exception {
		String basename = "conditionSequence";
		URL url = getClass().getResource(
				"../../bpmn/conditionalFlow/" + basename + ".bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(res);
		assertEquals(5, analysis.getPst().getFragments().size());
		assertEquals(5, analysis.getConditionalProfile()
				.getFlowNodeConditions().size());
	}

	@Test
	public void testAnalysisConditionSequence2() throws Exception {
		String basename = "conditionSequence2";
		URL url = getClass().getResource(
				"../../bpmn/conditionalFlow/" + basename + ".bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(res);
		assertEquals(5, analysis.getPst().getFragments().size());
		assertEquals(5, analysis.getConditionalProfile()
				.getFlowNodeConditions().size());
	}

}
