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

import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.FragmentUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public class Extractor {

	private static final Logger LOG = LoggerFactory.getLogger(Extractor.class);
	private static Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();

	public static ProcessTransformation extract(
			ProcessTransformation transformation) throws Exception {

		HenshinAdapter extractor = new HenshinAdapter();
		// Loop over all fragment matchings
		int fragmentCounter = 1;
		for (FragmentPair fPair : transformation.getProcessMatching()
				.getFragmentMatching().getPairs()) {
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
			transformation.getResults().put(idExtracted + ".bpmn", defsCopy);

			// callActivity parameters
			Process calledElement = DefinitionsUtil.getProcess(defsCopy);
			calledElement.setName(idExtracted);
			calledElement.setId(idExtracted.toLowerCase());

			fPair.setExtractedProcess(calledElement);
		}

		return transformation;
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
