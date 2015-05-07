package com.test.instrumentation;

import java.lang.instrument.Instrumentation;

import org.apache.log4j.*;

public class SimpleAgent {
	
	private static final Logger LOG = Logger.getLogger(SimpleAgent.class);
	
	public static void premain(String agentArgument,
			Instrumentation instrumentation) {
		
		for ( Class<?> c : instrumentation.getAllLoadedClasses() ){
			LOG.info("Class Name: " + c.getCanonicalName());
		}
		
		instrumentation.addTransformer(new SimpleClassTransform());
	}

}
