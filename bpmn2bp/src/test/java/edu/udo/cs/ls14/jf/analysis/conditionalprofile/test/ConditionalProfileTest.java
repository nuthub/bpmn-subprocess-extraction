package edu.udo.cs.ls14.jf.analysis.conditionalprofile.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.utils.bpmn.Bpmn2ResourceSet;

public class ConditionalProfileTest {

	@Test
	public void testConditionSequence() throws Exception {
		Process p1 = getResource("conditionSequence");
		ConditionalProfile cp1 = ConditionalProfiler.generateProfile(p1);
		System.out.println(cp1);
		// TODO: assertions

		Process p2 = getResource("conditionSequence2");
		ConditionalProfile cp2 = ConditionalProfiler.generateProfile(p2);
		System.out.println(cp2);
		// TODO: assertions
	}

	private Process getResource(String basename) throws Exception {
		Definitions definitions = (Definitions) new Bpmn2ResourceSet(
				"src/test/resources/edu/udo/cs/ls14/jf/bpmn/conditionalFlow")
				.loadResource(basename + ".bpmn").getContents().get(0);
		return ProcessLoader.getProcessFromDefinitions(definitions);
	}

}
