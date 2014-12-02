package edu.udo.cs.ls14.jf.analysis.behaviorprofile.test;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.jbpt.utils.IOUtils;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralRelation;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.Trace;
import edu.udo.cs.ls14.jf.bpmnanalysis.TraceProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingPackage;
import edu.udo.cs.ls14.jf.bpmnmatching.util.BpmnMatchingResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationPackage;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class BehavioralProfileTest {

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
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		outputBp(bp);
	}

	private void outputBp(BehavioralProfile bp) {
		for (BehavioralRelation rel : bp.getRelations()) {
			System.out.println(rel.getLeft().getName() + " / "
					+ rel.getRight().getName() + " : "
					+ rel.getRelation().toString());
		}

	}

	@Test
	public void testPM2() throws Exception {
		String basename = "PM2-mit-Fragment1";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testPM3() throws Exception {
		String basename = "PM3-mit-Fragment2";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testBpmn2bpLoopingXor() throws Exception {
		String basename = "looping-xor";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	@Test
	public void testBpmn2bpLoopingEvents() throws Exception {
		String basename = "looping-events-example";
		BehavioralProfile bp = createBpFromBpmn(basename);
		// print profile
		System.out.println(bp);
	}

	private BehavioralProfile createBpFromBpmn(String basename)
			throws Exception {
		System.out.println("Now profiling " + basename);
		// Load BPMN model
		Definitions definitions = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test").getPath())
				.loadDefinitions(basename + ".bpmn");
		Process process = ProcessUtil.getProcessFromDefinitions(definitions);

		// create P/T-Net from bpmn
		Bpmn2PtnetConverter converter = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = converter.convertToPetriNet(process);
		// converter.saveToPnmlFile("/tmp/" + basename + ".pnml");

		// create Reachability Graph from petri net
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		String dot = rg.toDot();

		IOUtils.invokeDOT("/tmp", basename + "-reachabilityGraph.png", dot);

		// Create Traces
		TraceProfile traceProfile = Tracer.getTraceProfile(process, rg);
		// output traces
		for (Trace trace : traceProfile.getTraces()) {
			for (FlowNode node : trace.getNodes()) {
				System.out
						.print(" -> "
								+ (node.getName() != null
										&& !node.getName().equals("") ? node
										.getName() : node.getId()));
			}
			if (trace.isFinished()) {
				System.out.println(" .");
			} else {
				System.out.println(" ...");
			}
		}
		// create Behavioral Profile
		BehavioralProfile bp = BehavioralProfiler.generateProfile(process,
				traceProfile);
		return bp;
	}

}
