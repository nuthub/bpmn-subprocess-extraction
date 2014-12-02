package edu.udo.cs.ls14.jf.bpmn.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNDiagram;

public class DefinitionsUtil {

	public static BPMNDiagram getDiagram(Definitions definitions)
			throws Exception {
		List<BPMNDiagram> diagrams = definitions.getDiagrams();
		if (diagrams.size() < 1) {
			throw new Exception("Definitions object contains no Diagram");
		} else if (diagrams.size() > 1) {
			throw new Exception(
					"Definitions object contains more than one Diagram");
		}
		return diagrams.get(0);
	}

	public static Process getProcess(Definitions definitions)
			throws Exception {
		List<Process> processes = definitions.getRootElements().stream()
				.filter(r -> r instanceof Process).map(p -> (Process) p)
				.collect(Collectors.toList());
		if (processes.size() < 1) {
			throw new Exception("Definitions object contains no Process");
		} else if (processes.size() > 1) {
			throw new Exception(
					"Definitions object contains more than one Process");
		}
		return processes.get(0);
	}

}
