package edu.udo.cs.ls14.jf.analysis.reachabilitygraph.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class TracerTest {

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
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		runTest(basename, 6);
	}

	@Test
	public void testPM2() throws Exception {
		String basename = "PM2-mit-Fragment1";
		// TODO: check if 10 is expected
		runTest(basename, 10);
	}

	@Test
	public void testXorExample() throws Exception {
		String basename = "xor-example";
		runTest(basename, 2);
	}

	@Test
	public void testLoopingEventsExample() throws Exception {
		String basename = "looping-events-example";
		runTest(basename, 2);
	}

	@Test
	public void loopingLoopingXor() throws Exception {
		String basename = "looping-xor";
		runTest(basename, 3);
	}

	@Test
	public void eventBasedGatewayExclusive() throws Exception {
		String basename = "event-based-gateway-exclusive";
		runTest(basename, 2);
	}

	@Test
	public void eventBasedGatewayParallel() throws Exception {
		String basename = "event-based-gateway-parallel";
		runTest(basename, 2);
	}

	private void runTest(String basename, int expectedTracesSize)
			throws Exception {
		Definitions definitions = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadDefinitions(basename + ".bpmn");
		Process process = ProcessUtil.getProcessFromDefinitions(definitions);

		System.out.println("Now testing " + basename);
		ReachabilityGraph rg = new ReachabilityGraph();
		File file = new File(getClass().getResource(
				"/edu/udo/cs/ls14/jf/pnml/" + basename + ".pnml").toURI());
		rg.createFromPnml(file);
		System.out.println("|V| = " + rg.getVertices().size());
		System.out.println("|E| = " + rg.getEdges().size());
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		traceProfile.getTraces().forEach(t -> System.out.println(t));
		assertEquals(expectedTracesSize, traceProfile.getTraces().size());
	}

}
