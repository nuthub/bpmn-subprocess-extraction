/**
 * <copyright>
 * Copyright (c) 2010-2014 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package edu.udo.cs.ls14.jf.transformation;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.henshin.interpreter.ApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.Assignment;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.BasicApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.util.InterpreterUtil;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link ApplicationMonitor} implementation with logging capabilities.
 * 
 * @author Christian Krause
 *
 */
public class HenshinApplicationMonitor extends BasicApplicationMonitor {

	private Logger logger = LoggerFactory
			.getLogger(HenshinApplicationMonitor.class);

	// Whether to print logs only for rule applications:
	protected boolean onlyRuleApplications = false;

	// Whether to print logs only for unit (not rule) applications:
	protected boolean onlyUnitApplications = false;

	// Whether to print logs only for successful unit applications:
	protected boolean onlySuccesses = false;

	// Whether to print logs only for failed unit applications:
	protected boolean onlyFailures = false;

	// URI for saving intermediate models:
	protected URI autoSaveURI;

	// Execution step:
	protected int step = 0;

	// Maximum number of execution steps:
	protected int maxSteps = -1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.henshin.interpreter.impl.ApplicationMonitorImpl#cancel()
	 */
	@Override
	public void cancel() {
		super.cancel();
		logger.debug("=== CANCEL REQUESTED ===");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.henshin.interpreter.impl.ApplicationMonitorImpl#cancelAndUndo
	 * ()
	 */
	@Override
	public void cancelAndUndo() {
		super.cancelAndUndo();
		logger.debug("=== CANCEL AND UNDO REQUESTED ===");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.henshin.interpreter.impl.ApplicationMonitorImpl#notifyExecute
	 * (org.eclipse.emf.henshin.interpreter.UnitApplication, boolean)
	 */
	@Override
	public void notifyExecute(UnitApplication application, boolean success) {
		super.notifyExecute(application, success);
		logStep(application, success, "EXECUTED");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.henshin.interpreter.impl.ApplicationMonitorImpl#notifyUndo
	 * (org.eclipse.emf.henshin.interpreter.UnitApplication, boolean)
	 */
	@Override
	public void notifyUndo(UnitApplication application, boolean success) {
		super.notifyUndo(application, success);
		logStep(application, success, "UNDONE");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.henshin.interpreter.impl.ApplicationMonitorImpl#notifyRedo
	 * (org.eclipse.emf.henshin.interpreter.UnitApplication, boolean)
	 */
	@Override
	public void notifyRedo(UnitApplication application, boolean success) {
		super.notifyExecute(application, success);
		logStep(application, success, "REDONE");
	}

	/*
	 * Print log info about an execution step.
	 */
	protected void logStep(UnitApplication application, boolean success,
			String stepKind) {
		if (onlyRuleApplications && !(application instanceof RuleApplication)) {
			return;
		}
		if (onlyUnitApplications && (application instanceof RuleApplication)) {
			return;
		}
		if (onlySuccesses && !success) {
			return;
		}
		if (onlyFailures && success) {
			return;
		}
		step++;
		EGraph graph = application.getEGraph();
		logger.debug("=== ("
				+ step
				+ ") "
				+ stepKind
				+ ((application instanceof RuleApplication) ? " RULE "
						: " UNIT ") + "'" + application.getUnit().getName()
				+ "' [" + String.valueOf(success).toUpperCase() + "] ===");
		String edges = "?";
		try {
			edges = String.valueOf(InterpreterUtil.countEdges(graph));
		} catch (Throwable t) {
		}

		logger.debug("Graph size: " + graph.size() + " nodes, " + edges
				+ " edges");
		if (application instanceof RuleApplication) {
			RuleApplication ruleApp = (RuleApplication) application;
			if (success) {
				logger.debug("Success: " + System.getProperty("line.separator")
						+ ruleApp.getCompleteMatch().toString()
						+ System.getProperty("line.separator")
						+ ruleApp.getResultMatch().toString());
			} else {
				Match match = ruleApp.getPartialMatch();
				if (match != null && !match.isEmpty()) {
					logger.debug("Partial Match: "
							+ System.getProperty("line.separator")
							+ "Partial "
							+ ruleApp.getPartialMatch().toString()
									.replaceFirst("Match", "match"));
				}
			}
		} else {
			Assignment assignment = application.getAssignment();
			Assignment resultAssignment = application.getResultAssignment();
			if (assignment != null && !assignment.isEmpty()) {
				logger.debug("Assignment: "
						+ System.getProperty("line.separator")
						+ assignment.toString());
			}
			if (success && resultAssignment != null
					&& !resultAssignment.isEmpty()) {
				logger.debug("ResultAssignment: "
						+ System.getProperty("line.separator")
						+ resultAssignment.toString());
			}
		}
		// Save intermediate result?
		if (autoSaveURI != null) {
			String basename = autoSaveURI.lastSegment();
			String realname = autoSaveURI.trimFileExtension().lastSegment()
					+ "-" + new DecimalFormat("0000").format(step) + "."
					+ autoSaveURI.fileExtension();
			URI uri = URI.createURI(autoSaveURI.toString().replaceFirst(
					basename, realname));
			ResourceSet resourceSet = null;
			for (EObject root : graph.getRoots()) {
				if (root.eResource() != null
						&& root.eResource().getResourceSet() != null) {
					resourceSet = root.eResource().getResourceSet();
					break;
				}
			}
			if (resourceSet == null) {
				resourceSet = new HenshinResourceSet();
			}
			EGraph copy = graph.copy(null);
			Resource resource = resourceSet.createResource(uri);
			resource.getContents().addAll(copy.getRoots());
			Map<Object, Object> options = new HashMap<Object, Object>();
			options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
			try {
				logger.info("Saving intermediate result to " + uri);
				resource.save(options);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		if (maxSteps >= 0 && step >= maxSteps) {
			logger.error("Terminated after " + step
					+ " steps by logging application monitor.");
			System.exit(1);
		}
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}
