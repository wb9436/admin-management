package com.ivan.manage.system.dao;

import com.ivan.manage.system.dto.RoleMenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMenuDao {

	RoleMenuDto get(@Param("id") Integer id);

	List<RoleMenuDto> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(RoleMenuDto roleMenu);

	int update(RoleMenuDto roleMenu);

	int remove(@Param("id") Integer id);

	int batchRemove(Integer[] ids);

	List<Integer> listMenuIdByRoleId(@Param("roleId") Integer roleId);

	int removeByRoleId(@Param("roleId") Integer roleId);

	int removeByMenuId(@Param("menuId") Integer menuId);

	int batchSave(List<RoleMenuDto> list);
}
