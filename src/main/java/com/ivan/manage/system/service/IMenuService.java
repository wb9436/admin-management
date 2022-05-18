package com.ivan.manage.system.service;

import com.ivan.manage.configuration.util.Tree;
import com.ivan.manage.system.dto.MenuDto;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface IMenuService {

	List<Tree<MenuDto>> getUserListMenuTree(int userId);

	List<MenuDto> list(Map<String, Object> params);

	MenuDto get(Integer pId);

	int save(MenuDto menu);

	int update(MenuDto menu);

	int remove(Integer menuId);

	Tree<MenuDto> getTree();

	Tree<MenuDto> getTree(Integer roleId);

	Set<String> listPerms(Integer userId);

}
