package edu.udo.cs.ls14.jf.analysis.conditionalprofile.test;

import static org.junit.Assert.*;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ConditionalProfileTest {

	@Before
	public void setUp() {
		Registries.registerAll();

	}

	@Test
	public void testConditionSequence() throws Exception {
		String pathname = "/bpmn/conditionalFlow/";
		Process p1 = getResource(pathname, "conditionSequence1");
		ConditionalProfile cp1 = ConditionalProfiler.generateProfile(p1);
		// TODO: assertions
		assertNotNull(cp1);

		Process p2 = getResource(pathname, "conditionSequence2");
		ConditionalProfile cp2 = ConditionalProfiler.generateProfile(p2);
		// TODO: assertions
		assertNotNull(cp2);
	}

	private Process getResource(String pathname, String basename)
			throws Exception {
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		return DefinitionsUtil.getProcess(definitions);
	}
}
