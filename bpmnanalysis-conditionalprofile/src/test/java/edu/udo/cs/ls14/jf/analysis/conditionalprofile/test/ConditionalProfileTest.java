package edu.udo.cs.ls14.jf.analysis.conditionalprofile.test;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;

public class ConditionalProfileTest {

	@Test
	public void testConditionSequence() throws Exception {
		ConditionalProfiler profiler = new ConditionalProfiler();

		Process p1 = getResource("conditionSequence");
		ConditionalProfile cp1 = profiler.generateProfile(p1);
		System.out.println(cp1);
		// TODO: assertions

		Process p2 = getResource("conditionSequence2");
		ConditionalProfile cp2 = profiler.generateProfile(p2);
		System.out.println(cp2);
		// TODO: assertions
	}

	private Process getResource(String basename) throws Exception {
		Definitions definitions = ((DocumentRoot) new Bpmn2ResourceSet(
				getClass().getResource(
						"/edu/udo/cs/ls14/jf/bpmn/test/conditionalFlow")
						.getPath()).loadResource(basename + ".bpmn")
				.getContents().get(0)).getDefinitions();
		return ProcessUtil.getProcessFromDefinitions(definitions);
	}
}
