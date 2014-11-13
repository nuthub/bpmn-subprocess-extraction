package edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile;

import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;

import edu.udo.cs.ls14.jf.analysis.conditionalprofile.ConditionalProfiler;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ConditionalProfile;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.conditionalprofile.ConditionalProfilerSEI")
public class ConditionalProfilerImpl implements ConditionalProfilerSEI {

	@Override
	public ConditionalProfile profile(Definitions definitions) throws Exception {
		Process process = ProcessUtil.getProcessFromDefinitions(definitions);
		return ConditionalProfiler.generateProfile(process);
	}

}
