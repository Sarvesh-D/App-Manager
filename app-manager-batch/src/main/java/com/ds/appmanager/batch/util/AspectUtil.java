package com.ds.appmanager.batch.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AspectUtil {
	
	public static final Logger LOG = LoggerFactory.getLogger(AspectUtil.class);
	
	public static Object logMethodEntryAndExit(ProceedingJoinPoint pjp) throws Throwable {
		LOG.info("Entering method : {}", pjp.getSignature());
		Object retval = null;
		retval = pjp.proceed();
		LOG.info("Exiting method : {}", pjp.getSignature());
		return retval;
	}
	
	public static void logAfterException(JoinPoint jp , Throwable error) {
		LOG.error("Exception occured in method : {}", jp.getSignature());
		LOG.error("Exception : {}", error.getMessage());
	}

}
