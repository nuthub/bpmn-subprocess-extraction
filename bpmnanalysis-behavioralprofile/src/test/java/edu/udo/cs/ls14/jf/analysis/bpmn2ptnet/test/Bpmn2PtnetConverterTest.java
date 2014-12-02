package edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;
import fr.lip6.move.pnml.ptnet.hlapi.PageHLAPI;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class Bpmn2PtnetConverterTest {

	private Bpmn2PtnetConverter converter;

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

		converter = new Bpmn2PtnetConverter();
	}

	@Test
	public void testLoopTransformation() throws Exception {
		String basename = "looping-events-example";
		runTest(basename, 9, 9, 18);
	}

	@Test
	public void testXorWEventsTransformation() throws Exception {
		String basename = "xor-example";
		runTest(basename, 9, 9, 18);
	}

	@Test
	public void testLoopingXorTransformation() throws Exception {
		String basename = "looping-xor";
		runTest(basename, 12, 13, 26);
	}

	@Test
	public void testPM1() throws Exception {
		String basename = "PM1-mit-Fragment1";
		runTest(basename, 12, 9, 22);
	}

	@Test
	public void testPM2() throws Exception {
		String basename = "PM2-mit-Fragment1";
		runTest(basename, 14, 11, 26);
	}

	@Test
	public void testEventBasedGatewayExclusive() throws Exception {
		String basename = "event-based-gateway-exclusive";
		runTest(basename, 8, 8, 16);
	}

	@Test
	public void testEventBasedGatewayParallel() throws Exception {
		String basename = "event-based-gateway-parallel";
		runTest(basename, 8, 6, 14);
	}

	private void runTest(String basename, int expectedPlaceCount,
			int expectedTransitionCount, int expectedArcCount) throws Exception {
		Definitions definitions = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath())
				.loadDefinitions(basename + ".bpmn");
		Process process = ProcessUtil.getProcessFromDefinitions(definitions);
		PetriNetHLAPI net = converter.convertToPetriNet(process);
		assertEquals(1, net.getPagesHLAPI().size());
		PageHLAPI page = net.getPagesHLAPI().get(0);
		assertEquals(expectedPlaceCount, page.getObjects_PlaceHLAPI().size());
		assertEquals(expectedTransitionCount, page.getObjects_TransitionHLAPI()
				.size());
		assertEquals(expectedArcCount, page.getObjects_ArcHLAPI().size());
		converter.saveToPnmlFile("/tmp/" + basename + ".pnml");

	}

}
