package edu.udo.cs.ls14.jf.bpmnanalysis.ws.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.ProcessAnalysisUtil;
import edu.udo.cs.ls14.jf.bpmnanalysis.ws.ProcessStructureTreeSEI;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmnanalysis.ws.ProcessStructureTreeSEI")
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
			LOG.error("An exception occurred: ", e);
			return null;
		}
	}

}
