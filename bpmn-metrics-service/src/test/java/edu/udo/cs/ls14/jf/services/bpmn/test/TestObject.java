package edu.udo.cs.ls14.jf.services.bpmn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Task;

@XmlRootElement
public class TestObject {

	public String string = "I am a string";

	public List<String> strList = new ArrayList<String>();

	public Map<String, String> strMap = new HashMap<String, String>();

	public TestObject() {
		strList.add("A");
		strList.add("B");

		strMap.put("A", "B");
		strMap.put("C", "D");

		Task t1 = Bpmn2Factory.eINSTANCE.createTask();
		t1.setName("A");
		Task t2 = Bpmn2Factory.eINSTANCE.createTask();
		t2.setName("B");
		Task t3 = Bpmn2Factory.eINSTANCE.createTask();
		t3.setName("C");
//		profile.put(Pair.with(t1, t2), RelationType.PARALLEL);
//		profile.put(Pair.with(t2, t1), RelationType.PARALLEL);
//		profile.put(Pair.with(t1, t3), RelationType.DIRECT_PREDECESSOR);
//		profile.put(Pair.with(t3, t1), RelationType.DIRECT_SUCCESSOR);
//		profile.put(Pair.with(t2, t3), RelationType.NO_SUCCESSION);
//		profile.put(Pair.with(t3, t2), RelationType.NO_SUCCESSION);

//		task.setName("My Task");

	}
}
