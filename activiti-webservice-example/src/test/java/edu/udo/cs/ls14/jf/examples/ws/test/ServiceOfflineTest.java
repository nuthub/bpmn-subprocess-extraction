package edu.udo.cs.ls14.jf.examples.ws.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.udo.cs.ls14.jf.services.StringSize;

public class ServiceOfflineTest {

	@Test
	public void StringSizeServiceTest() {
		StringSize service = new StringSize();
		String string = "This is a dummy test string";
		int size = service.getSize(string);
		assertEquals(string.length(), size);
	}
}
