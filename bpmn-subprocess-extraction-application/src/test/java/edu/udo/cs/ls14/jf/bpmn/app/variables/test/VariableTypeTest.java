package edu.udo.cs.ls14.jf.bpmn.app.variables.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.camunda.bpm.engine.impl.variable.ValueFields;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.variables.DefinitionsType;
import edu.udo.cs.ls14.jf.bpmn.app.variables.ProcessAnalysisType;
import edu.udo.cs.ls14.jf.bpmn.app.variables.ProcessMatchingType;
import edu.udo.cs.ls14.jf.bpmn.app.variables.ProcessTransformationType;
import edu.udo.cs.ls14.jf.bpmn.util.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmn.util.Registries;
import edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisFactory;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmnanalysis.util.BpmnAnalysisResourceFactoryImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.BpmnMatchingFactory;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmntransformation.BpmnTransformationFactory;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.BpmnTransformationResourceFactoryImpl;

public class VariableTypeTest {

	@Before
	public void setUp() {
		Registries.registerAll();
	}

	@Test
	public void testDefinitionsType() throws Exception {
		DefinitionsType t = new DefinitionsType();
		Definitions defs = getDefinitions();
		ValueFields fields = new ValueFieldsImpl();
		t.setValue(defs, fields);

		assertEquals("org.eclipse.bpmn2.Definitions",
				t.getTypeNameForValue("foo"));
		assertEquals("bpmn", t.getTypeName());
		assertTrue(t.isAbleToStore(null));
		assertTrue(t.isCachable());
		assertTrue(t.isAbleToStore(defs));
		assertNotNull(t.getValue(fields));
		assertEquals("myId", ((Definitions) t.getValue(fields)).getId());
	}

	@Test
	public void testProcessAnalysisType() {
		ProcessAnalysisType t = new ProcessAnalysisType();
		ProcessAnalysis object = getAnalysis();
		ValueFields fields = new ValueFieldsImpl();
		t.setValue(object, fields);

		assertEquals("bpmnanalysis", t.getTypeName());
		assertEquals("edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis",
				t.getTypeNameForValue("foo"));
		assertTrue(t.isAbleToStore(null));
		assertTrue(t.isAbleToStore(object));
		assertTrue(t.isCachable());
		assertNotNull(t.getValue(fields));
		assertEquals("myId", ((ProcessAnalysis) t.getValue(fields))
				.getDefinitions().getId());
	}

	@Test
	public void testProcessMatchingType() {
		ProcessMatchingType t = new ProcessMatchingType();
		ProcessMatching object = getProcessMatching();
		ValueFields fields = new ValueFieldsImpl();
		t.setValue(object, fields);

		assertEquals("edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching",
				t.getTypeNameForValue("foo"));
		assertEquals("bpmnmatching", t.getTypeName());
		assertTrue(t.isAbleToStore(null));
		assertTrue(t.isAbleToStore(object));
		assertTrue(t.isCachable());
		assertNotNull(t.getValue(fields));
		assertEquals("myId", ((ProcessMatching) t.getValue(fields))
				.getAnalysis1().getDefinitions().getId());
	}

	@Test
	public void testProcessTransformationType() {
		ProcessTransformationType t = new ProcessTransformationType();
		ValueFields fields = new ValueFieldsImpl();
		ProcessTransformation object = getProcessTransformation();
		assertEquals(
				"edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation",
				t.getTypeNameForValue("foo"));
		assertEquals("bpmntransformation", t.getTypeName());
		assertTrue(t.isCachable());
		assertTrue(t.isAbleToStore(null));
		assertTrue(t.isAbleToStore(object));
		t.setValue(object, fields);
		assertNotNull(t.getValue(fields));
		assertEquals("myId", ((ProcessTransformation) t.getValue(fields))
				.getProcessMatching().getAnalysis1().getDefinitions().getId());
	}

	private Definitions getDefinitions() {
		Resource res = Bpmn2ResourceSet.getInstance().createResource(
				URI.createURI("foo.bpmn"));
		Definitions defs = Bpmn2Factory.eINSTANCE.createDefinitions();
		defs.setId("myId");
		res.getContents().add(defs);
		return defs;
	}

	private ProcessAnalysis getAnalysis() {
		Resource res = new BpmnAnalysisResourceFactoryImpl().createResource(URI
				.createURI("foo.bpmnanalysis"));
		ProcessAnalysis object = BpmnAnalysisFactory.eINSTANCE
				.createProcessAnalysis();
		object.setDefinitions(getDefinitions());
		res.getContents().add(object);
		return object;
	}

	private ProcessMatching getProcessMatching() {
		Resource res = new BpmnAnalysisResourceFactoryImpl().createResource(URI
				.createURI("foo.bpmnmatching"));
		ProcessMatching object = BpmnMatchingFactory.eINSTANCE
				.createProcessMatching();
		object.setAnalysis1(getAnalysis());
		res.getContents().add(object);
		return object;
	}

	private ProcessTransformation getProcessTransformation() {
		Resource res = new BpmnTransformationResourceFactoryImpl()
				.createResource(URI.createURI("foo.bpmntransformation"));
		ProcessTransformation object = BpmnTransformationFactory.eINSTANCE
				.createProcessTransformation();
		object.setProcessMatching(getProcessMatching());
		res.getContents().add(object);
		return object;
	}
}
