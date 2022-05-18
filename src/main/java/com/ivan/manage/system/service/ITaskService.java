package com.ivan.manage.system.service;

import com.ivan.manage.system.dto.TaskDto;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

public interface ITaskService {

	List<TaskDto> list(Map<String, Object> params);

	int count(Map<String, Object> params);

	TaskDto get(Integer id);

	int save(TaskDto taskScheduleJob);

	void update(TaskDto taskScheduleJob);

	int remove(Integer id);

	int batchRemove(Integer[] ids);

	void changeStatus(Integer id, String cmd);

	void initSchedule() throws SchedulerException;
}
