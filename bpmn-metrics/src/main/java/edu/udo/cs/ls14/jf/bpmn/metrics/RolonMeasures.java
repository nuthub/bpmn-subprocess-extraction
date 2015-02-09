package edu.udo.cs.ls14.jf.bpmn.metrics;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

public class RolonMeasures {

	protected OCL<EPackage, EClassifier, EOperation, EStructuralFeature, ?, ?, EObject, ?, ?, Constraint, EClass, EObject> ocl;
	protected OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> helper;
	private static final String OCL_RESOURCE = "/RolonMeasures.ocl";

	public String evaluate(Process process) throws ParserException,
			MalformedURLException {
		// Create environment
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.putIfAbsent("bpmn", new Bpmn2ResourceFactoryImpl());
		EPackage.Registry.INSTANCE.putIfAbsent(Bpmn2Package.eNS_URI,
				Bpmn2Package.eINSTANCE);
		EcoreEnvironmentFactory environmentFactory = new EcoreEnvironmentFactory(
				EPackage.Registry.INSTANCE);

		// Create OCL instance
		ocl = OCL.newInstance(environmentFactory);
		if (ocl == null) {
			throw new RuntimeException("Could not create environment factory");
		}
		System.out.println("OCL initiated with package "
				+ Bpmn2Package.eINSTANCE);

		InputStream oclStream = getClass().getResourceAsStream(OCL_RESOURCE);

		// Create helper
		helper = ocl.createOCLHelper();
		helper.setContext(Bpmn2Package.Literals.PROCESS);
		OCLInput input = new OCLInput(oclStream);
		List<Constraint> constraints = ocl.parse(input);
		System.out.println("Constraints: " + constraints);
		System.out.println("OCL Constraints: " + ocl.getConstraints());

//		for (Constraint constraint : constraints) {
//			// System.out.println(constraint.getName() + ": " + constraint);
//		}
		OCLExpression<EClassifier> query = helper.createQuery("Measures()");
		return (String) ocl.evaluate(process, query);

	}

}
