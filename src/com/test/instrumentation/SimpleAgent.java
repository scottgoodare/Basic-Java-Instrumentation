package com.test.instrumentation;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.splunk.DataModel;
import com.splunk.DataModelCollection;
import com.splunk.DataModelConstraint;
import com.splunk.DataModelObject;
import com.splunk.Service;

public class SimpleAgent {
	
	private static final Logger LOG = Logger.getLogger(SimpleAgent.class);
	private static SimpleAgent agent;
	
	private ArrayBlockingQueue<SplunkLogEvent> queue;
	private List<String> whiteList;
	private static String splunkAppName;
	
	public SimpleAgent() {
		whiteList = getSplunkAppConfigs();
	}
	
	public static void premain(String agentArgument,
			Instrumentation instrumentation) {
		
		agent = new SimpleAgent();
		splunkAppName = agentArgument;
		LOG.debug("Splunk App: " + splunkAppName);
		
		instrumentation.addTransformer(new SimpleClassTransform());
	}
	
	private List<String> getSplunkAppConfigs() {
		List<String> objectList = new ArrayList<>();
		Service service = new Service("Scotts-MacBook-Pro.local", 8089);
		service.login("admin", "");
		
		DataModelCollection dataModels = service.getDataModels();
		LOG.debug("data models: " + dataModels.keySet());
		DataModel dataModel = dataModels.get("APM_Packages");
		LOG.debug("DataModel: " + dataModel.getName());
		
		for (DataModelObject dmObject : dataModel.getObjects()) {
			for(DataModelConstraint dmConstraint : dmObject.getConstraints()) {
				LOG.debug("Constraint: " + dmConstraint.getQuery());
				objectList.add(dmConstraint.getQuery());
			}
		}
		return objectList;
	}

}
