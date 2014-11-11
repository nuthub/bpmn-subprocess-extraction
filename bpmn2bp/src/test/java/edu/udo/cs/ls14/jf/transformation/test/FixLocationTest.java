package edu.udo.cs.ls14.jf.transformation.test;

import static org.junit.Assert.assertTrue;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.LoggingApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;
import edu.udo.cs.ls14.jf.transformation.LocationFixer;
import edu.udo.cs.ls14.jf.utils.bpmn.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.utils.bpmn.ResourceCopier;

public class FixLocationTest {

	private static final String RULEFILE = "callActivityTest";
	private static final String RULEPATH = "src/main/resources/edu/udo/cs/ls14/jf/henshin/";

	@Test
	public void testCallActivityCalledElementRef() throws Exception {
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(
				"src/test/resources/edu/udo/cs/ls14/jf/bpmn");
		Resource res1 = ResourceCopier.copy(
				resSet.loadResource("sequence.bpmn"),
				"/tmp/transformed/myCalledProcess.bpmn");
		Resource res2 = ResourceCopier.copy(resSet.loadResource("empty.bpmn"),
				"/tmp/transformed/myNewProcess.bpmn");
		// create two resources from existing resources

		// get process from first resource
		Process calledElement = ProcessLoader
				.getProcessFromDefinitions((Definitions) res1.getContents()
						.get(0));

		// transform second resource
		EGraph graph = new EGraphImpl(res2);
		Engine engine = new EngineImpl();
		// Load rule to create a call activity
		HenshinResourceSet resourceSet = new HenshinResourceSet(RULEPATH);
		Module module = resourceSet.getModule(RULEFILE + ".henshin");
		Unit unit = module.getUnit("test");
		if (unit == null) {
			throw new Exception("Could not get Unit: " + RULEFILE + " / test");
		}
		UnitApplication app = new UnitApplicationImpl(engine, graph, unit, null);

		app.setParameterValue("calledElement", calledElement);
		assertTrue(app.execute(new LoggingApplicationMonitor()));

		// seems to be important?
		res2.save(null);
		// now fix the location
		LocationFixer fixer = new LocationFixer();
		fixer.fixLocations(res2, "myCalledProcess.bpmn",
				"file:/tmp/transformed/myCalledProcess.bpmn");
		res2.save(null);
	}
}
