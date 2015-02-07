package edu.udo.cs.ls14.bpmn.test;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BpmnDiPackage;
import org.eclipse.dd.dc.DcPackage;
import org.eclipse.dd.di.DiPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.junit.Assert;

//import edu.udo.cs.ls14.qe.ocl.checker.impl.EMFUtil;

public abstract class EvaluatorBaseTest {

	protected OCL<EPackage, EClassifier, EOperation, EStructuralFeature, ?, ?, EObject, ?, ?, Constraint, EClass, EObject> ocl;
	protected OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> helper;

//	@Before
	public void setUp() {
		// Create environment
		EPackage.Registry registry = new EPackageRegistryImpl();
		registry.put(Bpmn2Package.eINSTANCE.getName(), Bpmn2Package.eINSTANCE);
		// TODO: REquired?
		registry.put(BpmnDiPackage.eINSTANCE.getName(), BpmnDiPackage.eINSTANCE);
		registry.put(DcPackage.eINSTANCE.getName(), DcPackage.eINSTANCE);
		registry.put(DiPackage.eINSTANCE.getName(), DiPackage.eINSTANCE);

		EcoreEnvironmentFactory environmentFactory = new EcoreEnvironmentFactory(
				registry);

		// Create OCL instance
		ocl = OCL.newInstance(environmentFactory);
		if (ocl == null) {
			Assert.fail("Could not get instance of OCL!");
		}
		System.out.println("OCL initiated with package "
				+ Bpmn2Package.eINSTANCE);

		// Create helper
		helper = ocl.createOCLHelper();
		helper.setContext(Bpmn2Package.Literals.PROCESS);
	}

	protected String evaluate(String xmiPath, InputStream oclStream)
			throws ParserException, MalformedURLException {
		setUp();
		// load process
/*		EObject xmiContent = EMFUtil.loadObjectFromXmi(xmiPath);
		Process process = null;
		for (EObject o : xmiContent.eContents()) {
			if (o instanceof Process) {
				process = (Process) o;
			}
		}
		Assert.assertNotNull(process);
		System.out.println("Process loaded: " + process);

		// Parse ocl input
		OCLInput input = new OCLInput(oclStream);
		List<Constraint> constraints = ocl.parse(input);
		System.out.println("Constraints: " + constraints);
		System.out.println("OCL Constraints: " + ocl.getConstraints());

		for (Constraint constraint : constraints) {
			System.out.println(constraint.getName() + ": " + constraint);
		}
		OCLExpression<EClassifier> query = helper.createQuery("Measures()");
		return (String) ocl.evaluate(process, query);
		*/
		return null;
	}

}
