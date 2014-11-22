package edu.udo.cs.ls14.jf.bpmnapplication;

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
import edu.udo.cs.ls14.jf.transformation.FragmentExtractor;
import edu.udo.cs.ls14.jf.transformation.LabelGenerator;

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

		String filename1 = ProcessUtil.getProcessFromDefinitions(
				pMatching.getAnalysis1().getDefinitions()).getName()
				+ "_transformed.bpmn";
		String filename2 = ProcessUtil.getProcessFromDefinitions(
				pMatching.getAnalysis2().getDefinitions()).getName()
				+ "_transformed.bpmn";
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
			fPair.getBetter().setLabel(
					LabelGenerator.getLabel(fPair.getBetter()));

			// replace fragment in Process1
			LOG.info("Replacing " + fPair.getA());
			extractor.replaceFragmentByCallActivity(fPair.getA(), fPair
					.getBetter().getLabel(), calledElement);
			LOG.info("Replaced " + fPair.getA());

			// replace fragment in Process2
			LOG.info("Replacing " + fPair.getB());
			extractor.replaceFragmentByCallActivity(fPair.getB(), fPair
					.getBetter().getLabel(), calledElement);
			LOG.info("Replaced " + fPair.getB());

			// Add definitions to result
			extraction.getResults().put(filename1,
					fPair.getA().getDefinitions());
			extraction.getResults().put(filename2,
					fPair.getB().getDefinitions());
		}
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
