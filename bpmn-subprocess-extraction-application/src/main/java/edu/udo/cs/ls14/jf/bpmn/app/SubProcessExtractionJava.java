package edu.udo.cs.ls14.jf.bpmn.app;

import java.io.ByteArrayOutputStream;

import org.eclipse.bpmn2.Definitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmn.analysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmn.matching.ProcessMatcher;
import edu.udo.cs.ls14.jf.bpmn.matching.ProcessMatcherImpl;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformer;
import edu.udo.cs.ls14.jf.bpmn.transformation.ProcessTransformerImpl;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.registry.Registries;

public class SubProcessExtractionJava implements SubProcessExtraction {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SubProcessExtractionJava.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtraction#init()
	 */
	@Override
	public void init() {
		Registries.registerAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.udo.cs.ls14.jf.bpmn.app.SubProcessExtraction#run(org.eclipse.bpmn2
	 * .Definitions, org.eclipse.bpmn2.Definitions)
	 */
	@Override
	public ProcessTransformation run(Definitions defs1, Definitions defs2)
			throws Exception {
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		long timeAnalysis1Start = System.currentTimeMillis();
		ProcessAnalysis analysis1 = analyzer.analyze(defs1);
		long timeAnalysis1End = System.currentTimeMillis();
		long timeAnalysis2Start = System.currentTimeMillis();
		ProcessAnalysis analysis2 = analyzer.analyze(defs2);
		long timeAnalysis2End = System.currentTimeMillis();

		ProcessMatcher matcher = new ProcessMatcherImpl();
		long timeMatchingStart = System.currentTimeMillis();
		ProcessMatching matching = matcher.match(analysis1, analysis2);
		long timeMatchingEnd = System.currentTimeMillis();

		ProcessTransformer transformer = new ProcessTransformerImpl();
		long timeTransformationStart = System.currentTimeMillis();
		ProcessTransformation transformation = transformer.transform(matching);
		long timeTransformationEnd = System.currentTimeMillis();

		LOGGER.info("Time for Analyzing Model 1       : "
				+ (timeAnalysis1End - timeAnalysis1Start) + "ms");
		LOGGER.info("Time for Analyzing Model 2       : "
				+ (timeAnalysis2End - timeAnalysis2Start) + "ms");
		LOGGER.info("Time for Matching Models         : "
				+ (timeMatchingEnd - timeMatchingStart) + "ms");
		LOGGER.info("Time for Transformation of Models: "
				+ (timeTransformationEnd - timeTransformationStart) + "ms");

		return transformation;
	}
}
