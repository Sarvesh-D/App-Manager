package com.ds.appmanager.batch.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchLauncher {
	
	private static final Logger LOG = LoggerFactory.getLogger(BatchLauncher.class);
	
	public static void main(String[] args) {
		String[] springConfigs = {
				"config/batch-context.xml",
				"config/persistence.xml",
				"jobs/batch-jobs.xml"
		};
		
		ApplicationContext context =  new ClassPathXmlApplicationContext(springConfigs);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		Job job = (Job) context.getBean("insertAppsJob");
		
		JobExecution execution = null;
		
		try {
			LOG.info("Starting Job {}",job.getName());
			execution = jobLauncher.run(job, new JobParameters());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LOG.info("Job {} completed with status {}",job.getName(),execution.getStatus());
		}
		
	}

}
