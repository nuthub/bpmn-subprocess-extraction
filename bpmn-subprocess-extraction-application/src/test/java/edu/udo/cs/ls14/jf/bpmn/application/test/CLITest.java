package edu.udo.cs.ls14.jf.bpmn.application.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.CLI;

public class CLITest {
	
	private static final String TEST_DIR = "/tmp/test-output";

	@Test
	public void testConstructor() {
		assertNotNull(new CLI());
	}
	
	@Test
	public void testHelp() {
		String[] args = { "help" };
		CLI.main(args);
	}

	@Test
	public void testAlslpp() throws IOException {
		String[] args = new String[6];
		args[0] = "-m1";
		args[1] = getClass().getResource("/bpmn/complete/complete1.bpmn")
				.getFile();
		args[2] = "-m2";
		args[3] = getClass().getResource("/bpmn/complete/complete2.bpmn")
				.getFile();
		args[4] = "-t";
		args[5] = TEST_DIR;
		CLI.main(args);
		FileUtils.deleteDirectory(new File(TEST_DIR));
	}

	@Test
	public void testException() {
		String[] args = new String[6];
		args[0] = "-m1";
		args[1] = "not-existent";
		args[2] = "-m2";
		args[3] = "not-existent";
		args[4] = "-t";
		args[5] = "/dev/null";
		CLI.main(args);
	}
	
}
