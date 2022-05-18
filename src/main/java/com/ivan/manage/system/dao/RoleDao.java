package com.ivan.manage.system.dao;

import com.ivan.manage.system.dto.RoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleDao {

	RoleDto get(@Param("roleId") Integer roleId);

	List<RoleDto> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(RoleDto role);

	int update(RoleDto role);

	int remove(@Param("roleId") Integer roleId);

	int batchRemove(Integer[] roleIds);
}
