package edu.udo.cs.ls14.jf.ws.bpmn.analysis;

import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.ws.bpmn.behavioralprofile.BehavioralProfilerImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile.ConditionalProfilerImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.pst.ProcessStructureTreeImpl;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.analysis.BpmnAnalysisSEI")
public class BpmnAnalysisImpl implements BpmnAnalysisSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(BpmnAnalysisImpl.class);

	/**
	 * local version just calls other service implementations
	 */
	@Override
	public ProcessAnalysis analyze(Definitions definitions) throws Exception {
		LOG.info("Service input: " + definitions);
		ProcessAnalysis analysis = ProcessAnalysisFactory
				.createAnalysis(definitions);
		analysis = new BehavioralProfilerImpl().profile(analysis);
		analysis = new ConditionalProfilerImpl().profile(analysis);
		analysis = new ProcessStructureTreeImpl().getPst(analysis);
		return analysis;
	}
}
