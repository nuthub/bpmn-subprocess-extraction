package edu.udo.cs.ls14.jf.ws.bpmn.matching.matcher.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import org.eclipse.bpmn2.Definitions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.udo.cs.ls14.jf.bpmn.utils.Bpmn2ResourceSet;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalysis;
import edu.udo.cs.ls14.jf.bpmn.analysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmn.analysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.registry.Registries;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilterbehavior.FragmentPairFilterBehaviorImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilterbehavior.FragmentPairFilterBehaviorSEI;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilterconditions.FragmentPairFilterConditionsImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilterconditions.FragmentPairFilterConditionsSEI;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternestings.FragmentPairFilterNestingsImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternestings.FragmentPairFilterNestingsSEI;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternodes.FragmentPairFilterNodesImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfilternodes.FragmentPairFilterNodesSEI;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfiltertrivial.FragmentPairFilterTrivialImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairfiltertrivial.FragmentPairFilterTrivialSEI;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairjointersequential.FragmentPairJointerSequentialImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairjointersequential.FragmentPairJointerSequentialSEI;
//import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairrankersize.FragmentPairRankerSizeImpl;
//import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairrankersize.FragmentPairRankerSizeSEI;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.nodepairfilter.NodePairFilterImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.nodepairfilter.NodePairFilterSEI;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.processmatchingfactory.ProcessMatchingFactoryImpl;
import edu.udo.cs.ls14.jf.ws.bpmn.matching.processmatchingfactory.ProcessMatchingFactorySEI;

public class BpmnMatchingTest {

	private List<Endpoint> endpoints;
	private ProcessMatchingFactorySEI portProcessMatchingFactory;
	private NodePairFilterSEI portNodePairFilter;
	private FragmentPairFilterNodesSEI portFragmentPairFilterNodes;
	private FragmentPairFilterBehaviorSEI portFragmentPairFilterBehavior;
	private FragmentPairFilterConditionsSEI portFragmentPairFilterConditions;
	private FragmentPairFilterNestingsSEI portFragmentPairFilterNestings;
	private FragmentPairJointerSequentialSEI portFragmentPairJointerSequential;
	private FragmentPairFilterTrivialSEI portFragmentPairFilterTrivial;
//	private FragmentPairRankerSizeSEI portFragmentPairRankerSize;

	private Bpmn2ResourceSet resSet;

	@Before
	public void setUp() throws Exception {
		String url = null;
		String namespace = null;
		String localPart = null;
		endpoints = new ArrayList<Endpoint>();
		// ProcessMatchingFactory
		url = "http://localhost:8880/bpmnmatching-services/ProcessMatchingFactoryImplService";
		endpoints.add(Endpoint.publish(url, new ProcessMatchingFactoryImpl()));
		portProcessMatchingFactory = Service
				.create(new URL(url + "?wsdl"),
						new QName(
								"http://processmatchingfactory.matching.bpmn.ws.jf.ls14.cs.udo.edu/",
								"ProcessMatchingFactoryImplService")).getPort(
						ProcessMatchingFactorySEI.class);

		// NodePairFilter
		url = "http://localhost:8880/bpmnmatching-services/NodePairFilterImplService";
		namespace = "http://nodepairfilter.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		localPart = "NodePairFilterImplService";
		endpoints.add(Endpoint.publish(url, new NodePairFilterImpl()));
		portNodePairFilter = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				NodePairFilterSEI.class);
		// FragmentPairFilterNodes
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterNodesService";
		namespace = "http://fragmentpairfilternodes.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterNodesImplService";
		endpoints.add(Endpoint.publish(url, new FragmentPairFilterNodesImpl()));
		portFragmentPairFilterNodes = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterNodesSEI.class);

		// FragmentPairFilterBehavior
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterBehaviorService";
		namespace = "http://fragmentpairfilterbehavior.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterBehaviorImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairFilterBehaviorImpl()));
		portFragmentPairFilterBehavior = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterBehaviorSEI.class);

		// FragmentPairFilterConditions
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterConditionsService";
		namespace = "http://fragmentpairfilterconditions.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterConditionsImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairFilterConditionsImpl()));
		portFragmentPairFilterConditions = Service.create(
				new URL(url + "?wsdl"), new QName(namespace, localPart))
				.getPort(FragmentPairFilterConditionsSEI.class);

		// FragmentPairFilterNestings
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterNestingsService";
		namespace = "http://fragmentpairfilternestings.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterNestingsImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairFilterNestingsImpl()));
		portFragmentPairFilterNestings = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterNestingsSEI.class);

		// FragmentPairJointerSequential
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairJointerSequentialService";
		namespace = "http://fragmentpairjointersequential.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairJointerSequentialImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairJointerSequentialImpl()));
		portFragmentPairJointerSequential = Service.create(
				new URL(url + "?wsdl"), new QName(namespace, localPart))
				.getPort(FragmentPairJointerSequentialSEI.class);

		// FragmentPairFilterTrivial
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterTrivialService";
		namespace = "http://fragmentpairfiltertrivial.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterTrivialImplService";
		endpoints.add(Endpoint
				.publish(url, new FragmentPairFilterTrivialImpl()));
		portFragmentPairFilterTrivial = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterTrivialSEI.class);

//		// FragmentPairRankerSize
//		url = "http://localhost:8880/bpmnmatching-services/FragmentPairRankerSizeService";
//		namespace = "http://fragmentpairrankersize.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
//		localPart = "FragmentPairRankerSizeImplService";
//		endpoints.add(Endpoint.publish(url, new FragmentPairRankerSizeImpl()));
//		portFragmentPairRankerSize = Service.create(new URL(url + "?wsdl"),
//				new QName(namespace, localPart)).getPort(
//				FragmentPairRankerSizeSEI.class);

		// Register resource factories
		Registries.registerAll();
		resSet = Bpmn2ResourceSet.getInstance();

	}

	@After
	public void tearDown() {
		for (Endpoint endpoint : endpoints) {
			endpoint.stop();
		}
	}

	@Test
	public void testPM1PM2() throws Exception {
		// create some test data
		String pathname = "/bpmn/parallelGateway/";
		Definitions definitions1 = resSet.loadDefinitions(getClass()
				.getResource(pathname + "parallelism1.bpmn").getFile());
		Definitions definitions2 = resSet.loadDefinitions(getClass()
				.getResource(pathname + "parallelism2.bpmn").getFile());
		ProcessAnalyzer analyzer = new ProcessAnalyzerImpl();
		ProcessAnalysis analysis1 = analyzer.analyze(definitions1);
		ProcessAnalysis analysis2 = analyzer.analyze(definitions2);
		// ProcessMatchingFactory
		ProcessMatching processMatching = portProcessMatchingFactory.create(
				analysis1, analysis2);
		assertEquals(49, processMatching.getNodeMatching().getPairs().size());
		assertEquals(42, processMatching.getFragmentMatching().getPairs()
				.size());
		// NodePairFilter
		processMatching = portNodePairFilter.filter(processMatching);
		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		assertEquals(42, processMatching.getFragmentMatching().getPairs()
				.size());
		// FragmentPairFilterNodes
		processMatching = portFragmentPairFilterNodes.filter(processMatching);
		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		assertEquals(4, processMatching.getFragmentMatching().getPairs().size());
		// FragmentPairFilterBehavior
		processMatching = portFragmentPairFilterBehavior
				.filter(processMatching);
		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		assertEquals(4, processMatching.getFragmentMatching().getPairs().size());

		// FragmentPairFilterConditions
		processMatching = portFragmentPairFilterConditions
				.filter(processMatching);
		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		assertEquals(4, processMatching.getFragmentMatching().getPairs().size());

		// FragmentPairFilterNestings
		processMatching = portFragmentPairFilterNestings
				.filter(processMatching);
		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		assertEquals(1, processMatching.getFragmentMatching().getPairs().size());

		// FragmentPairJointerSequential
		processMatching = portFragmentPairJointerSequential
				.join(processMatching);
		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		assertEquals(1, processMatching.getFragmentMatching().getPairs().size());

		// FragmentPairFilterTrivial
		processMatching = portFragmentPairFilterTrivial.filter(processMatching);
		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		assertEquals(1, processMatching.getFragmentMatching().getPairs().size());

		// FragmentPairRankerSize
//		processMatching = portFragmentPairRankerSize
//				.setFragmentPairRankings(processMatching);
//		assertEquals(5, processMatching.getNodeMatching().getPairs().size());
//		assertEquals(1, processMatching.getFragmentMatching().getPairs().size());

	}
}
