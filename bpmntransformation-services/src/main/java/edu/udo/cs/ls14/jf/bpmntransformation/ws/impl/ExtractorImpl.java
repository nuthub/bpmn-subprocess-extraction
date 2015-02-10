package edu.udo.cs.ls14.jf.bpmntransformation.ws.impl;

import javax.jws.WebService;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.ws.ExtractorSEI;
import edu.udo.cs.ls14.jf.transformation.Extractor;

@WebService(endpointInterface = "edu.udo.cs.ls14.jf.bpmntransformation.ws.ExtractorSEI")
public class ExtractorImpl implements ExtractorSEI {

	@Override
	public ProcessTransformation extract(ProcessTransformation transformation) {
		System.out.println("hier");
		try {
			System.out.println("T: " + transformation);
			transformation = Extractor.extract(transformation);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return transformation;
	}

}
