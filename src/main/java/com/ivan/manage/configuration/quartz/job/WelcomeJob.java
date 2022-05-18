package com.ivan.manage.configuration.quartz.job;

import com.ivan.manage.system.service.ITestService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomeJob implements Job {

	@Autowired
	private ITestService testService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		testService.startTestJob();
	}

}
