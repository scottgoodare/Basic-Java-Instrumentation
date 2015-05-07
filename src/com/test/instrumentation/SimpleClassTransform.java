package com.test.instrumentation;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.apache.log4j.Logger;

public class SimpleClassTransform implements ClassFileTransformer {
	
	private final Logger LOG = Logger.getLogger(SimpleClassTransform.class);

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		
		LOG.debug("Class To Transform: " + className);
		
		return classfileBuffer;
	}

}
