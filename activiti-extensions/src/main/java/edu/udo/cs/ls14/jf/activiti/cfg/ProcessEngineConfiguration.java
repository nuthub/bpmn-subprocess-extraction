package edu.udo.cs.ls14.jf.activiti.cfg;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.variable.VariableType;

import edu.udo.cs.ls14.jf.activiti.variables.EObjectType;

public class ProcessEngineConfiguration extends ProcessEngineConfigurationImpl {

	@Override
	protected CommandInterceptor createTransactionInterceptor() {
		return null;
	}
	
	@Override
	public ProcessEngine buildProcessEngine() {
		List<VariableType> customPostVariableTypes = new ArrayList<VariableType>();
		customPostVariableTypes.add(new EObjectType());
		setCustomPostVariableTypes(customPostVariableTypes);
		return super.buildProcessEngine();
	}
	

}
