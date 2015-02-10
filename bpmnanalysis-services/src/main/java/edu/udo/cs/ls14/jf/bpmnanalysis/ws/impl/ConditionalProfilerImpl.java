package edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl;

import javax.jws.WebService;

import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.ConditionalProfilerSEI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnanalysis.ws.ConditionalProfilerSEI")
public class ConditionalProfilerImpl implements ConditionalProfilerSEI {

	@Override
	public ProcessAnalysis profile(ProcessAnalysis processAnalysis) {
		try {
			Process process = DefinitionsUtil.getProcess(processAnalysis
					.getDefinitions());
			processAnalysis.getResults().put(
					ProcessAnalysisUtil.CONDITIONALPROFILE,
					ConditionalProfiler.generateProfile(process));
			return processAnalysis;
		} catch (Exception e) {
			return null;
		}
	}

}
