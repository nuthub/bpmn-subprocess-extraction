package edu.udo.cs.ls14.jf.analysis.conditionalprofile.test;

import static org.junit.Assert.*;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ConditionalProfileTest {

	@Before
	public void setUp() {
		Registries.registerAll();

	}

	@Test
	public void testConditionSequence() throws Exception {
		String pathname = "/bpmn/conditionalFlow/";
		Definitions definitions = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + "conditionSequence1" + ".bpmn")
								.getPath());
		Process p1 = DefinitionsUtil.getProcess(definitions);
		ConditionalProfile cp1 = ConditionalProfiler.generateProfile(p1);
		assertNotNull(cp1);
		
		Fragment f = BpmnAnalysisFactory.eINSTANCE.createFragment();
		f.setEntry((SequenceFlow) DefinitionsUtil.getSequenceFlow(definitions, "entry"));
		f.setExit((SequenceFlow) DefinitionsUtil.getSequenceFlow(definitions, "exit"));
		ConditionalProfile pf = ConditionalProfiler.getFragmentProfile(cp1, f);
		assertNotNull(pf);
		
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
