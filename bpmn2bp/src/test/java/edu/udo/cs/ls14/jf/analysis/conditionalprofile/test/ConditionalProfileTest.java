package edu.udo.cs.ls14.jf.analysis.conditionalprofile.test;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfile;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class ConditionalProfileTest {

	@Test
	public void testConditionSequence() throws Exception {
		Resource res1 = getResource("conditionSequence");
		ConditionalProfile cp1 = ConditionalProfiler.generateProfile(res1);
		System.out.println(cp1);

		Resource res2 = getResource("conditionSequence2");
		ConditionalProfile cp2 = ConditionalProfiler.generateProfile(res2);
		System.out.println(cp2);
	}

	private Resource getResource(String basename) throws Exception {
		return ProcessLoader.getBpmnResource(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/conditionalFlow/" + basename + ".bpmn"));
	}

}
