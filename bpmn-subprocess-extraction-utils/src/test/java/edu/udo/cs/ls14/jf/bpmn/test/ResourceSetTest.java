package edu.udo.cs.ls14.jf.bpmn.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.registry.Registries;

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

		String filename2 = "parallelism2.bpmn";
		Definitions def2 = resSet.loadDefinitions(getClass().getResource(
				pathname + filename2).getPath());

		System.out.println(toContentString(resSet));

		assertEquals(resourcesBefore + 2, resSet.getResources().size());
		assertEquals(
				def1,
				resSet.getResource(
						getClass().getResource(pathname + filename1).getPath())
						.getContents().get(0).eContents().get(0));
		assertEquals(
				def2,
				resSet.getResource(
						getClass().getResource(pathname + filename2).getPath())
						.getContents().get(0).eContents().get(0));

		assertNull(resSet.getResource("not-existent.bpmn"));
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
