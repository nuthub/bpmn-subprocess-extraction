package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.ModifierSEI;
import edu.udo.cs.ls14.jf.transformation.Modifier;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.ModifierSEI")
public class ModifierImpl implements ModifierSEI {

	@Override
	public ProcessTransformation modify(ProcessTransformation transformation) {
		try {
			transformation = Modifier.modify(transformation);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return transformation;
	}

}
