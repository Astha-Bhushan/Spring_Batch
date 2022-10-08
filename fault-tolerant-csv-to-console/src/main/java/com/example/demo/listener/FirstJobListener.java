package com.example.demo.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener  implements JobExecutionListener
{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("It is before job....");
		System.out.println("Job Id : " +  jobExecution.getJobId());
		System.out.println("Instance " +  jobExecution.getJobInstance());
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
	
		System.out.println("It is after job....");
		
	}

}
