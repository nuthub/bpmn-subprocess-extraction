package edu.udo.cs.ls14.jf.ws.bpmn.pst;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.bpmn.utils.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.pst.ProcessStructureTreeSEI")
public class ProcessStructureTreeImpl implements ProcessStructureTreeSEI {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProcessStructureTreeImpl.class);

	@Override
	public ProcessAnalysis getPst(ProcessAnalysis processAnalysis) {
		PSTBuilder pstBuilder = new PSTBuilder();
		try {
			ProcessStructureTree pst = pstBuilder.getTree(processAnalysis
					.getDefinitions());
			processAnalysis.getResults().put(
					ProcessAnalysisUtil.PROCESSTRUCTURETREE, pst);
			return processAnalysis;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("An exception occurred: " + e.getMessage());
			return null;
		}
	}

}
