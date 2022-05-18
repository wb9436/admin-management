package com.ivan.manage.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivan.manage.configuration.annotation.Log;
import com.ivan.manage.configuration.controller.BaseController;
import com.ivan.manage.configuration.util.PageUtils;
import com.ivan.manage.configuration.util.Result;
import com.ivan.manage.system.dto.RoleDto;
import com.ivan.manage.system.dto.UserDto;
import com.ivan.manage.system.service.IRoleService;
import com.ivan.manage.system.service.ISysUserService;
import com.ivan.manage.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/sys/user")
@Controller
public class SysUserController extends BaseController {
    private final String prefix = "system/user";

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("sys:user:user")
    @GetMapping()
    public String user(Model model) {
        return prefix + "/user";
    }

    @RequiresPermissions("sys:user:user")
    @GetMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<UserDto> sysUserList = sysUserService.list(query);
        int total = sysUserService.count(query);
        PageUtils pageUtil = new PageUtils(sysUserList, total);
        return pageUtil;
    }

    @Log("添加用户")
    @RequiresPermissions("sys:user:add")
    @GetMapping("/add")
    public String add(Model model) {
        List<RoleDto> roles = roleService.list();
        model.addAttribute("roles", roles);

        JSONArray areaList = UserArea.getAreaList(null, false);
        model.addAttribute("areaList", areaList);

        JSONArray gameList = GameType.getGameList(null);
        model.addAttribute("gameList", gameList);

        return prefix + "/add";
    }

    @Log("保存用户")
    @RequiresPermissions("sys:user:add")
    @PostMapping("/save")
    @ResponseBody
    public Result save(UserDto user) {
        user.setPassword(MD5.encodeUTF8(user.getPassword()));
        if (sysUserService.save(user) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Log("编辑用户")
    @RequiresPermissions("sys:user:edit")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        UserDto userDto = sysUserService.get(id);
        model.addAttribute("user", userDto);
        List<RoleDto> roleDtos = roleService.list();
        model.addAttribute("roles", roleDtos);
        return prefix + "/edit";
    }

    @Log("更新用户")
    @RequiresPermissions("sys:user:edit")
    @PostMapping("/update")
    @ResponseBody
    public Result update(UserDto user) {
        if (sysUserService.update(user) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Log("删除用户")
    @RequiresPermissions("sys:user:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Integer id) {
        if (sysUserService.remove(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @RequiresPermissions("sys:user:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public Result batchRemove(@RequestParam("ids[]") Integer[] userIds) {
        int r = sysUserService.batchRemove(userIds);
        if (r > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("/exit")
    @ResponseBody
    public boolean exit(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !sysUserService.exit(params);
    }

    @Log("重置密码")
    @RequiresPermissions("sys:user:resetPwd")
    @GetMapping("/resetPwd/{id}")
    public String resetPwd(@PathVariable("id") Integer userId, Model model) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        model.addAttribute("user", userDto);
        return prefix + "/reset_pwd";
    }

    @Log("管理员重置密码")
    @RequiresPermissions("sys:user:resetPwd")
    @PostMapping("/adminResetPwd")
    @ResponseBody
    public Result adminResetPwd(UserDto userDto) {
        try {
            sysUserService.adminResetPwd(userDto);
            return Result.ok();
        } catch (Exception e) {
            return Result.error(1, e.getMessage());
        }
    }

    @GetMapping("/personal")
    public String personal(Model model) {
        UserDto userDto = sysUserService.get(getUser().getUserId());
        model.addAttribute("user", userDto);
        return prefix + "/personal";
    }

    @PostMapping("/updatePersonal")
    @ResponseBody
    public Result updatePersonal(UserDto user) {
        if (sysUserService.updatePersonal(user) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @Log("自己重置密码")
    @PostMapping("/resetPwd")
    @ResponseBody
    public Result resetPwd(int userId, String pwdOld, String pwdNew) {
        try {
            UserDto userDto = sysUserService.get(userId);
            if (userDto == null || !MD5.encodeUTF8(pwdOld).equals(userDto.getPassword())) {
                return Result.error("旧密码错误");
            }
            userDto.setPassword(MD5.encodeUTF8(pwdNew));
            sysUserService.userResetPwd(userDto);
            return Result.ok();
        } catch (Exception e) {
            return Result.error(1, e.getMessage());
        }
    }

}
