package edu.udo.cs.ls14.jf.application.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;

public class ResourceSetTest {

	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
	}

	@Test
	public void testResourceSet() throws IOException {
		resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/utils/test").getPath());

		String filename1 = "PM1-mit-Fragment1.bpmn";
		Definitions def1 = resSet.loadDefinitions(filename1);

		String filename2 = "PM2-mit-Fragment1.bpmn";
		Definitions def2 = resSet.loadDefinitions(filename2);

		System.out.println(resSet.toContentString());

		assertEquals(2, resSet.getResources().size());
		assertEquals(def1, resSet.getResource(filename1).getContents().get(0)
				.eContents().get(0));
		assertEquals(def2, resSet.getResource(filename2).getContents().get(0)
				.eContents().get(0));

		assertNull(resSet.getResource("not-existent.bpmn"));
	}
}
