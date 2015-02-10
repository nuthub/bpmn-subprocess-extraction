package edu.udo.cs.ls14.jf.bpmnmatching.ws.test;

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
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzer;
import edu.udo.cs.ls14.jf.bpmnanalysis.ProcessAnalyzerImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ProcessMatching;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterBehaviorSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterConditionsSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNestingsSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairFilterNodesSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FragmentPairJointerSequentialSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FullFragmentMatchingSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.FullNodeMatchingSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.NodePairFilterSEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.ProcessMatchingFactorySEI;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterBehaviorImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterConditionsImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterNestingsImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairFilterNodesImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FragmentPairJointerSequentialImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FullFragmentMatchingImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.FullNodeMatchingImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.NodePairFilterImpl;
import edu.udo.cs.ls14.jf.bpmnmatching.ws.impl.ProcessMatchingFactoryImpl;
import edu.udo.cs.ls14.jf.registry.Registries;
//import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairrankersize.FragmentPairRankerSizeImpl;
//import edu.udo.cs.ls14.jf.ws.bpmn.matching.fragmentpairrankersize.FragmentPairRankerSizeSEI;

public class BpmnMatchingTest {

	private List<Endpoint> endpoints;
	private ProcessMatchingFactorySEI portProcessMatchingFactory;
	private FullNodeMatchingSEI portFullNodeMatching;
	private FullFragmentMatchingSEI portFullFragmentMatching;
	private NodePairFilterSEI portNodePairFilter;
	private FragmentPairFilterNodesSEI portFragmentPairFilterNodes;
	private FragmentPairFilterBehaviorSEI portFragmentPairFilterBehavior;
	private FragmentPairFilterConditionsSEI portFragmentPairFilterConditions;
	private FragmentPairFilterNestingsSEI portFragmentPairFilterNestings;
	private FragmentPairJointerSequentialSEI portFragmentPairJointerSequential;
	// TODO: Move to transformation-services
	// private FragmentPairFilterTrivialSEI portFragmentPairFilterTrivial;
	// private FragmentPairRankerSizeSEI portFragmentPairRankerSize;

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
		portProcessMatchingFactory = Service.create(
				new URL(url + "?wsdl"),
				new QName("http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/",
						"ProcessMatchingFactoryImplService")).getPort(
				ProcessMatchingFactorySEI.class);
		// FullNodeMatching
		url = "http://localhost:8880/bpmnmatching-services/FullNodeMatchingImplService";
		endpoints.add(Endpoint.publish(url, new FullNodeMatchingImpl()));
		portFullNodeMatching = Service.create(
				new URL(url + "?wsdl"),
				new QName("http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/",
						"FullNodeMatchingImplService")).getPort(
				FullNodeMatchingSEI.class);

		// FullFragmentMatching
		url = "http://localhost:8880/bpmnmatching-services/FullFragmentMatchingImplService";
		endpoints.add(Endpoint.publish(url, new FullFragmentMatchingImpl()));
		portFullFragmentMatching = Service.create(
				new URL(url + "?wsdl"),
				new QName("http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/",
						"FullFragmentMatchingImplService")).getPort(
				FullFragmentMatchingSEI.class);

		// NodePairFilter
		url = "http://localhost:8880/bpmnmatching-services/NodePairFilterImplService";
		namespace = "http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/";
		localPart = "NodePairFilterImplService";
		endpoints.add(Endpoint.publish(url, new NodePairFilterImpl()));
		portNodePairFilter = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				NodePairFilterSEI.class);
		// FragmentPairFilterNodes
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterNodesService";
		namespace = "http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterNodesImplService";
		endpoints.add(Endpoint.publish(url, new FragmentPairFilterNodesImpl()));
		portFragmentPairFilterNodes = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterNodesSEI.class);

		// FragmentPairFilterBehavior
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterBehaviorService";
		namespace = "http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterBehaviorImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairFilterBehaviorImpl()));
		portFragmentPairFilterBehavior = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterBehaviorSEI.class);

		// FragmentPairFilterConditions
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterConditionsService";
		namespace = "http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterConditionsImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairFilterConditionsImpl()));
		portFragmentPairFilterConditions = Service.create(
				new URL(url + "?wsdl"), new QName(namespace, localPart))
				.getPort(FragmentPairFilterConditionsSEI.class);

		// FragmentPairFilterNestings
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairFilterNestingsService";
		namespace = "http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairFilterNestingsImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairFilterNestingsImpl()));
		portFragmentPairFilterNestings = Service.create(new URL(url + "?wsdl"),
				new QName(namespace, localPart)).getPort(
				FragmentPairFilterNestingsSEI.class);

		// FragmentPairJointerSequential
		url = "http://localhost:8880/bpmnmatching-services/FragmentPairJointerSequentialService";
		namespace = "http://impl.ws.bpmnmatching.jf.ls14.cs.udo.edu/";
		localPart = "FragmentPairJointerSequentialImplService";
		endpoints.add(Endpoint.publish(url,
				new FragmentPairJointerSequentialImpl()));
		portFragmentPairJointerSequential = Service.create(
				new URL(url + "?wsdl"), new QName(namespace, localPart))
				.getPort(FragmentPairJointerSequentialSEI.class);

		// TODO: Move to transformation services
		// FragmentPairFilterTrivial
		// url =
		// "http://localhost:8880/bpmnmatching-services/FragmentPairFilterTrivialService";
		// namespace =
		// "http://fragmentpairfiltertrivial.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		// localPart = "FragmentPairFilterTrivialImplService";
		// endpoints.add(Endpoint
		// .publish(url, new FragmentPairFilterTrivialImpl()));
		// portFragmentPairFilterTrivial = Service.create(new URL(url +
		// "?wsdl"),
		// new QName(namespace, localPart)).getPort(
		// FragmentPairFilterTrivialSEI.class);

		// // FragmentPairRankerSize
		// url =
		// "http://localhost:8880/bpmnmatching-services/FragmentPairRankerSizeService";
		// namespace =
		// "http://fragmentpairrankersize.matching.bpmn.ws.jf.ls14.cs.udo.edu/";
		// localPart = "FragmentPairRankerSizeImplService";
		// endpoints.add(Endpoint.publish(url, new
		// FragmentPairRankerSizeImpl()));
		// portFragmentPairRankerSize = Service.create(new URL(url + "?wsdl"),
		// new QName(namespace, localPart)).getPort(
		// FragmentPairRankerSizeSEI.class);

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
		processMatching = portFullNodeMatching
				.createFullNodeMatching(processMatching);
		processMatching = portFullFragmentMatching
				.createFullFragmentMatching(processMatching);
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
		// TODO: Move to transformation-services
		// processMatching =
		// portFragmentPairFilterTrivial.filter(processMatching);
		// assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		// assertEquals(1,
		// processMatching.getFragmentMatching().getPairs().size());

		// FragmentPairRankerSize
		// processMatching = portFragmentPairRankerSize
		// .setFragmentPairRankings(processMatching);
		// assertEquals(5, processMatching.getNodeMatching().getPairs().size());
		// assertEquals(1,
		// processMatching.getFragmentMatching().getPairs().size());

	}
}
