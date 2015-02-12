package edu.udo.cs.ls14.jf.bpmnanalysis.behaviorprofile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Generic matrix implementation.
 * 
 * @author Julian Flake
 *
 * @param <K>
 *            Key type
 * @param <V>
 *            Value type
 */
public class Matrix<K, V> {

	private Map<K, Map<K, V>> matrix;
	private Set<K> keys;
	private V defaultValue;

	/**
	 * Create empty matrix and set default value.
	 * 
	 * @param defaultValue
	 *            the default if no other value is set
	 */
	public Matrix(V defaultValue) {
		super();
		this.matrix = new HashMap<K, Map<K, V>>();
		this.keys = new HashSet<K>();
		this.defaultValue = defaultValue;
	}

	/**
	 * Add a value.
	 * 
	 * @param a
	 *            row
	 * @param b
	 *            column
	 * @param value
	 *            value
	 */
	public void put(K a, K b, V value) {
		if (!matrix.containsKey(a)) {
			matrix.put(a, new HashMap<K, V>());
			keys.add(a);
		}
		matrix.get(a).put(b, value);
		keys.add(b);
	}

	/**
	 * Get a value.
	 * 
	 * @param a
	 *            row
	 * @param b
	 *            column
	 * @return value
	 */
	public V get(K a, K b) {
		if (!keys.contains(a) || !keys.contains(b)) {
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

	/**
	 * Get all Keys.
	 * 
	 * @return set of keys
	 */
	public Set<K> getKeys() {
		return keys;
	}

	/**
	 * {@inheritDoc}
	 */
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
