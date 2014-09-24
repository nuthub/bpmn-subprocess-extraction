package edu.udo.cs.ls14.jf.matching.test;

import java.net.URL;
import java.util.Set;

import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.matching.Matcher;
import edu.udo.cs.ls14.jf.matching.Matching;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class MatcherTest {

	@Test
	public void testMatcherPM1withPM2() throws Exception {
		runTest("PM1-mit-Fragment1", "PM2-mit-Fragment1");
	}

	@Test
	public void testMatcherPM1withPM3() throws Exception {
		runTest("PM1-mit-Fragment1", "PM3-mit-Fragment2");
	}

	@Test
	public void testMatcherEventBasedGwExamples() throws Exception {
		runTest("event-based-gateway-parallel", "event-based-gateway-exclusive");
	}

	private void runTest(String basename1, String basename2) throws Exception {
		URL url1 = MatcherTest.class.getResource("../../bpmn/" + basename1
				+ ".bpmn");
		URL url2 = MatcherTest.class.getResource("../../bpmn/" + basename2
				+ ".bpmn");
		Process p = ProcessLoader.loadFirstProcessFromResource(url1);
		Process q = ProcessLoader.loadFirstProcessFromResource(url2);
		System.out.println("Comparing " + p.getName() + " with " + q.getName());
		Matcher matcher = new Matcher();
		Set<Matching> matches = matcher.getMatches(p, q);
		matches.forEach(m -> System.out.println(m));
		System.out.println();

	}
}
