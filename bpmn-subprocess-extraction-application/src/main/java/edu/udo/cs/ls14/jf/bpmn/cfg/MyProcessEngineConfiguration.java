package edu.udo.cs.ls14.jf.bpmn.cfg;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.variable.VariableType;

import edu.udo.cs.ls14.jf.bpmn.variables.DefinitionsType;
import edu.udo.cs.ls14.jf.bpmn.variables.ProcessAnalysisType;
import edu.udo.cs.ls14.jf.bpmn.variables.ProcessMatchingType;
import edu.udo.cs.ls14.jf.bpmn.variables.ProcessExtractionType;

public class MyProcessEngineConfiguration extends
		StandaloneProcessEngineConfiguration {

	@Override
	public ProcessEngine buildProcessEngine() {
		List<VariableType> customPostVariableTypes = getCustomPostVariableTypes();
		if (customPostVariableTypes == null) {
			customPostVariableTypes = new ArrayList<VariableType>();
		}
		customPostVariableTypes.add(new DefinitionsType());
		customPostVariableTypes.add(new ProcessAnalysisType());
		customPostVariableTypes.add(new ProcessMatchingType());
		customPostVariableTypes.add(new ProcessExtractionType());
		setCustomPostVariableTypes(customPostVariableTypes);
		return super.buildProcessEngine();
	}

}