package com.ivan.manage.configuration.shiro;

import com.ivan.manage.system.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}

	public static UserDto getUser() {
		Object object = getSubjct().getPrincipal();
		return (UserDto) object;
	}

	public static Integer getUserId() {
		return getUser().getUserId();
	}

	public static void logout() {
		getSubjct().logout();
	}


}
