package edu.udo.cs.ls14.jf.bpmn.transformation.test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmn.analysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmn.matching.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmn.matching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformer;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessMatchingUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

/**
 * @deprecated See ProcessExtractionHybridTest
 * 
 * @author flake
 *
 */
@Deprecated
public class ProcessTransformerTest {

	private static final String TARGET_DIR = "/tmp/";

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testSequence1Sequence2() throws Exception {
		String pathname = "/bpmn/sequences/";
		String basename1 = "sequence1";
		String basename2 = "sequence2";
		List<String> nodes = Arrays.asList();
		runTest(pathname, basename1, basename2, nodes, nodes);
	}

	@Test
	public void testParallelism1Parallelism2() throws Exception {
		String pathname = "/bpmn/parallelGateway/";
		String basename1 = "parallelism1";
		String basename2 = "parallelism2";
		List<String> nodes = Arrays.asList();
		runTest(pathname, basename1, basename2, nodes, nodes);
	}

	@Test
	public void testComplete1Complete2() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename1 = "complete1";
		String basename2 = "complete2";
		List<String> nodes = Arrays.asList("n_start", "T1", "T2", "T3", "T4",
				"T5", "T6", "E1", "E2", "E3", "E4", "n_end");
		runTest(pathname, basename1, basename2, nodes, nodes);
	}

	private void runTest(String pathname, String basename1, String basename2,
			List<String> nodes1, List<String> nodes2) throws Exception {

		// Pre
		String targetDir = (TARGET_DIR + pathname + "/").replaceAll("//", "/");
		new File(targetDir).mkdirs();
		// START
		// 1a. analyze process1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename1 + ".bpmn")
						.getPath());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyzeAndDebug(def1,
				pathname, basename1, targetDir, nodes1);
		ProcessAnalysisUtil.writeToFile(
				targetDir + basename1 + "-analysis.xml", analysis1);
		// 1b. analyze process2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename2 + ".bpmn")
						.getPath());
		analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis2 = analyzer.analyzeAndDebug(def2,
				pathname, basename2, targetDir, nodes2);
		ProcessAnalysisUtil.writeToFile(
				targetDir + basename2 + "-analysis.xml", analysis2);
		// 2. match process1 and process2
		ProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		ProcessMatchingUtil.writeToFile(targetDir + "matching.xml", matching);
		// 3. extract processes
		ProcessTransformer transformer = new ProcessTransformerImpl();
		ProcessTransformation extraction = transformer.transform(matching);
		// END

		// Post
		ProcessTransformationUtil.writeToFile(targetDir + "extraction.xml",
				extraction);
		ProcessTransformationUtil.writeResults(targetDir, extraction);

		// TODO: assertions
	}
}
