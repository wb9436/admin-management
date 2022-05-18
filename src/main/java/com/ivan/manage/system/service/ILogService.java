package com.ivan.manage.system.service;

import com.ivan.manage.system.dto.LogDto;

public interface ILogService {
    void save(LogDto logDO);

    int remove(Long id);

    int batchRemove(Long[] ids);

    void cleanData();
}
