package edu.udo.cs.ls14.jf.analysis.test;

import java.net.URL;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessAnalyzerTest {

	@Test
	public void testAnalysis() throws Exception {
		URL url = getClass().getResource("../../bpmn/PM1-mit-Fragment1.bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(res);
		System.out.println(analysis);
	}
}
