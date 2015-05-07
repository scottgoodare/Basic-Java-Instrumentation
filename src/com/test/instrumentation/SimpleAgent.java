package com.test.instrumentation;

import java.lang.instrument.Instrumentation;


public class SimpleAgent {
	
	
	public static void premain(String agentArgument,
			Instrumentation instrumentation) {
		
		instrumentation.getAllLoadedClasses();

	}

}
