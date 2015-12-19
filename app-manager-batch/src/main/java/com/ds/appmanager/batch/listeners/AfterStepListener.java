package com.ds.appmanager.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class AfterStepListener implements StepExecutionListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(AfterStepListener.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOG.info("Step {} completed with status {} ",stepExecution.getStepName(),stepExecution.getExitStatus());
		LOG.info("Total Items Skipped {}",stepExecution.getSkipCount());
		LOG.info("Total Items Written {}",stepExecution.getWriteCount());
		return stepExecution.getExitStatus();
	}

}
