package edu.udo.cs.ls14.jf.analysis.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzerOld;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;

public class ProcessAnalyzerTest {

	@Test
	public void testAnalysisPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		URL url = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/" + basename + ".bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		ProcessAnalysis analysis = ProcessAnalyzerOld.analyze(res);
		assertEquals(6, analysis.getPst().getFragments().size());
		assertEquals(7, analysis.getConditionalProfile().getRelations().size());
	}

	@Test
	public void testAnalysisConditionSequence() throws Exception {
		String basename = "conditionSequence";
		URL url = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/conditionalFlow/" + basename
						+ ".bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		ProcessAnalysis analysis = ProcessAnalyzerOld.analyze(res);
		assertEquals(5, analysis.getPst().getFragments().size());
		assertEquals(5, analysis.getConditionalProfile().getRelations().size());
	}

	@Test
	public void testAnalysisConditionSequence2() throws Exception {
		String basename = "conditionSequence2";
		URL url = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/conditionalFlow/" + basename
						+ ".bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		ProcessAnalysis analysis = ProcessAnalyzerOld.analyze(res);
		assertEquals(5, analysis.getPst().getFragments().size());
		assertEquals(5, analysis.getConditionalProfile().getRelations().size());
	}
}
