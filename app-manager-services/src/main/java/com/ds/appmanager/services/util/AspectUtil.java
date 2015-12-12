package com.ds.appmanager.services.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.ds.appmanager.services.exceptions.ApplicationException;

public class AspectUtil {

	private static final Logger logger = LoggerFactory.getLogger(AspectUtil.class); 
	
	public static boolean isTransactionActive() {
		return TransactionSynchronizationManager.isActualTransactionActive();
	}
	
	public static void proceedIfTransactionActive(JoinPoint jp) {
		if (isTransactionActive()) {
			logger.debug("Active transaction found. Proceeding with method {} ",jp.getSignature());
		} else {
			logger.debug("No active transaction found. Skipping call for method {} ",jp.getSignature());
			throw new NoTransactionException("NO active transaction found. Skipping call for method "+jp.getSignature());
		}
	}
	
	public static Object logMethodEntryAndExit(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Entering method : {}", pjp.getSignature());
		Object retval = null;
		retval = pjp.proceed();
		logger.info("Exiting method : {}", pjp.getSignature());
		return retval;
	}
	
	public static void logAfterException(JoinPoint jp , Throwable error) {
		logger.error("Exception occured in method : {}", jp.getSignature());
		logger.error("Exception : {}", error.getMessage());
	}
	
}
