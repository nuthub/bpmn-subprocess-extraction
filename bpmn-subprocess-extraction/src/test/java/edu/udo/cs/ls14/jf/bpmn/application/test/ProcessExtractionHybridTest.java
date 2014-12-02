package edu.udo.cs.ls14.jf.bpmn.application.test;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.application.SubProcessExtraction;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;
import edu.udo.cs.ls14.jf.transformation.ProcessExtractor;

public class ProcessExtractionHybridTest {

	@Before
	public void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnanalysis",
						new BpmnAnalysisResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnmatching",
						new BpmnMatchingResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmntransformation",
						new BpmnTransformationResourceFactoryImpl());

		EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		EPackage.Registry.INSTANCE.put(BpmnAnalysisPackage.eNS_URI,
				BpmnAnalysisPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(BpmnMatchingPackage.eNS_URI,
				BpmnMatchingPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(BpmnTransformationPackage.eNS_URI,
				BpmnTransformationPackage.eINSTANCE);
	}

	@Test
	@Ignore
	public void testSequenceSequence2() throws Exception {
		String basename1 = "sequence";
		String basename2 = "sequence2";
		runTest(basename1, basename2, "");
	}

	@Test
	public void testPM1PM2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2, "", "PM1PM2");
	}

	@Test
	@Ignore
	public void testXorExampleLoopingXor() throws Exception {
		String basename1 = "xor-example";
		String basename2 = "looping-xor";
		runTest(basename1, basename2, "");
	}

	@Test
	@Ignore
	public void testConditionSequenceConditionSequence2() throws Exception {
		String basename1 = "conditionSequence";
		String basename2 = "conditionSequence2";
		runTest(basename1, basename2, "conditionalFlow");
	}

	private void runTest(String name1, String name2, String path)
			throws Exception {
		runTest(name1, name2, path, path);
	}

	private void runTest(String name1, String name2, String path, String key)
			throws Exception {

		String targetDir = "/tmp/hybridtest/" + key + "/";

		// Pre
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/" + path + "/").getPath());
		Definitions def1 = EcoreUtil.copy(resSet.loadDefinitions(name1
				+ ".bpmn"));
		Definitions def2 = EcoreUtil.copy(resSet.loadDefinitions(name2
				+ ".bpmn"));

		SubProcessExtraction processEngine = new SubProcessExtraction();
		processEngine.init();

		// START
		// 1a. analyze model1
		ProcessAnalysis analysis1 = processEngine.runProcessAnalysis(def1);
		ProcessAnalysisUtil.writeToFile(targetDir + "analysis1.xml", analysis1);

		// 1b. analyze model2
		ProcessAnalysis analysis2 = processEngine.runProcessAnalysis(def2);
		ProcessAnalysisUtil.writeToFile(targetDir + "analysis2.xml", analysis2);

		// 2. match models
		ProcessMatching matching = processEngine.runProcessMatching(analysis1,
				analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + "matching.xml", matching);

		// bis hier unproblematisch
		// ab hier problematisch

		// 3. extract models
		// BPMN variant (funktioniert nicht)
		ProcessExtraction extraction1 = processEngine
				.runProcessExtraction(matching);
		ProcessExtractionUtil.writeResults(targetDir + "bpmn/", extraction1);
		ProcessExtractionUtil.writeToFile(targetDir + "bpmn/"
				+ "extraction.xml", extraction1);

		// pure Java variant (funktioniert)
		ProcessExtraction extraction2 = ProcessExtractor.extract(matching);
		ProcessExtractionUtil.writeResults(targetDir + "java/", extraction2);
		ProcessExtractionUtil.writeToFile(targetDir + "java/"
				+ "extraction.xml", extraction2);

		// END

		// Post

		// TODO: assertions
	}
}
