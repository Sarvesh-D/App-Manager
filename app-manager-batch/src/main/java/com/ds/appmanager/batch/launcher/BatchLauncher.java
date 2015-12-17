package com.ds.appmanager.batch.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchLauncher {
	
	public static void main(String[] args) {
		String[] springConfigs = {
				"config/batch-context.xml",
				"config/persistence.xml",
				"jobs/batch-jobs.xml"
		};
		
		ApplicationContext context =  new ClassPathXmlApplicationContext(springConfigs);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		Job job = (Job) context.getBean("insertAppsJob");
		
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println(execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
