package edu.udo.cs.ls14.jf.bpmn.app.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.app.CLI;

public class CLITest {

	private static final String TEST_DIR = System.getProperty("java.io.tmpdir")
			+ "/test-cli";

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
	public void testApp() throws IOException {
		String[] args = new String[6];
		args[0] = "-m1";
		args[1] = getClass().getResource("/bpmn/parallelGateway/parallelism1.bpmn")
				.getFile();
		args[2] = "-m2";
		args[3] = getClass().getResource("/bpmn/parallelGateway/parallelism2.bpmn")
				.getFile();
		args[4] = "-t";
		args[5] = TEST_DIR;
		CLI.main(args);
		FileUtils.deleteDirectory(new File(TEST_DIR));
	}

	@Test
	public void testDebug() throws IOException {
		String[] args = new String[7];
		args[0] = "-m1";
		args[1] = getClass().getResource("/bpmn/parallelGateway/parallelism1.bpmn")
				.getFile();
		args[2] = "-m2";
		args[3] = getClass().getResource("/bpmn/parallelGateway/parallelism2.bpmn")
				.getFile();
		args[4] = "-t";
		args[5] = TEST_DIR;
		args[6] = "-d";
		CLI.main(args);
		FileUtils.deleteDirectory(new File(TEST_DIR));
	}

	@Test
	public void testException() {
		System.out.println(TEST_DIR);
		String[] args = new String[6];
		args[0] = "-m1";
		args[1] = "not-existent";
		args[2] = "-m2";
		args[3] = "not-existent";
		args[4] = "-t";
		args[5] = TEST_DIR;
		CLI.main(args);
	}

}
