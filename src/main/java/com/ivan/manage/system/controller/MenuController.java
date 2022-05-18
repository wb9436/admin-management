package com.ivan.manage.system.controller;

import com.ivan.manage.configuration.annotation.Log;
import com.ivan.manage.configuration.controller.BaseController;
import com.ivan.manage.configuration.util.Result;
import com.ivan.manage.configuration.util.Tree;
import com.ivan.manage.system.dto.MenuDto;
import com.ivan.manage.system.service.IMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
    public String prefix = "system/menu";

    @Autowired
    private IMenuService menuService;

    @RequiresPermissions("sys:menu:menu")
    @GetMapping()
    public String menu(Model model) {
        return prefix + "/menu";
    }

    @RequiresPermissions("sys:menu:menu")
    @RequestMapping("/list")
    @ResponseBody
    public List<MenuDto> list(@RequestParam Map<String, Object> params) {
        List<MenuDto> menus = menuService.list(params);
        return menus;
    }

    @Log("添加菜单")
    @RequiresPermissions("sys:menu:add")
    @GetMapping("/add/{pId}")
    public String add(Model model, @PathVariable("pId") Integer pId) {
        model.addAttribute("pId", pId);
        MenuDto menuDto = new MenuDto();
        if (pId == 0) {
            model.addAttribute("pName", "根目录");

            menuDto.setType(0); //类型 0：目录 1：菜单 2：按钮
            model.addAttribute("menu", menuDto);
        } else {
            MenuDto superMenu = menuService.get(pId);
            model.addAttribute("pName", superMenu.getName());

            if (superMenu.getType() == 0) { //上级是目录
                menuDto.setType(1); //下级菜单
                model.addAttribute("menu", menuDto);
            } else if (superMenu.getType() == 1) { //上级是菜单
                menuDto.setType(2); //下级按钮
                model.addAttribute("menu", menuDto);
            }
        }
        return prefix + "/add";
    }

    @Log("编辑菜单")
    @RequiresPermissions("sys:menu:edit")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        MenuDto mdo = menuService.get(id);
        Integer pId = mdo.getParentId();
        model.addAttribute("pId", pId);
        if (pId == 0) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(pId).getName());
        }
        model.addAttribute("menu", mdo);
        return prefix + "/edit";
    }

    @Log("保存菜单")
    @RequiresPermissions("sys:menu:add")
    @PostMapping("/save")
    @ResponseBody
    public Result save(MenuDto menu) {
        if (menuService.save(menu) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "保存失败");
        }
    }

    @Log("更新菜单")
    @RequiresPermissions("sys:menu:edit")
    @PostMapping("/update")
    @ResponseBody
    public Result update(MenuDto menu) {
        if (menuService.update(menu) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "更新失败");
        }
    }

    @Log("删除菜单")
    @RequiresPermissions("sys:menu:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Integer id) {
        if (menuService.remove(id) > 0) {
            return Result.ok();
        } else {
            return Result.error(1, "删除失败");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<MenuDto> tree() {
        Tree<MenuDto> tree = menuService.getTree();
        return tree;
    }

    @GetMapping("/tree/{roleId}")
    @ResponseBody
    public Tree<MenuDto> tree(@PathVariable("roleId") Integer roleId) {
        Tree<MenuDto> tree = menuService.getTree(roleId);
        return tree;
    }

}
