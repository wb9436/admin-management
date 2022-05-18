package com.ivan.manage.system.service;

import com.ivan.manage.system.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface ISysUserService {

    UserDto getUserDto(String username, String password);

    List<UserDto> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    UserDto get(Integer id);

    int save(UserDto user);

    int update(UserDto user);

    int updatePersonal(UserDto user);

    int remove(Integer id);

    int batchRemove(Integer[] userIds);

    boolean exit(Map<String, Object> params);

    int adminResetPwd(UserDto userDto) throws Exception;

    int userResetPwd(UserDto userDto);

}
