package edu.udo.cs.ls14.jf.bpmnapplication.test;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessExtractor;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class ProcessExtractionTest {

	@Before
	public void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn",
						new BpmnTransformationResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnextraction",
						new BpmnTransformationResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(BpmnTransformationPackage.eNS_URI,
				BpmnTransformationPackage.eINSTANCE);

	}

	@Test
	public void testSequenceSequence2() throws Exception {
		String basename1 = "sequence";
		String basename2 = "sequence2";
		runTest(basename1, basename2, "");
	}

	@Test
	public void testPM1PM2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2, "");
	}

	@Test
	public void testXorExampleLoopingXor() throws Exception {
		String basename1 = "xor-example";
		String basename2 = "looping-xor";
		runTest(basename1, basename2, "");
	}

	@Test
	public void testConditionSequenceConditionSequence2() throws Exception {
		String basename1 = "conditionSequence";
		String basename2 = "conditionSequence2";
		runTest(basename1, basename2, "conditionalFlow");
	}

	private void runTest(String basename1, String basename2, String path)
			throws Exception {
		String targetDir = "/tmp/applicationtest/";
		// hit the process
		Bpmn2ResourceSet resSetIn = new Bpmn2ResourceSet(getClass()
				.getResource("/edu/udo/cs/ls14/jf/bpmn/test/" + path + "/")
				.getPath());
		Definitions definitions1 = EcoreUtil.copy(resSetIn
				.loadDefinitions(basename1 + ".bpmn"));
		Definitions definitions2 = EcoreUtil.copy(resSetIn
				.loadDefinitions(basename2 + ".bpmn"));

		// Create resources for source definitions
		Bpmn2ResourceSet resSetOut = new Bpmn2ResourceSet(targetDir);
		resSetOut.createResource(basename1 + "_transformed.bpmn", definitions1);
		resSetOut.createResource(basename2 + "_transformed.bpmn", definitions2);

		// START
		// analyze process1
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(definitions1);
		// analyze process2
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(definitions2);
		// match process1 and process2
		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		// Do the extraction
		ProcessExtraction extraction = ProcessExtractor.extract(matching);
		// END

		// write out
		ProcessExtractionUtil.writeResults(targetDir, extraction);
		// TODO: assertions
	}
}
