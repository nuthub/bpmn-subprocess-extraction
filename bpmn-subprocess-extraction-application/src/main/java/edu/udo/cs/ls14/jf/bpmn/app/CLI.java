package edu.udo.cs.ls14.jf.bpmn.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.bpmn2.Definitions;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import edu.udo.cs.ls14.jf.bpmn.resourceset.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmntransformation.ProcessTransformation;
import edu.udo.cs.ls14.jf.bpmntransformation.util.ProcessTransformationUtil;

/**
 * This class implements a command line interface to the application.
 * 
 * @author Julian Flake
 *
 */
public class CLI {

	private static final String APPNAME = "BPMN subprocess extraction";
	private static final String HELP = "help";
	private static final String HELP_DESC = "print this usage help";
	private static final String MODEL1 = "m1";
	private static final String MODEL1_DESC = "first model (required)";
	private static final String MODEL2 = "m2";
	private static final String MODEL2_DESC = "second model (required)";
	private static final String TARGET_DIR = "t";
	private static final String TARGET_DIR_DESC = "target directory (required)";

	/**
	 * Does the definition and parsing of arguments, creates and calls the
	 * application.
	 * 
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// define options
		Options options = new Options();
		options.addOption(OptionBuilder.hasArg(false)
				.withDescription(HELP_DESC).create(HELP));
		options.addOption(OptionBuilder.hasArg(true).isRequired()
				.withDescription(MODEL2_DESC).create(MODEL2));
		options.addOption(OptionBuilder.hasArg(true).isRequired()
				.withDescription(MODEL1_DESC).create(MODEL1));
		options.addOption(OptionBuilder.hasArg(true).isRequired()
				.withDescription(TARGET_DIR_DESC).create(TARGET_DIR));

		try {
			// parse options
			CommandLineParser parser = new GnuParser();
			CommandLine line = parser.parse(options, args);
			String model1 = line.getOptionValue(MODEL1);
			String model2 = line.getOptionValue(MODEL2);
			String target = line.getOptionValue(TARGET_DIR);
			// run application
			SubProcessExtraction app = new SubProcessExtractionCamunda();
			Definitions m1 = Bpmn2ResourceSet.getInstance().loadDefinitions(
					model1);
			Definitions m2 = Bpmn2ResourceSet.getInstance().loadDefinitions(
					model2);
			ProcessTransformation transformation = app.run(m1, m2);
			ProcessTransformationUtil.writeResults(target, transformation);
			// shut down logger
			ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
			if (loggerFactory instanceof LoggerContext) {
				LoggerContext context = (LoggerContext) loggerFactory;
				context.stop();
			}
		} catch (ParseException exp) {
			System.err.println("Parsing failed. Reason: " + exp.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(APPNAME, options);
		} catch (Exception e) {
			// shut down logger
			ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
			if (loggerFactory instanceof LoggerContext) {
				LoggerContext context = (LoggerContext) loggerFactory;
				context.stop();
			}
			e.printStackTrace(System.err);
			System.err.println("---");
			System.err
					.println("An application exception occured during execution, stack trace above, messge is: "
							+ e.getMessage());
		}
	}
}
