package edu.udo.cs.ls14.jf.bpmnapplication.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnapplication.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class ProcessAnalyzerTest {
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

	private int getPstSize(ProcessAnalysis analysis) {
		return ((ProcessStructureTree) analysis.getResults().get("pst"))
				.getFragments().size();
	}

	private int getCpSize(ProcessAnalysis analysis) {
		return ((ConditionalProfile) analysis.getResults().get(
				"conditionalProfile")).getRelations().size();
	}

	@Test
	public void testAnalysisPM1() throws Exception {
		Definitions def = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadDefinitions("PM1-mit-Fragment1.bpmn");
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(def);
		assertEquals(6, getPstSize(analysis));
		assertEquals(7, getCpSize(analysis));
	}

	@Test
	public void testAnalysisConditionSequence() throws Exception {
		Definitions def = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadDefinitions("conditionalFlow/conditionSequence.bpmn");
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(def);
		assertEquals(5, getPstSize(analysis));
		assertEquals(5, getCpSize(analysis));
	}

	@Test
	public void testAnalysisConditionSequence2() throws Exception {
		Definitions def = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadDefinitions("conditionalFlow/conditionSequence2.bpmn");
		ProcessAnalysis analysis = ProcessAnalyzer.analyze(def);
		assertEquals(5, getPstSize(analysis));
		assertEquals(5, getCpSize(analysis));
	}
}
