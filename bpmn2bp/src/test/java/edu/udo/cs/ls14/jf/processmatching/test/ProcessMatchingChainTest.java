package edu.udo.cs.ls14.jf.processmatching.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.function.Predicate;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;

public class ProcessMatchingChainTest {

	@Test
	public void testConditionSequence1ConditionSequence2() throws Exception {
		String basename1 = "conditionSequence";
		String basename2 = "conditionSequence2";
		System.out.println("Testing " + basename1 + " with " + basename2);
		URL url1 = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/conditionalFlow/" + basename1 + ".bpmn");
		URL url2 = getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/conditionalFlow/" + basename2 + ".bpmn");
		Resource res1 = ProcessLoader.getBpmnResource(url1);
		Resource res2 = ProcessLoader.getBpmnResource(url2);
		runTest(res1, res2, 1);
	}

	@Test
	public void testPm1WithPm2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		System.out.println("Testing " + basename1 + " with " + basename2);
		URL url1 = getClass().getResource(
				"../../bpmn/" + basename1 + ".bpmn");
		URL url2 = getClass().getResource(
				"../../bpmn/" + basename2 + ".bpmn");
		Resource res1 = ProcessLoader.getBpmnResource(url1);
		Resource res2 = ProcessLoader.getBpmnResource(url2);
		runTest(res1, res2, 1);
	}

	private ProcessMatching runTest(Resource res1, Resource res2,
			int expectedFCs) throws Exception {
		ProcessMatching matching = ProcessMatchingChain.getCandidatesOld(res1,
				res2);
		printMatching(matching);
		assertEquals(expectedFCs, matching.getFragmentCorrespondencesOld().size());
		return matching;
	}

	private void printMatching(ProcessMatching matching) {
		Predicate<FlowNode> filter = n -> n instanceof Event
				|| n instanceof Activity;
		matching.getFragmentCorrespondencesOld()
				.stream()
				.forEach(
						p -> System.out.println(p.getValue0()
								+ " with "
								+ p.getValue0().getContainedFlowNodes(filter)
										.size()
								+ " As/Es corresponds to "
								+ p.getValue1()
								+ " with "
								+ p.getValue1().getContainedFlowNodes(filter)
										.size() + " As/Es."));

	}
}
