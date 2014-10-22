package edu.udo.cs.ls14.jf.transformation;

import java.util.Map;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

public abstract class HenshinTransformation {

	private Engine engine = null;
	private HenshinResourceSet resourceSet = null;

	protected abstract String getRulePath();

	private void init() {
		engine = new EngineImpl();
		resourceSet = new HenshinResourceSet(getRulePath());
		resourceSet.registerXMIResourceFactories("bpmn2");
		resourceSet.getPackageRegistry().put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("bpmn2", new Bpmn2ResourceFactoryImpl());
	}

	protected void applyRule(EGraph graph, String rulefileBaseName,
			String ruleName, Map<String, Object> parameters) throws Exception {
		if (engine == null || resourceSet == null) {
			init();
		}
		// Load rule
		Module module = resourceSet.getModule(rulefileBaseName + ".henshin");
		Unit unit = module.getUnit(ruleName);
		if (unit == null) {
			throw new Exception("Could not get Unit: " + rulefileBaseName
					+ " / " + ruleName);
		}
		UnitApplication app = new UnitApplicationImpl(engine, graph, unit, null);
		for (Map.Entry<String, Object> p : parameters.entrySet()) {
			app.setParameterValue(p.getKey(), p.getValue());
		}
		if (!app.execute(new HenshinApplicationMonitor())) {
			throw new Exception("Could not apply rule: " + rulefileBaseName
					+ "->" + ruleName + " with " + parameters);
		}
	}
}
