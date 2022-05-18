package com.ivan.manage.configuration.controller;

import com.ivan.manage.configuration.shiro.ShiroUtils;
import com.ivan.manage.system.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    public UserDto getUser() {
        return ShiroUtils.getUser();
    }

    public Integer getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    public String getName() {
        return getUser().getName();
    }
}
