package edu.udo.cs.ls14.jf.bpmnapplication;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnmatching.FragmentPair;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
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

	public ProcessExtraction extract(ProcessMatching pMatching)
			throws Exception {

		String filename1 = ProcessUtil.getProcessFromDefinitions(
				pMatching.getAnalysis1().getDefinitions()).getName()
				+ "_transformed.bpmn";
		String filename2 = ProcessUtil.getProcessFromDefinitions(
				pMatching.getAnalysis2().getDefinitions()).getName()
				+ "_transformed.bpmn";

		ProcessExtraction extraction = BpmnTransformationFactory.eINSTANCE
				.createProcessExtraction();
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
			defsExtracted.setTargetNamespace("http://" + idExtracted.toLowerCase());
			extractor.replaceId(defsExtracted, ProcessUtil
					.getProcessFromDefinitions(defsExtracted).getId(),
					EcoreUtil.generateUUID());
			extractor.cropFragment(defsExtracted, fPair.getBetter());
			extraction.getResults().put(idExtracted + ".bpmn", defsExtracted);
			LOG.info("SubProcess extracted.");

			// callActivity parameters
			String callActivityName = LabelGenerator
					.getLabel(fPair.getBetter());
			Process calledElement = ProcessUtil
					.getProcessFromDefinitions(defsExtracted);

			// replace fragment in Process1
			LOG.info("Replacing " + fPair.getA());
			extractor.replaceFragmentByCallActivity(fPair.getA()
					.getDefinitions(), fPair.getA(), callActivityName,
					calledElement);
			extraction.getResults().put(filename1,
					fPair.getA().getDefinitions());
			LOG.info("Replaced " + fPair.getA());

			// replace fragment in Process2
			LOG.info("Replacing " + fPair.getB());
			extractor.replaceFragmentByCallActivity(fPair.getB()
					.getDefinitions(), fPair.getB(), callActivityName,
					calledElement);
			extraction.getResults().put(filename2,
					fPair.getB().getDefinitions());
			LOG.info("Replaced " + fPair.getB());

		}

		return extraction;
	}

	private String getExtractedProcessIdPrefix(FragmentPair fPair)
			throws Exception {
		return ProcessUtil.getProcessFromDefinitions(
				fPair.getA().getDefinitions()).getName()
				+ "_"
				+ ProcessUtil.getProcessFromDefinitions(
						fPair.getB().getDefinitions()).getName()
				+ "_extracted_process-";
	}

}
