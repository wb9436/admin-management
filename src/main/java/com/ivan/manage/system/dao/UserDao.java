package com.ivan.manage.system.dao;

import com.ivan.manage.system.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

	UserDto get(@Param("userId") Integer userId);

	List<UserDto> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserDto user);

	int update(UserDto user);

	int remove(@Param("userId") Integer userId);

	int batchRemove(Integer[] userIds);

	UserDto checkUser(Map<String, Object> params);

	int resetGame(@Param("gameTypes") String gameTypes);

    int resetArea(@Param("areaId") String areaId);
}
