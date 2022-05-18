package com.ivan.manage.system.dao;

import com.ivan.manage.system.dto.LogDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 */
@Repository
public interface LogDao {

    LogDto get(Long id);

    List<LogDto> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(LogDto log);

    int update(LogDto log);

    int remove(Long id);

    int batchRemove(Long[] ids);

    void cleanData();
}
