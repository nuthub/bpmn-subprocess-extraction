package edu.udo.cs.ls14.jf.transformation;

import java.util.UUID;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessTransformationFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class FragmentExtractor {

	private static final Logger LOG = LoggerFactory
			.getLogger(FragmentExtractor.class);
	private static Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();

	public static ProcessTransformation transform(ProcessMatching pMatching)
			throws Exception {

		ProcessTransformation extraction = ProcessTransformationFactory
				.createProcessTransformation(pMatching);

		// Copy definitions of matched processes
		Definitions definitions1 = EcoreUtil.copy(pMatching.getAnalysis1()
				.getDefinitions());
		Definitions definitions2 = EcoreUtil.copy(pMatching.getAnalysis2()
				.getDefinitions());
		definitions1.setTargetNamespace("http://" + UUID.randomUUID());
		definitions2.setTargetNamespace("http://" + UUID.randomUUID());

		// Need to create resources for copied definitions
		resSet.createResource(
				URI.createFileURI(UUID.randomUUID().toString() + ".bpmn"),
				definitions1);
		resSet.createResource(
				URI.createFileURI(UUID.randomUUID().toString() + ".bpmn"),
				definitions2);

		EGraph graph1 = new EGraphImpl(definitions1);
		EGraph graph2 = new EGraphImpl(definitions2);

		HenshinAdapter extractor = new HenshinAdapter();
		// Loop over all fragment matchings
		int fragmentCounter = 1;
		for (FragmentPair fPair : pMatching.getFragmentMatching().getPairs()) {
			// create new (sub)process
			LOG.info("Extracting SubProcess for Fragment "
					+ FragmentUtil.toString(fPair.getA()) + " / "
					+ FragmentUtil.toString(fPair.getB()));
			// Copy better definitions
			Definitions defsExtracted = EcoreUtil.copy(FragmentUtil
					.getDefinitions(fPair.getBetter()));
			// Need to create resource for copied definitions
			resSet.createResource(
					URI.createFileURI(UUID.randomUUID().toString() + ".bpmn"),
					defsExtracted);

			String idExtracted = getExtractedProcessIdPrefix(fPair)
					+ fragmentCounter++;
			defsExtracted.setTargetNamespace("http://"
					+ idExtracted.toLowerCase());
			defsExtracted.setId(UUID.randomUUID().toString());
			EGraph graph = new EGraphImpl(defsExtracted);
			extractor.cropFragment(graph, fPair.getBetter(), defsExtracted);
			LOG.info("Extracted SubProcess " + idExtracted + ".");
			Definitions defsCopy = DefinitionsUtil.copy(defsExtracted);
			extraction.getResults().put(idExtracted + ".bpmn", defsCopy);

			// callActivity parameters
			Process calledElement = DefinitionsUtil.getProcess(defsCopy);
			calledElement.setName(idExtracted);
			calledElement.setId(idExtracted.toLowerCase());

			// // replace fragment in Process1
			LOG.info("Replacing Fragment "
					+ FragmentUtil.toString(fPair.getA()) + " in Model 1");
			extractor.replaceFragmentByCallActivity(graph1, fPair.getA(),
					calledElement, fPair.getBetter().getLabel());
			LOG.info("Replaced Fragment " + FragmentUtil.toString(fPair.getA())
					+ " in Model 1");

			// replace fragment in Process2
			LOG.info("Replacing Fragment "
					+ FragmentUtil.toString(fPair.getB()) + " in Model 2");
			extractor.replaceFragmentByCallActivity(graph2, fPair.getB(),
					calledElement, fPair.getBetter().getLabel());
			LOG.info("Replaced Fragment " + FragmentUtil.toString(fPair.getB())
					+ " in Model 2");
		}

		// Add copies of definitions with modified IDs to result
		extraction.getResults().put(
				DefinitionsUtil.getProcess(definitions1).getName()
						+ "-modified.bpmn", DefinitionsUtil.copy(definitions1));
		extraction.getResults().put(
				DefinitionsUtil.getProcess(definitions2).getName()
						+ "-modified.bpmn", DefinitionsUtil.copy(definitions2));
		return extraction;
	}

	private static String getExtractedProcessIdPrefix(FragmentPair fPair)
			throws Exception {
		return DefinitionsUtil.getProcess(
				FragmentUtil.getDefinitions(fPair.getA())).getName()
				+ "_"
				+ DefinitionsUtil.getProcess(
						FragmentUtil.getDefinitions(fPair.getB())).getName()
				+ "-extraction-";
	}
}
