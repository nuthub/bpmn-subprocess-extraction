package edu.udo.cs.ls14.jf.ws.bpmn.pst;

import javax.jws.WebService;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.analysis.pst.PSTBuilder;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessStructureTree;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.ws.bpmn.pst.ProcessStructureTreeSEI")
public class ProcessStructureTreeImpl implements ProcessStructureTreeSEI {

	@Override
	public ProcessStructureTree getPst(Definitions definitions)
			throws Exception {
		System.out.println(definitions);
		PSTBuilder pst = new PSTBuilder();
		return pst.getTree(definitions);
	}

}
