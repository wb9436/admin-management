package com.ivan.manage.system.dao;

import com.ivan.manage.system.dto.TaskDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskDao {

	TaskDto get(@Param("id") Integer id);

	List<TaskDto> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(TaskDto task);

	int update(TaskDto task);

	int remove(@Param("id") Integer id);

	int batchRemove(Integer[] ids);
}
