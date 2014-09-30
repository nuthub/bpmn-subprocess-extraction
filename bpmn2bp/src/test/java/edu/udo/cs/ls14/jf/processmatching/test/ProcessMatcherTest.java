package edu.udo.cs.ls14.jf.processmatching.test;

import java.net.URL;
import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.processmatching.ProcessMatcher;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ProcessMatcherTest {

	@Test
	public void testPm1WithPm2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2, 3, 4);
	}

	@Test
	public void testPm1WithPm3() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM3-mit-Fragment2";
		runTest(basename1, basename2, 5, 5);
	}

	private void runTest(String basename1, String basename2,
			int nodeCorrespondences, int fragmentCorrespondences)
			throws Exception {
		System.out.println("Testing " + basename1 + " with " + basename2);
		URL url1 = getClass().getResource("../../bpmn/" + basename1 + ".bpmn");
		URL url2 = getClass().getResource("../../bpmn/" + basename2 + ".bpmn");
		Process process1 = ProcessLoader.loadFirstProcessFromResource(url1);
		Process process2 = ProcessLoader.loadFirstProcessFromResource(url2);
		ProcessMatching matching = ProcessMatcher.match(process1, process2);
		assertEquals(nodeCorrespondences, matching.getNodeCorrespondences()
				.size());
		assertEquals(fragmentCorrespondences, matching
				.getFragmentCorrespondences().size());
	}
}
