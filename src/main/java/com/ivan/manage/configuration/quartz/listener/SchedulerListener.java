package com.ivan.manage.configuration.quartz.listener;

import com.ivan.manage.system.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * CommandLineRunner 服务器启动执行
 *
 * @author WB
 *
 */
@Configuration
@Order(value = 1)
public class SchedulerListener implements CommandLineRunner {

	@Autowired
	ITaskService taskService;

	@Override
	public void run(String... args) throws Exception {
		try {
			taskService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
