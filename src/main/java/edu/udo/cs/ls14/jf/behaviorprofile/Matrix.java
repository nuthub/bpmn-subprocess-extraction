package edu.udo.cs.ls14.jf.behaviorprofile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Matrix<K, V> {

	private Map<K, Map<K, V>> matrix;
	private Set<K> keys;
	private V defaultValue;

	public Matrix(V defaultValue) {
		super();
		this.matrix = new HashMap<K, Map<K, V>>();
		this.keys = new TreeSet<K>();
		this.defaultValue = defaultValue;
	}

	public void put(K a, K b, V value) {
		if (!matrix.containsKey(a)) {
			matrix.put(a, new HashMap<K, V>());
			keys.add(a);
		}
		matrix.get(a).put(b, value);
		keys.add(b);
	}

	public V get(K a, K b) {
		if(!keys.contains(a) || !keys.contains(b)) {
			return null;
		}
		if (!matrix.containsKey(a)) {
			return defaultValue;
		}
		if (!matrix.get(a).containsKey(b)) {
			return defaultValue;
		}
		return matrix.get(a).get(b);
	}

	public Set<K> getKeys() {
		return keys;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Matrix (" + keys + ") [");
		sb.append(System.getProperty("line.separator"));
		for (Map.Entry<K, Map<K, V>> row : matrix.entrySet()) {
			sb.append("  " + row);
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("]");
		return sb.toString();
	}

}
