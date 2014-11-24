package edu.udo.cs.ls14.jf.bpmn.variables;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.camunda.bpm.engine.impl.variable.VariableType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import edu.udo.cs.ls14.jf.bpmn.utils.EObjectXmlConverter;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;

public class ProcessAnalysisType implements VariableType {

	@Override
	public String getTypeName() {
		return "processanalysis";
	}

	@Override
	public String getTypeNameForValue(Object value) {
		return ProcessAnalysis.class.getName();
	}

	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		return ProcessAnalysis.class.isAssignableFrom(value.getClass());
	}

	@Override
	public boolean isCachable() {
		return true;
	}

	@Override
	public Object getValue(ValueFields valueFields) {
		registerFactoriesAndPackages();
		try {
			return EObjectXmlConverter.xml2EObject("bpmnanalysis", new String(
					valueFields.getByteArrayValue().getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setValue(Object value, ValueFields valueFields) {
		registerFactoriesAndPackages();
		try {
			valueFields.setByteArrayValue(EObjectXmlConverter.eObject2Xml(
					"bpmnanalysis", (EObject) value).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerFactoriesAndPackages() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmnanalysis",
						new BpmnAnalysisResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(BpmnAnalysisPackage.eNS_URI,
				BpmnAnalysisPackage.eINSTANCE);

	}

}
