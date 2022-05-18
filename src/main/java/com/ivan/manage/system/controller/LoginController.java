package com.ivan.manage.system.controller;

import com.ivan.manage.configuration.annotation.Log;
import com.ivan.manage.configuration.controller.BaseController;
import com.ivan.manage.configuration.shiro.ShiroUtils;
import com.ivan.manage.configuration.util.Result;
import com.ivan.manage.configuration.util.Tree;
import com.ivan.manage.system.dto.MenuDto;
import com.ivan.manage.system.dto.UserDto;
import com.ivan.manage.system.service.IMenuService;
import com.ivan.manage.system.service.ISysUserService;
import com.ivan.manage.utils.IPUtils;
import com.ivan.manage.utils.MD5;
import com.ivan.manage.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController extends BaseController {
    private Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private IMenuService menuService;
    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result ajaxLogin(HttpServletRequest request, String username, String password) {
        String ip = IPUtils.getIpAddress(request);
        password = MD5.encodeUTF8(password);
        UserDto userDto = sysUserService.getUserDto(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        if (userDto != null) {
            if (userDto.getStatus() == 0) {
                return Result.error("账号已被锁定,请联系管理员");
            }
            String authIp = userDto.getAuthIp();
            if (!StringUtils.isTrimEmpty(authIp)) {
                if (!authIp.contains(ip)) {
                    logger.error("username: " + username + "使用非法IP[" + ip + "]登录管理后台");
                    return Result.error("账号登录IP未被授权,请联系管理员");
                }
            }
            subject.login(token);
            return Result.ok();
        } else {
            return Result.error("用户或密码错误");
        }
    }

    @Log("请求访问主页")
    @GetMapping("/index")
    public String index(Model model) {
        List<Tree<MenuDto>> menus = menuService.getUserListMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("username", getUsername());
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

}
