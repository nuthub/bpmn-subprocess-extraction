package edu.udo.cs.ls14.jf.transformation.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.transformation.HenshinTransformation;

/**
 * checks, if conditional and default sequence flow can be removed
 * 
 * @param graph
 * @param id
 * @throws Exception
 */
public class DeleteSequenceFlowTest extends HenshinTransformation {

	private static final String RULEPATH = "src/main/resources/edu/udo/cs/ls14/jf/henshin/";
	private static final String RULEFILE = "bpmnModifier";

	@Override
	protected String getRulePath() {
		return RULEPATH;
	}

	private void deleteSequenceFlows(String basename, List<String> ids)
			throws Exception {
		Bpmn2ResourceSet resSet = new Bpmn2ResourceSet(getClass().getResource(
				"/edu/udo/cs/ls14/jf/bpmn/test/").getPath());
		Resource res = resSet.loadResource(basename + ".bpmn");
		EGraph graph = new EGraphImpl(res);

		Map<String, Object> parameters = new HashMap<String, Object>();
		for (String id : ids) {
			parameters.put("id", id);
			applyRule(graph, RULEFILE, "deleteSequenceFlow", parameters);
		}
		new File("/tmp/transformed/").mkdirs();
		res.save(new FileOutputStream("/tmp/transformed/" + basename
				+ "_transformed.bpmn"), null);
	}

	@Test
	public void testDeleteDefaultFlowExample1() throws Exception {
		String basename = "xor-example";
		deleteSequenceFlows(basename,
				Arrays.asList("SequenceFlow_4", "SequenceFlow_5"));
	}

}
