package com.ivan.manage.system.controller;

import com.ivan.manage.configuration.annotation.Log;
import com.ivan.manage.configuration.controller.BaseController;
import com.ivan.manage.configuration.util.Result;
import com.ivan.manage.system.dto.RoleDto;
import com.ivan.manage.system.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "system/role";

	@Autowired
	private IRoleService roleService;

	@RequiresPermissions("sys:role:role")
	@GetMapping()
	public String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	public List<RoleDto> list() {
		List<RoleDto> roles = roleService.list();
		return roles;
	}

	@Log("添加角色")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	@Log("保存角色")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	public Result save(RoleDto role) {
		if (roleService.save(role) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "保存失败");
		}
	}

	@Log("编辑角色")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		RoleDto roleDto = roleService.get(id);
		model.addAttribute("role", roleDto);
		return prefix + "/edit";
	}

	@Log("更新角色")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	public Result update(RoleDto role) {
		if (roleService.update(role) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	public Result save(Integer id) {
		if (roleService.remove(id) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "删除失败");
		}
	}

	@RequiresPermissions("sys:role:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public Result batchRemove(@RequestParam("ids[]") Integer[] ids) {
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return Result.ok();
		}
		return Result.error();
	}
}
