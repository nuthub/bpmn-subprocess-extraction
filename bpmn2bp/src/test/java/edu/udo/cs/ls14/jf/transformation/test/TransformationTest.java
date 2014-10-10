package edu.udo.cs.ls14.jf.transformation.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNPlane;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.LoggingApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.javatuples.Pair;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.udo.cs.ls14.jf.processmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.processmatching.ProcessMatchingChain;
import edu.udo.cs.ls14.jf.pst.Fragment;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

public class TransformationTest {
	private Engine engine = null;

	private static final String RULEFILE = "sequenceRule";
	private static final String RULEPATH = "src/test/resources/edu/udo/cs/ls14/jf/henshin/";

	@Before
	public void setUp() {
		if (engine == null) {
			engine = new EngineImpl();
		}
	}

	@Test
	public void testExtractSequenceSequence2() throws Exception {
		String basename1 = "sequence";
		String basename2 = "sequence2";
		runTest(basename1, basename2);
		// testCoords(basename1, basename2);
	}

	@Test
	public void testPM1PM2() throws Exception {
		String basename1 = "PM1-mit-Fragment1";
		String basename2 = "PM2-mit-Fragment1";
		runTest(basename1, basename2);
		// testCoords(basename1, basename2);
	}

	public void testCoords(String basename1, String basename2) throws Exception {

		URL url1 = getClass().getResource("../../bpmn/" + basename1 + ".bpmn");
		URL url2 = getClass().getResource("../../bpmn/" + basename2 + ".bpmn");
		ProcessMatching matching = ProcessMatchingChain.getCandidates(url1,
				url2);
		assertEquals(1, matching.getFragmentCorrespondences().size());
		Pair<Fragment, Fragment> pair = matching.getFragmentCorrespondences()
				.iterator().next();

		// Process1
		Fragment fragment1 = pair.getValue0();
		getCoords(url1, fragment1);
	}

	private void runTest(String basename1, String basename2) throws Exception {
		URL url1 = getClass().getResource("../../bpmn/" + basename1 + ".bpmn");
		URL url2 = getClass().getResource("../../bpmn/" + basename2 + ".bpmn");
		ProcessMatching matching = ProcessMatchingChain.getCandidates(url1,
				url2);
		assertEquals(1, matching.getFragmentCorrespondences().size());
		Pair<Fragment, Fragment> pair = matching.getFragmentCorrespondences()
				.iterator().next();

		// Process1
		Fragment fragment1 = pair.getValue0();
		File targetFile1 = new File("/tmp/" + basename1 + "_transformed.bpmn2");
		replaceFragment(url1, fragment1, targetFile1);

		// Process2
		Fragment fragment2 = pair.getValue1();
		File targetFile2 = new File("/tmp/" + basename2 + "_transformed.bpmn2");
		replaceFragment(url2, fragment2, targetFile2);

	}

	private void replaceFragment(URL srcUrl, Fragment fragment, File targetFile)
			throws Exception {

		Set<FlowElement> elements = fragment
				.getContainedFlowElements(f -> true);

		String label = "My new Call Activity";

		// TODO: Calculate
		Pair<Float, Float> coords = getCoords(srcUrl, fragment);

		Set<String> nodeIds = new HashSet<String>();
		Set<String> edgeIds = new HashSet<String>();
		for (FlowElement e : elements) {
			if (e instanceof FlowNode) {
				nodeIds.add(e.getId());
			} else if (e instanceof SequenceFlow) {
				edgeIds.add(e.getId());
			}
		}

		Resource res1 = ProcessLoader.getBpmnResource(srcUrl);
		extract(res1, fragment.getEntry().getId(), fragment.getExit().getId(),
				nodeIds, edgeIds, label, coords.getValue0(), coords.getValue1());
		res1.save(new FileOutputStream(targetFile), null);
		System.out.println("Coords: " + coords);
	}

	@Test
	@Ignore
	public void test2Sequence() throws Exception {
		String basename = "sequence";

		String entryId = "SequenceFlow_3";
		String exitId = "SequenceFlow_6";
		String label = "My new Call Activity";
		int x = 360;
		int y = 44;

		Set<String> edgeIds = new HashSet<String>();
		edgeIds.add("SequenceFlow_4");
		edgeIds.add("SequenceFlow_5");
		Set<String> nodeIds = new HashSet<String>();
		nodeIds.add("Task_2");
		nodeIds.add("Task_3");
		nodeIds.add("Task_4");

		URL url = getClass().getResource("../../bpmn/" + basename + ".bpmn");
		Resource res = ProcessLoader.getBpmnResource(url);
		extract(res, entryId, exitId, nodeIds, edgeIds, label, x, y);
		res.save(new FileOutputStream(new File("/tmp/" + basename
				+ "_transformed.bpmn2")), null);
	}

	private void extract(Resource res, String entryId, String exitId,
			Set<String> nodeIds, Set<String> edgeIds, String label, float x,
			float y) throws Exception {

		EGraph graph = new EGraphImpl(res);
		Map<String, Object> parameters = new HashMap<String, Object>();

		// create call activity
		parameters.clear();
		String callActivityUuid = EcoreUtil.generateUUID();
		parameters.put("id", callActivityUuid);
		parameters.put("name", label);
		parameters.put("shapeId", callActivityUuid + "_gui");
		parameters.put("x", x);
		parameters.put("y", y);
		applyRule(graph, RULEFILE, "createCallActivity", parameters);

		// modify entry's target ref
		parameters.clear();
		parameters.put("id", entryId);
		parameters.put("targetId", callActivityUuid);
		applyRule(graph, RULEFILE, "setTargetRef", parameters);

		// modify exit's source ref
		parameters.clear();
		parameters.put("id", exitId);
		parameters.put("sourceId", callActivityUuid);
		applyRule(graph, RULEFILE, "setSourceRef", parameters);

		// delete sequenceflows
		parameters.clear();
		for (String edgeId : edgeIds) {
			parameters.put("id", edgeId);
			applyRule(graph, RULEFILE, "deleteSequenceFlow", parameters);
		}
		// delete flownodes
		parameters.clear();
		for (String nodeId : nodeIds) {
			parameters.put("id", nodeId);
			applyRule(graph, RULEFILE, "deleteFlowNode", parameters);
		}

	}

	private void applyRule(EGraph graph, String rulefileBaseName,
			String ruleName, Map<String, Object> parameters) {
		// Load rule
		HenshinResourceSet resourceSet = new HenshinResourceSet(RULEPATH);
		resourceSet.registerXMIResourceFactories("bpmn2");
		resourceSet.getPackageRegistry().put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("bpmn2", new Bpmn2ResourceFactoryImpl());
		Module module = resourceSet.getModule(rulefileBaseName + ".henshin");
		Unit unit = module.getUnit(ruleName);
		assertNotNull(unit);
		UnitApplication app = new UnitApplicationImpl(engine, graph, unit, null);
		for (Map.Entry<String, Object> p : parameters.entrySet()) {
			app.setParameterValue(p.getKey(), p.getValue());
		}
		assertTrue(app.execute(null));
		// assertTrue(app.execute(new LoggingApplicationMonitor()));
	}

	private Pair<Float, Float> getCoords(URL url, Fragment fragment)
			throws Exception {
		Set<FlowNode> nodes = fragment.getContainedFlowNodes(n -> true);
		Set<String> nodeIds = nodes.stream().map(n -> n.getId())
				.collect(Collectors.toSet());
		// TODO: reuse resource
		BPMNDiagram diagram = ProcessLoader.getDiagram(url);
		BPMNPlane plane = diagram.getPlane();
		float minX = -1;
		float maxX = 0;
		float minY = -1;
		float maxY = 0;
		for (DiagramElement element : plane.getPlaneElement()) {
			if (!(element instanceof BPMNShape)) {
				continue;
			}
			BPMNShape shape = (BPMNShape) element;
			if (!nodeIds.contains(shape.getBpmnElement().getId())) {
				continue;
			}
			float centerX = shape.getBounds().getX()
					+ shape.getBounds().getWidth() / 2;
			minX = minX == -1 ? centerX : Math.min(minX, centerX);
			maxX = Math.max(maxX, centerX);

			float centerY = shape.getBounds().getY()
					+ shape.getBounds().getHeight() / 2;
			minY = minY == -1 ? centerY : Math.min(minY, centerY);
			maxY = Math.max(maxY, centerY);
		}
		float x = minX + ((maxX - minX) / 2);
		float y = minY + ((maxY - minY) / 2);
		System.out.println("X: [" + minX + ", " + maxX + "], x=" + x);
		System.out.println("Y: [" + minY + ", " + maxY + "], y=" + y);
		return Pair.with(x, y);
	}
}
