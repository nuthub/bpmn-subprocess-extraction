package edu.udo.cs.ls14.jf.bpmn.application.test.thesis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.CPDebugUtil;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformer;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.Fragment;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationUtil;

/**
 * Have to be run manually (not included in mvn test) and unignored
 * 
 * Problems with mvn test along other tests
 * 
 * @author flake
 *
 */
@Ignore
public class ThesisExport {
	private static final String TARGET_DIR = "/home/flake/Arbeitsfläche/Diplomarbeit/bilder/example/";

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void createAnalysisArtifacts1() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename = "complete1";
		String targetDir = (TARGET_DIR + "analysis/").replaceAll("//", "/");
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");
		Definitions def = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis = analyzer.analyzeAndDebug(def, pathname,
				basename, targetDir, nodes);
		assertNotNull(analysis);
	}

	@Test
	public void createAnalysisArtifacts2() throws Exception {
		String pathname = "/bpmn/conditionalFlow/";
		String basename = "conditionSequence1";
		String targetDir = (TARGET_DIR + "analysis/").replaceAll("//", "/");
		List<String> nodes = Arrays.asList("n_start", "Task 1", "Task 2",
				"Task 3", "Task 4", "n_end");
		Definitions def = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());

		Fragment fragment = BpmnAnalysisFactory.eINSTANCE.createFragment();
		fragment.setEntry(DefinitionsUtil.getSequenceFlow(
				def, "entry"));
		fragment.setExit(DefinitionsUtil.getSequenceFlow(
				def, "exit"));
		// analyze and debug
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis = analyzer.analyzeAndDebug(def, pathname,
				basename, targetDir, nodes);

		// create conditional profile of fragment
		ConditionalProfile fragmentProfile = ConditionalProfiler
				.getFragmentProfile(
						ProcessAnalysisUtil.getConditionalProfile(analysis),
						fragment);

		List<String> fragmentNodes = Arrays
				.asList("Task 1", "Task 2", "Task 3");
		CPDebugUtil.writeDebugFiles(targetDir, basename,
				fragmentNodes, fragmentProfile, "conditionalprofile-fragment");
		assertNotNull(analysis);
	}

	@Test
	public void createEvaluationArtifacts2() throws Exception {
		String pathname = "/bpmn/completeLabelled/";
		String basename1 = "complete1labelled";
		String basename2 = "complete2labelled";
		String targetDir = (TARGET_DIR + "evaluation/bsp2/").replaceAll("//", "/");
		List<String> nodes1 = Arrays.asList("n_start", "Anspruch pruefen",
				"Lieferschein erstellen", "Rechnung erstellen",
				"Ware verpacken", "Betrag erstatten", "Gutschein ausstellen",
				"E1", "E2", "E3", "E4", "n_end");
		List<String> nodes2 = Arrays.asList("n_start", "Anspruch pruefen",
				"Lieferschein anfertigen", "Rechnung anfertigen",
				"Artikel verpacken", "Betrag erstatten",
				"Gutschein ausstellen", "E1", "E2", "E3", "E4", "n_end");
		new File(targetDir).mkdirs();
		// 1a. analyze model1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename1 + ".bpmn")
						.getPath());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyzeAndDebug(def1, pathname,
				basename1, targetDir, nodes1);
		ProcessAnalysisUtil.writeToFile(targetDir + basename1 + "."
				+ BpmnAnalysisPackage.eNAME, analysis1);

		// 1b. analyze model2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename2 + ".bpmn")
						.getPath());
		analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis2 = analyzer.analyzeAndDebug(def2, pathname,
				basename2, targetDir, nodes2);
		ProcessAnalysisUtil.writeToFile(targetDir + basename2 + "."
				+ BpmnAnalysisPackage.eNAME, analysis2);

		// 2. match models
		ProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + basename1 + "_" + basename2
				+ "_matching." + BpmnMatchingPackage.eNAME, matching);

		// 3. transform models
		ProcessTransformer transformer = new ProcessTransformerImpl();
		ProcessTransformation transformation = transformer.transform(matching);
		ProcessTransformationUtil.writeToFile(targetDir + basename1 + "_"
				+ basename2 + "." + BpmnTransformationPackage.eNAME,
				transformation);
		ProcessTransformationUtil.writeResults(targetDir, transformation);

		assertEquals(4, transformation.getResults().size());
	}

	@Test
	public void createEvaluationArtifacts1() throws Exception {
		String pathname = "/bpmn/sequences/";
		String basename = "sequence1";
		String targetDir = (TARGET_DIR + "evaluation/bsp1/").replaceAll("//", "/");
		List<String> nodes = Arrays.asList("n_start", "A", "B", "C", "D", "F",
				"n_end");
		Definitions def = Bpmn2ResourceSet.getInstance()
				.loadDefinitions(
						getClass().getResource(pathname + basename + ".bpmn")
								.getPath());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis = analyzer.analyzeAndDebug(def, pathname,
				basename, targetDir, nodes);
		assertNotNull(analysis);
	}
}
