package edu.udo.cs.ls14.jf.bpmnmatching.nodematching;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowNode;

import edu.udo.cs.ls14.jf.nlp.Lemmatizer;
import edu.udo.cs.ls14.jf.nlp.OpenThesaurusSQLite;
import edu.udo.cs.ls14.jf.nlp.StopWordList;

/**
 * Compares two nodes, if they are of the same type and if their labels are
 * equivalent.
 * 
 * @author Julian Flake
 *
 */
public class NodeComparator {

	private static final String LANGUAGE = "de";
	private List<Class<? extends FlowNode>> classes;
	private static final String SPECIAL_CHARACTERS_PATTERN = "[^a-zA-Z 0-9äÄöÖüÜß]";

	/**
	 * public Constructor.
	 */
	public NodeComparator() {
		super();
		classes = new ArrayList<Class<? extends FlowNode>>();
		classes.add(Activity.class);
		classes.add(Event.class);
	}

	/**
	 * Returns true if type of e1 equals type of e2 and if label e1 is
	 * equivalent with label of e2. Only Activites and Events are considered
	 * equivalent in this implementation.
	 * 
	 * @param n1
	 *            first flow node
	 * @param n2
	 *            second flow node
	 * @return true if nodes are equivalent
	 */
	public boolean isEquivalent(FlowNode n1, FlowNode n2) {
		// is e1 of relevant type?
		// TODO: Not comparators job
		if (!isClassOfInterest(n1.getClass())) {
			return false;
		}
		// is e2 of relevant type?
		// TODO: Not comparators job
		if (!isClassOfInterest(n2.getClass())) {
			return false;
		}
		// are e1 and e2 type equivalent?
		if (!isTypeEquivalent(n1, n2)) {
			return false;
		}
		// are e1 and e2 label equivalent?
		if (!isLabelEquivalent(n1.getName(), n2.getName())) {
			return false;
		}
		return true;
	}

	private boolean isClassOfInterest(Class<? extends FlowNode> cls) {
		for (Class<? extends FlowNode> cls2 : classes) {
			if (cls2.isAssignableFrom(cls)) {
				return true;
			}
		}
		return false;
	}

	private boolean isTypeEquivalent(FlowNode e1, FlowNode e2) {
		return e1.getClass().equals(e2.getClass());
	}

	private boolean isLabelEquivalent(String str1, String str2) {
		// remove special chars and split strings
		if (str1 == null) {
			str1 = "";
		}
		if (str2 == null) {
			str2 = "";
		}
		List<String> list1 = Arrays.asList(StringUtils.split(str1.replaceAll(
				SPECIAL_CHARACTERS_PATTERN, " ")));
		List<String> list2 = Arrays.asList(StringUtils.split(str2.replaceAll(
				SPECIAL_CHARACTERS_PATTERN, " ")));

		// remove stop words
		// build base forms
		list1 = removeStopWordsAndBuildBaseForms(list1);
		list2 = removeStopWordsAndBuildBaseForms(list2);

		// structure to store matches
		Map<String, String> map = new HashMap<String, String>();
		for (String word : list1) {
			map.put(word, null);
		}

		// build base form
		// check every word for synonymity
		for (String w1 : list1) {
			String w1base = Lemmatizer.lemmatize(LANGUAGE, w1);
			for (String w2 : list2) {
				String w2base = Lemmatizer.lemmatize(LANGUAGE, w2);
				try {
					if (OpenThesaurusSQLite.areSynonyms(w1base, w2base)) {
						map.put(w1, w2);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("Exception: ", e);
				}
			}
		}
		return (map.keySet().containsAll(list1)
				&& map.values().containsAll(list2) && list1.size() == list2
				.size());
	}

	private List<String> removeStopWordsAndBuildBaseForms(List<String> list) {
		List<String> l = new ArrayList<String>();
		for (String word : list) {
			if (!StopWordList.contains(LANGUAGE, word)) {
				l.add(Lemmatizer.lemmatize(LANGUAGE, word));
			}
		}

		return l;
	}
}
