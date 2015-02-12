package edu.udo.cs.ls14.jf.bpmntransformation.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.eclipse.bpmn2.Definitions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.IProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.IProcessMatcher;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.ExtractorImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentCoordinateCalculatorImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentLabellerImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentPairFilterTrivialImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentPairRankerCFCImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.FragmentPairRankerSizeImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.ModifierImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.impl.ProcessTransformationFactoryImpl;

public class BpmnTransformationTest {

	private List<Endpoint> endpoints;
	private FragmentPairFilterTrivialSEI portFragmentPairFilterTrivial;
	private FragmentPairRankerCFCSEI portFragmentPairRankerCFC;
	private FragmentPairRankerSizeSEI portFragmentPairRankerSize;
	private FragmentLabellerSEI portFragmentLabeller;
	private FragmentCoordinateCalculatorSEI portFragmentCoordinateCalculator;
	private ProcessTransformationFactorySEI portProcessTransformationFactory;
	private ExtractorSEI portExtractor;
	private ModifierSEI portModifier;

	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() throws Exception {
		String namespace = "http://impl.ws.bpmntransformation.jf.ls14.cs.udo.edu/";
		String globalPart = "http://localhost:8880/bpmntransformation-services/";
		String localPart = null;
		endpoints = new ArrayList<Endpoint>();

		// FragmentPairFilterTrivial
		localPart = "FragmentPairFilterTrivialImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new FragmentPairFilterTrivialImpl()));
		portFragmentPairFilterTrivial = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterTrivialSEI.class);

		// FragmentPairRankerSize
		localPart = "FragmentPairRankerSizeImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new FragmentPairRankerSizeImpl()));
		portFragmentPairRankerSize = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairRankerSizeSEI.class);

		// FragmentPairRankerCFC
		localPart = "FragmentPairRankerCFCImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new FragmentPairRankerCFCImpl()));
		portFragmentPairRankerCFC = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairRankerCFCSEI.class);

		// FragmentLabeller
		localPart = "FragmentLabellerImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new FragmentLabellerImpl()));
		portFragmentLabeller = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentLabellerSEI.class);

		// FragmentCoordinateCalculator
		localPart = "FragmentCoordinateCalculatorImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new FragmentCoordinateCalculatorImpl()));
		portFragmentCoordinateCalculator = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentCoordinateCalculatorSEI.class);

		// ProcessTransformationFactory
		localPart = "ProcessTransformationFactoryImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new ProcessTransformationFactoryImpl()));
		portProcessTransformationFactory = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				ProcessTransformationFactorySEI.class);

		// Extractor
		localPart = "ExtractorImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new ExtractorImpl()));
		portExtractor = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(ExtractorSEI.class);

		// FragmentPairFilterNestings
		localPart = "ModifierImplService";
		endpoints.add(Endpoint.publish(globalPart + localPart,
				new ModifierImpl()));
		portModifier = Service.create(
				new URL(globalPart + localPart + "?wsdl"),
				new QName(namespace, localPart)).getPort(ModifierSEI.class);

		// Register resource factories
		Registries.registerAll();
		resSet = Bpmn2ResourceSet.getInstance();

	}

	@After
	public void tearDown() {
		for (Endpoint endpoint : endpoints) {
			endpoint.stop();
		}
	}

	@Test
	public void testPM1PM2() throws Exception {
		// create some test data
		String pathname = "/bpmn/parallelGateway/";
		Definitions definitions1 = resSet.loadDefinitions(getClass()
				.getResource(pathname + "parallelism1.bpmn").getFile());
		Definitions definitions2 = resSet.loadDefinitions(getClass()
				.getResource(pathname + "parallelism2.bpmn").getFile());
		// ProcessAnalyzer
		IProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyze(definitions1);
		ProcessAnalysis analysis2 = analyzer.analyze(definitions2);
		// ProcessMatcher
		IProcessMatcher matcher = new ProcessMatcherImpl();
		ProcessMatching processMatching = matcher.match(analysis1, analysis2);
		// ProcessTransformationServices
		assertEquals(1, processMatching.getFragmentMatching().getPairs().size());
		processMatching = portFragmentPairFilterTrivial.filter(processMatching);
		assertEquals(1, processMatching.getFragmentMatching().getPairs().size());

		assertNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getBetter());
		processMatching = portFragmentPairRankerSize.rank(processMatching);
		assertEquals(processMatching.getFragmentMatching().getPairs().get(0)
				.getA(), processMatching.getFragmentMatching().getPairs()
				.get(0).getBetter());
		processMatching = portFragmentPairRankerCFC.rank(processMatching);
		assertEquals(processMatching.getFragmentMatching().getPairs().get(0)
				.getA(), processMatching.getFragmentMatching().getPairs()
				.get(0).getBetter());

		assertNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getA().getLabel());
		assertNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getB().getLabel());
		processMatching = portFragmentLabeller.generateLabels(processMatching);
		assertNotNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getA().getLabel());
		assertNotNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getB().getLabel());
		assertNotEquals("", processMatching.getFragmentMatching().getPairs()
				.get(0).getA().getLabel());
		assertNotEquals("", processMatching.getFragmentMatching().getPairs()
				.get(0).getB().getLabel());

		assertNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getA().getCenter());
		processMatching = portFragmentCoordinateCalculator
				.calculateCoordinates(processMatching);
		assertNotNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getA().getCenter());

		ProcessTransformation transformation = portProcessTransformationFactory
				.create(processMatching);
		assertNotNull(transformation);

		assertNull(processMatching.getFragmentMatching().getPairs().get(0)
				.getExtractedProcess());
		transformation = portExtractor.extract(transformation);
		assertNotNull(transformation.getProcessMatching().getFragmentMatching()
				.getPairs().get(0).getExtractedProcess());
		assertEquals(1, transformation.getResults().size());

		transformation = portModifier.modify(transformation);
		assertEquals(3, transformation.getResults().size());

	}

	@Test(expected = RuntimeException.class)
	public void runNullTest1() {
		portFragmentPairFilterTrivial.filter(null);
	}

	@Test(expected = RuntimeException.class)
	public void runNullTest2() {
		portFragmentPairRankerSize.rank(null);
	}

	@Test(expected = RuntimeException.class)
	public void runNullTest3() {
		portFragmentPairRankerCFC.rank(null);
	}

	@Test(expected = RuntimeException.class)
	public void runNullTest4() {
		portFragmentLabeller.generateLabels(null);
	}

	@Test(expected = RuntimeException.class)
	public void runNullTest5() {
		portFragmentCoordinateCalculator.calculateCoordinates(null);
	}

	@Test(expected = RuntimeException.class)
	public void runNullTest6() {
		portExtractor.extract(null);
	}

	@Test(expected = RuntimeException.class)
	public void runNullTest7() {
		portModifier.modify(null);
	}

}
