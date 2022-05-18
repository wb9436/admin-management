package com.ivan.manage.system.service;

import com.ivan.manage.system.dto.RoleDto;

import java.util.List;


public interface IRoleService {

	List<RoleDto> list();

	RoleDto get(Integer id);

	int save(RoleDto role);

	int update(RoleDto role);

	int remove(Integer id);

	int batchremove(Integer[] ids);

	List<RoleDto> getByUserId(Integer userId);

}
