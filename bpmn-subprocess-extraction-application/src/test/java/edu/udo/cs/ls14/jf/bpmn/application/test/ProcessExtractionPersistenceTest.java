package edu.udo.cs.ls14.jf.bpmn.application.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.registry.Registries;

public class ProcessExtractionPersistenceTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testProcessExtractionPersistence() throws Exception {

		Bpmn2ResourceSet resSetIn = Bpmn2ResourceSet.getInstance();
		String pathname = "/bpmn/parallelGateway/";
		String basename1 = "parallelism1.bpmn";
		Definitions definitions = resSetIn.loadDefinitions(getClass().getResource(pathname + basename1).getPath());
		definitions.setTargetNamespace("http://" + UUID.randomUUID());
		System.out.println(definitions);
		
		ProcessExtraction extraction = BpmnTransformationFactory.eINSTANCE
				.createProcessExtraction();
		extraction.getResults().put("first", EcoreUtil.copy(definitions));
		ProcessExtractionUtil.writeToFile("/tmp/test.xml", extraction);

		// Forget everything, try to reproduce from disk
		String xml = readFile("/tmp/test.xml");
		ProcessExtraction next = (ProcessExtraction) EObjectXmlConverter
				.xml2EObject("bpmntransformation", xml);
		// write results
		ProcessExtractionUtil.writeToFile("/tmp/test2.xml",
				EcoreUtil.copy(next));

	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
	}

}
