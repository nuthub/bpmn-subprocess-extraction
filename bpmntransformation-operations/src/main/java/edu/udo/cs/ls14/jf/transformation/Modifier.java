package edu.udo.cs.ls14.jf.transformation;

import java.util.UUID;

import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class Modifier {

	private static final Logger LOG = LoggerFactory.getLogger(Modifier.class);
	private static Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();

	public static ProcessTransformation modify(
			ProcessTransformation transformation) throws Exception {
		// Copy definitions of matched processes
		Definitions definitions1 = EcoreUtil.copy(transformation
				.getProcessMatching().getAnalysis1().getDefinitions());
		Definitions definitions2 = EcoreUtil.copy(transformation
				.getProcessMatching().getAnalysis2().getDefinitions());
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
		for (FragmentPair fPair : transformation.getProcessMatching()
				.getFragmentMatching().getPairs()) {
			// create new (sub)process
			CallableElement calledElement = fPair.getExtractedProcess();
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
		transformation.getResults().put(
				DefinitionsUtil.getProcess(definitions1).getName()
						+ "-modified.bpmn", DefinitionsUtil.copy(definitions1));
		transformation.getResults().put(
				DefinitionsUtil.getProcess(definitions2).getName()
						+ "-modified.bpmn", DefinitionsUtil.copy(definitions2));
		return transformation;
	}
}
