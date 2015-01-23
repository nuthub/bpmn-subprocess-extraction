package edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile;

import javax.jws.WebService;

import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.DefinitionsUtil;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile.ConditionalProfilerSEI")
public class ConditionalProfilerImpl implements ConditionalProfilerSEI {

	@Override
	public ProcessAnalysis profile(ProcessAnalysis processAnalysis) {
		Process process;
		try {
			process = DefinitionsUtil.getProcess(processAnalysis
					.getDefinitions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		try {
			processAnalysis.getResults().put(
					ProcessAnalysisUtil.CONDITIONALPROFILE,
					ConditionalProfiler.generateProfile(process));
			return processAnalysis;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
