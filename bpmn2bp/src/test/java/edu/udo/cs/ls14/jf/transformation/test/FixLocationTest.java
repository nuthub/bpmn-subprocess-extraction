package edu.udo.cs.ls14.jf.transformation.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

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

import edu.udo.cs.ls14.jf.transformation.LocationFixer;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;
import edu.udo.cs.ls14.jf.utils.bpmn.ResourceCopier;

public class FixLocationTest {

	private static final String RULEFILE = "callActivityTest";
	private static final String RULEPATH = "src/main/resources/edu/udo/cs/ls14/jf/henshin/";

	@Test
	public void testCallActivityCalledElementRef() throws Exception {
		Resource srcResource1 = ProcessLoader.getBpmnResource(new File(
				getClass().getResource("../../bpmn/sequence.bpmn").getFile()));
		String targetFilename1 = "/tmp/transformed/myCalledProcess.bpmn";
		Resource res1 = ResourceCopier.copy(srcResource1, targetFilename1);
		Process calledElement = ProcessLoader.getProcessFromResource(res1);

		Resource srcResource2 = ProcessLoader.getBpmnResource(getClass()
				.getResource("../../bpmn/empty.bpmn"));
		String targetFilename2 = "/tmp/transformed/myNewProcess.bpmn";
		Resource res2 = ResourceCopier.copy(srcResource2, targetFilename2);

		EGraph graph = new EGraphImpl(res2);
		Engine engine = new EngineImpl();
		// Load rule
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
		System.out.println(res2.getURI());
		System.out.println(calledElement.eResource().getURI());
		res2.save(null);
		
		LocationFixer fixer = new LocationFixer();
		fixer.fixLocations(res2, "myCalledProcess.bpmn", "file:/tmp/transformed/myCalledProcess.bpmn");
		res2.save(null);
	}

}
