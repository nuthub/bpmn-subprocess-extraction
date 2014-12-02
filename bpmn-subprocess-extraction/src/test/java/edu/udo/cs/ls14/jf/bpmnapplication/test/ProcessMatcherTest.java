package edu.udo.cs.ls14.jf.bpmnapplication.test;

import static org.junit.Assert.assertEquals;

import java.util.function.Predicate;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class ProcessMatcherTest {

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
				.putIfAbsent("bpmnextraction",
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
	public void testConditionSequence1ConditionSequence2() throws Exception {
		String basename1 = "conditionSequence";
		String basename2 = "conditionSequence2";
		System.out.println("Testing " + basename1 + " with " + basename2);
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/conditionalFlow/").getPath());
		Definitions def1 = resSet.loadDefinitions(basename1 + ".bpmn");
		Definitions def2 = resSet.loadDefinitions(basename2 + ".bpmn");
		runTest(def1, def2, 1);
	}

	@Test
	public void testPm1WithPm2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		System.out.println("Testing " + basename1 + " with " + basename2);
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath());
		Definitions def1 = resSet.loadDefinitions(basename1 + ".bpmn");
		Definitions def2 = resSet.loadDefinitions(basename2 + ".bpmn");
		runTest(def1, def2, 1);
	}

	private ProcessMatching runTest(Definitions definitions1,
			Definitions definitions2, int expectedFCs) throws Exception {

		// analyze process1
		ProcessAnalysis analysis1 = ProcessAnalyzer.analyze(definitions1);
		// analyze process2
		ProcessAnalysis analysis2 = ProcessAnalyzer.analyze(definitions2);

		ProcessMatching matching = ProcessMatcher.match(analysis1, analysis2);
		printMatching(matching);
		assertEquals(expectedFCs, matching.getFragmentMatching().getPairs()
				.size());
		return matching;
	}

	private void printMatching(ProcessMatching matching) {
		Predicate<FlowElement> filter = n -> n instanceof Event
				|| n instanceof Activity;
		matching.getFragmentMatching()
				.getPairs()
				.stream()
				.forEach(
						p -> System.out.println(p.getA()
								+ " with "
								+ FragmentUtil
										.getFlowElements(p.getA(), filter)
										.size()
								+ " As/Es corresponds to "
								+ p.getB()
								+ " with "
								+ FragmentUtil
										.getFlowElements(p.getB(), filter)
										.size() + " As/Es."));

	}
}
