package edu.udo.cs.ls14.jf.bpmn.application.test;

import java.util.List;
import java.util.UUID;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SerializationTest {

	@Test
	public void testCopier() throws Exception {
		Registries.registerAll();

		String pathname = "/bpmn/parallelGateway2/";
		String basename = "parallelism1";
		Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();

		Definitions defs = resSet.loadDefinitions(getClass().getResource(
				pathname + basename + ".bpmn").getFile());
		Definitions copy = DefinitionsUtil.copy(defs);
		Resource res = resSet.createResource("/tmp/test.bpmn", copy);
//		copy.setTargetNamespace(UUID.randomUUID().toString());
		res.save(null);
		res.save(System.out, null);

	}


}
