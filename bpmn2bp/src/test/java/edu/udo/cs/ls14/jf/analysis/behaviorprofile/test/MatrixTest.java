package edu.udo.cs.ls14.jf.analysis.behaviorprofile.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.udo.cs.ls14.jf.analysis.behaviorprofile.Matrix;

public class MatrixTest {

	@Test
	public void test() {
		Matrix<String, Boolean> m = new Matrix<String, Boolean>(false);
		assertNull(m.get("a","a"));
		m.put("a", "a", true);
		assertNull(m.get("a","b"));
		assertEquals(m.get("a","a"), true);
		assertEquals(m.get("a", "b"), null);
	}

}
