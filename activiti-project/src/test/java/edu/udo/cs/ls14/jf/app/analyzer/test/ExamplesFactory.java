package edu.udo.cs.ls14.jf.app.analyzer.test;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Task;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;

import edu.udo.cs.ls14.jf.bpmn.utils.ProcessLoader;

public class ExamplesFactory {

	public static DocumentRoot createDocRoot(Resource res) {
		if (res.getContents().size() != 1) {
			Assert.fail("more than one EObject in resource " + res);
		}
		System.out.println(res.getContents().get(0));
		if (!(res.getContents().get(0) instanceof DocumentRoot)) {
			Assert.fail("resource does not contain definitions.");
		}
		return (DocumentRoot) res.getContents().get(0);
	}

	public static Definitions createDefintions(Resource res) {
		DocumentRoot root = createDocRoot(res);
		return root.getDefinitions();
	}

	public static Resource createResource(
			Class<? extends ProcessAnalyzer> class1) throws Exception {
		return ProcessLoader.getBpmnResource(class1
				.getResource("/sequence2.bpmn"));
	}

	public static Person createPerson() {
		return new Person("Max Mustermann");
	}

	public static Task createTask(String name) {
		Task t = Bpmn2Factory.eINSTANCE.createTask();
		t.setName(name);
		return t;

	}

}
