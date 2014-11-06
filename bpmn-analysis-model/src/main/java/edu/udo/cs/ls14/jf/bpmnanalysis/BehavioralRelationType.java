/**
 */
package edu.udo.cs.ls14.jf.bpmnanalysis;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Behavioral Relation Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see edu.udo.cs.ls14.jf.bpmnanalysis.BpmnAnalysisPackage#getBehavioralRelationType()
 * @model
 * @generated
 */
public enum BehavioralRelationType implements Enumerator {
	/**
	 * The '<em><b>NO SUCCESSION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_SUCCESSION_VALUE
	 * @generated
	 * @ordered
	 */
	NO_SUCCESSION(1, "NO_SUCCESSION", "NO_SUCCESSION"),

	/**
	 * The '<em><b>DIRECT SUCCESSOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECT_SUCCESSOR_VALUE
	 * @generated
	 * @ordered
	 */
	DIRECT_SUCCESSOR(2, "DIRECT_SUCCESSOR", "DIRECT_SUCCESSOR"),

	/**
	 * The '<em><b>DIRECT PREDECESSOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIRECT_PREDECESSOR_VALUE
	 * @generated
	 * @ordered
	 */
	DIRECT_PREDECESSOR(3, "DIRECT_PREDECESSOR", "DIRECT_PREDECESSOR"),

	/**
	 * The '<em><b>PARALLEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARALLEL_VALUE
	 * @generated
	 * @ordered
	 */
	PARALLEL(4, "PARALLEL", "PARALLEL");

	/**
	 * The '<em><b>NO SUCCESSION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO SUCCESSION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_SUCCESSION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NO_SUCCESSION_VALUE = 1;

	/**
	 * The '<em><b>DIRECT SUCCESSOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIRECT SUCCESSOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIRECT_SUCCESSOR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIRECT_SUCCESSOR_VALUE = 2;

	/**
	 * The '<em><b>DIRECT PREDECESSOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIRECT PREDECESSOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIRECT_PREDECESSOR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DIRECT_PREDECESSOR_VALUE = 3;

	/**
	 * The '<em><b>PARALLEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PARALLEL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARALLEL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARALLEL_VALUE = 4;

	/**
	 * An array of all the '<em><b>Behavioral Relation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BehavioralRelationType[] VALUES_ARRAY =
		new BehavioralRelationType[] {
			NO_SUCCESSION,
			DIRECT_SUCCESSOR,
			DIRECT_PREDECESSOR,
			PARALLEL,
		};

	/**
	 * A public read-only list of all the '<em><b>Behavioral Relation Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<BehavioralRelationType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Behavioral Relation Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BehavioralRelationType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BehavioralRelationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Behavioral Relation Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BehavioralRelationType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BehavioralRelationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Behavioral Relation Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BehavioralRelationType get(int value) {
		switch (value) {
			case NO_SUCCESSION_VALUE: return NO_SUCCESSION;
			case DIRECT_SUCCESSOR_VALUE: return DIRECT_SUCCESSOR;
			case DIRECT_PREDECESSOR_VALUE: return DIRECT_PREDECESSOR;
			case PARALLEL_VALUE: return PARALLEL;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private BehavioralRelationType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //BehavioralRelationType
