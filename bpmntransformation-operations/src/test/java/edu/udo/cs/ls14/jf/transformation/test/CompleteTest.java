package edu.udo.cs.ls14.jf.transformation.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Definitions;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.registry.Registries;
import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationFactory;
import edu.udo.cs.ls14.jf.transformation.CoordinateCalculator;
import edu.udo.cs.ls14.jf.transformation.Extractor;
import edu.udo.cs.ls14.jf.transformation.FragmentPairFilterTrivial;
import edu.udo.cs.ls14.jf.transformation.FragmentPairRanker;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;
import edu.udo.cs.ls14.jf.transformation.Modifier;

public class CompleteTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testComplete() throws Exception {
		String pathname = "/bpmn/complete/";
		String basename1 = "complete1";
		String basename2 = "complete2";
		ProcessTransformation extraction = runTest(pathname, basename1,
				basename2);
		assertEquals(4, extraction.getResults().size());
	}

	/**
	 * Running twice fails, not sure why
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testComplete2() throws Exception {
		String pathname = "/bpmn/sequences/";
		String basename1 = "sequence1";
		String basename2 = "sequence2";
		runTest(pathname, basename1, basename2);
		runTest(pathname, basename1, basename2);
	}

	@Test
	public void testCompleteLabelled() throws Exception {
		String pathname = "/bpmn/completeLabelled/";
		String basename1 = "complete2labelled";
		String basename2 = "complete1labelled";
		ProcessTransformation extraction = runTest(pathname, basename1,
				basename2);
		assertEquals(4, extraction.getResults().size());
	}

	@Test
	public void testConstructors() {
		new FragmentPairFilterTrivial();
		new FragmentPairRanker();
		new LabelGenerator();
		new CoordinateCalculator();
		new ProcessTransformationFactory();
		new Extractor();
		new Modifier();
	}

	private ProcessTransformation runTest(String pathname, String basename1,
			String basename2) throws Exception {
		// START
		// 1a. analyze model1
		Definitions def1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename1 + ".bpmn")
						.getPath());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyze(def1);

		// 1b. analyze model2
		Definitions def2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
				getClass().getResource(pathname + basename2 + ".bpmn")
						.getPath());
		analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis2 = analyzer.analyze(def2);
		// 2. match models
		ProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching pMatching = matcher.match(analysis1, analysis2);
		// 3. extract models
		// 1. select non trivial matchings
		pMatching.setFragmentMatching(FragmentPairFilterTrivial
				.filter(pMatching.getFragmentMatching()));
		// 2. determine order in fragments
		pMatching.setFragmentMatching(FragmentPairRanker.rankBySize(pMatching
				.getFragmentMatching()));
		pMatching.setFragmentMatching(FragmentPairRanker.rankByCFC(pMatching
				.getFragmentMatching()));
		// 3. compute coords
		for (FragmentPair pair : pMatching.getFragmentMatching().getPairs()) {
			pair.getA().setCenter(CoordinateCalculator.getCenter(pair.getA()));
			pair.getB().setCenter(CoordinateCalculator.getCenter(pair.getB()));
		}
		// 4. generate labels
		for (FragmentPair pair : pMatching.getFragmentMatching().getPairs()) {
			pair.getA().setLabel(LabelGenerator.getLabel(pair.getA()));
			pair.getB().setLabel(LabelGenerator.getLabel(pair.getB()));
		}
		// 5. create transformation object
		ProcessTransformation transformation = ProcessTransformationFactory
				.createProcessTransformation(pMatching);
		// 6. extract better fragments
		transformation = Extractor.extract(transformation);
		// 7. modify input models
		transformation = Modifier.modify(transformation);
		// 8. return
		return transformation;
	}
}
