package edu.udo.cs.ls14.jf.bpmn.app;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.variable.VariableType;

import edu.udo.cs.ls14.jf.bpmn.app.variables.DefinitionsType;
import edu.udo.cs.ls14.jf.bpmn.app.variables.ProcessAnalysisType;
import edu.udo.cs.ls14.jf.bpmn.app.variables.ProcessMatchingType;
import edu.udo.cs.ls14.jf.bpmn.app.variables.ProcessTransformationType;

/**
 * {@inheritDoc}
 * 
 * This process engine configuration adds custom variable types to the post
 * variable type list.
 * 
 * @author Julian Flake
 * 
 */
public class MyProcessEngineConfiguration extends
		StandaloneProcessEngineConfiguration {

	/**
	 * {@inheritDoc}
	 * 
	 * Creates a new process engine with this configuration.
	 */
	@Override
	public ProcessEngine buildProcessEngine() {
		List<VariableType> customPostVariableTypes = getCustomPostVariableTypes();
		if (customPostVariableTypes == null) {
			customPostVariableTypes = new ArrayList<VariableType>();
		}
		customPostVariableTypes.add(new DefinitionsType());
		customPostVariableTypes.add(new ProcessAnalysisType());
		customPostVariableTypes.add(new ProcessMatchingType());
		customPostVariableTypes.add(new ProcessTransformationType());
		setCustomPostVariableTypes(customPostVariableTypes);
		return super.buildProcessEngine();
	}

}