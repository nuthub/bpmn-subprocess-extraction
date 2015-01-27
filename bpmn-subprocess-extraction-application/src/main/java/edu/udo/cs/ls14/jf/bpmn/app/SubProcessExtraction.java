package edu.udo.cs.ls14.jf.bpmn.app;

import org.eclipse.bpmn2.Definitions;

import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;

public interface SubProcessExtraction {

	public abstract void init();

	public abstract ProcessTransformation run(Definitions definitions1,
			Definitions definitions2) throws Exception;

}