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

import edu.udo.cs.ls14.jf.transformation.HenshinTransformation;
import edu.udo.cs.ls14.jf.utils.bpmn.ProcessLoader;

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
		Resource res = ProcessLoader.getBpmnResource(getClass().getResource(
				"../../bpmn/" + basename + ".bpmn"));
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