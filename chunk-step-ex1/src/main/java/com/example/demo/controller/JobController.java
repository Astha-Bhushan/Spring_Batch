package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	JobLauncher jobLauncher ;
	
	@Autowired
	Job secondJob ; 
	
	@GetMapping("/start")
	public String startJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
	{
		Map<String , JobParameter > params = new HashMap<String , JobParameter>() ;
		params.put("Current Time", new JobParameter(System.currentTimeMillis())) ;
		
		JobParameters jobParameters = new JobParameters(params) ;
		
		jobLauncher.run(secondJob, jobParameters) ;
		
		return "Second Job is running ";
		
	}
	
}
