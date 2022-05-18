package com.ivan.manage.system.service.impl;

import com.ivan.manage.system.service.ITestService;
import com.ivan.manage.utils.BService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends BService implements ITestService {

	@Override
	public void startTestJob() {
		System.err.println("Welcome to manage system, now time :" + System.currentTimeMillis());
	}

}
