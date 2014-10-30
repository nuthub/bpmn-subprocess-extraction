package edu.udo.cs.ls14.jf.app.analyzer.test;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String toString() {
		return "Person [name=" + name + "]";
	}
}
