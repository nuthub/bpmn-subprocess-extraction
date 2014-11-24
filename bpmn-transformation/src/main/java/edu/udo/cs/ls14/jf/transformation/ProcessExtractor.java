package edu.udo.cs.ls14.jf.transformation;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessExtractionFactory;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessExtraction;

/**
 * 
 * @author flake
 *
 */
public class ProcessExtractor {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessExtractor.class);

	public static ProcessExtraction extract(ProcessMatching pMatching)
			throws Exception {

		// Copy definitions of matched processes
		Definitions definitions1 = EcoreUtil.copy(pMatching.getAnalysis1()
				.getDefinitions());
		Definitions definitions2 = EcoreUtil.copy(pMatching.getAnalysis2()
				.getDefinitions());

		// Create extraction object (-> process?)
		ProcessExtraction extraction = ProcessExtractionFactory
				.createProcessTransformation(pMatching);

		FragmentExtractor extractor = new FragmentExtractor();
		// Loop over all fragment matchings
		int fragmentCounter = 1;
		for (FragmentPair fPair : pMatching.getFragmentMatching().getPairs()) {
			// create new (sub)process
			LOG.info("Extracting SubProcess.");
			Definitions defsExtracted = EcoreUtil.copy(fPair.getBetter()
					.getDefinitions());
			String idExtracted = getExtractedProcessIdPrefix(fPair)
					+ fragmentCounter++;
			defsExtracted.setTargetNamespace("http://"
					+ idExtracted.toLowerCase());
			defsExtracted.setId(EcoreUtil.generateUUID());
			extractor.replaceId(defsExtracted, ProcessUtil
					.getProcessFromDefinitions(defsExtracted).getId(),
					EcoreUtil.generateUUID());
			extractor.cropFragment(defsExtracted, fPair.getBetter());
			extraction.getResults().put(idExtracted + ".bpmn", defsExtracted);
			LOG.info("SubProcess extracted.");

			// callActivity parameters
			Process calledElement = ProcessUtil
					.getProcessFromDefinitions(defsExtracted);
			calledElement.setName(idExtracted);
			calledElement.setId(idExtracted.toLowerCase());

			// replace fragment in Process1
			LOG.info("Replacing " + fPair.getA());

			extractor.replaceFragmentByCallActivity(definitions1, fPair.getA(),
					fPair.getBetter().getLabel(), calledElement);
			LOG.info("Replaced " + fPair.getA());

			// /replace fragment in Process2
			LOG.info("Replacing " + fPair.getB());
			extractor.replaceFragmentByCallActivity(definitions2, fPair.getB(),
					fPair.getBetter().getLabel(), calledElement);
			LOG.info("Replaced " + fPair.getB());

		}
		// Add definitions to result
		extraction.getResults().put(
				ProcessUtil.getProcessFromDefinitions(definitions1).getName()
						+ "_transformed.bpmn", definitions1);
		extraction.getResults().put(
				ProcessUtil.getProcessFromDefinitions(definitions2).getName()
						+ "_transformed.bpmn", definitions2);
		return extraction;
	}

	private static String getExtractedProcessIdPrefix(FragmentPair fPair)
			throws Exception {
		return ProcessUtil.getProcessFromDefinitions(
				fPair.getA().getDefinitions()).getName()
				+ "_"
				+ ProcessUtil.getProcessFromDefinitions(
						fPair.getB().getDefinitions()).getName()
				+ "_extracted_process-";
	}

}
