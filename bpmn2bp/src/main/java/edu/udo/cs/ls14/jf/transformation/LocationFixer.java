package edu.udo.cs.ls14.jf.transformation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;

public class LocationFixer extends HenshinTransformation {

	protected static final String RULEPATH = "src/main/resources/edu/udo/cs/ls14/jf/henshin/";
	private static final String RULEFILE = "locationFix";

	@Override
	protected String getRulePath() {
		return RULEPATH;
	}

	/**
	 * This substitutes a location of an import. BPMN2 EMF model (or Henshin?)
	 * produces relative location Strings, where absolute and prefixed are
	 * required. Unproved.
	 * 
	 * @param resource
	 * @param oldLocation
	 * @param newLocation
	 * @throws Exception
	 */
	public void fixLocations(Resource resource, String oldLocation,
			String newLocation) throws Exception {
		EGraph graph = new EGraphImpl(resource);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("oldLocation", oldLocation);
		parameters.put("newLocation", newLocation);
		applyRule(graph, RULEFILE, "fixImports", parameters);
	}

}
