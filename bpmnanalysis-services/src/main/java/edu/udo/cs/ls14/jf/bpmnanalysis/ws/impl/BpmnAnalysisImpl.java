package edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl;

import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.BpmnAnalysisSEI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnanalysis.ws.BpmnAnalysisSEI")
public class BpmnAnalysisImpl implements BpmnAnalysisSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(BpmnAnalysisImpl.class);

	/**
	 * local version just calls other service implementations
	 */
	@Override
	public ProcessAnalysis analyze(Definitions definitions) throws Exception {
		LOG.info("Service input: " + definitions);
		ProcessAnalysis analysis = new ProcessAnalysisFactoryImpl()
				.createProcessAnalysis(definitions);
		analysis = new BehavioralProfilerImpl().profile(analysis);
		analysis = new ConditionalProfilerImpl().profile(analysis);
		analysis = new ProcessStructureTreeImpl().getPst(analysis);
		return analysis;
	}
}
