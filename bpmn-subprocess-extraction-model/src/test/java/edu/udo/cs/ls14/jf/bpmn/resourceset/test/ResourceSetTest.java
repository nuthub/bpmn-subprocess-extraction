package edu.udo.cs.ls14.jf.bpmn.resourceset.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;

public class ResourceSetTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testResourceSet() throws Exception {
		Bpmn2ResourceSet resSet = Bpmn2ResourceSet.getInstance();
		int resourcesBefore = resSet.getResources().size();

		String pathname = "/bpmn/parallelGateway/";
		String filename1 = "parallelism1.bpmn";
		Definitions def1 = resSet.loadDefinitions(getClass().getResource(
				pathname + filename1).getPath());
		assertNotNull(def1);

		String filename2 = "parallelism2.bpmn";
		Definitions def2 = resSet.loadDefinitions(getClass().getResource(
				pathname + filename2).getPath());
		assertNotNull(def2);
		System.out.println(toContentString(resSet));

		assertEquals(resourcesBefore + 2, resSet.getResources().size());

		resSet.createResource(URI.createURI("foobar"),
				Bpmn2Factory.eINSTANCE.createDefinitions());
		assertEquals(resourcesBefore + 3, resSet.getResources().size());

	}

	public String toContentString(Bpmn2ResourceSet resSet) {
		StringBuffer sb = new StringBuffer();
		for (Resource res : resSet.getResources()) {
			sb.append(res);
			sb.append(System.getProperty("line.separator"));
			for (EObject eObj : res.getContents()) {
				sb.append("  - " + eObj);
				sb.append(System.getProperty("line.separator"));
				for (EObject eObj2 : eObj.eContents()) {
					sb.append("    - " + eObj2);
					sb.append(System.getProperty("line.separator"));
				}
			}
			sb.append("---");
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

}
