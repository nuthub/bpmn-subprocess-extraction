package edu.udo.cs.ls14.jf.bpmnanalysis.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.BehavioralProfiler;
import edu.udo.cs.ls14.jf.analysis.bpmn2ptnet.Bpmn2PtnetConverter;
import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.analysis.pst.PST;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.ReachabilityGraph;
import edu.udo.cs.ls14.jf.analysis.reachabilitygraph.Tracer;
import edu.udo.cs.ls14.jf.bpmn.utils.BpmnXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.bpmnanalysis.BehavioralProfile;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import fr.lip6.move.pnml.ptnet.hlapi.PetriNetHLAPI;

public class BpmnAnalysisTest {

	@Test
	public void testModel() throws Exception {
		long start, end, before, after;
		start = getTime();
		before = getTime();
		ProcessAnalysis a = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		after = getTime();
		System.out.println((after - before) + " ms (create analysis)");

		// String defXml =
		// readFile("/edu/udo/cs/ls14/jf/bpmn/conditionalFlow/conditionSequence.bpmn");
		before = getTime();
		String defXml = readFile("/edu/udo/cs/ls14/jf/bpmn/PM1-mit-Fragment1.bpmn");
		after = getTime();
		System.out.println((after - before) + " ms (read file)");
		before = getTime();
		Definitions definitions = (Definitions) BpmnXmlConverter.xml2Bpmn(
				defXml, Definitions.class);
		after = getTime();
		System.out.println((after - before) + " ms (xml2bpmn)");
		before = getTime();
		a.setDefinitions(definitions);
		after = getTime();
		System.out.println((after - before) + " ms (set definitions)");

		// Get Process
		before = getTime();
		Process process = ProcessLoader.getProcessFromDefinitions(definitions);
		after = getTime();
		System.out.println((after - before) + " ms (get Process)");

		// TODO: Petrinet, RG, Traces,
		before = getTime();
		Bpmn2PtnetConverter converter = new Bpmn2PtnetConverter();
		PetriNetHLAPI ptnet = converter.convertToPetriNet(process);
		ReachabilityGraph rg = new ReachabilityGraph();
		rg.createFromPTNet(ptnet.getContainedItem());
		// BehavioralProfile
		// - Traces
		BehavioralProfile eBp = BpmnAnalysisFactory.eINSTANCE
				.createBehavioralProfile();
		// TODO: Bad Smell: addAll
		eBp.getTraces().addAll(Tracer.getTraces(process, rg));
		// - BehavioralRelations
		eBp.getRelations().addAll(
				BehavioralProfiler.generateProfile(process, eBp.getTraces()));
		a.getResults().put("behavioralProfile", eBp);

		// ConditionalProfile
		a.getResults().put("conditionalProfile",
				ConditionalProfiler.generateProfile(process));

		// PST
		ProcessStructureTree ePst = BpmnAnalysisFactory.eINSTANCE
				.createProcessStructureTree();
		PST pst = new PST();
		pst.createFromDefinitions(definitions);
		ePst.getFragments().addAll(pst.getFragments());
		a.getResults().put("pst", ePst);
		after = getTime();
		System.out.println((after - before) + " ms (complete analysis)");

		// ///////////////
		before = getTime();
		EObjectXmlConverter.eObject2Xml(a);
		after = getTime();
		System.out.println((after - before) + " ms (eObject2Xml)");
		end = getTime();
		System.out.println((end - start) + " ms (total)");

	}

	private long getTime() {
		return System.currentTimeMillis();
	}

	private String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(getClass().getResource(
				path).getPath())), "UTF-8");
	}
}
