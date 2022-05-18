package com.ivan.manage.system.service.impl;

import com.ivan.manage.system.dao.LogDao;
import com.ivan.manage.system.dto.LogDto;
import com.ivan.manage.system.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    LogDao logDao;

    @Async
    @Override
    public void save(LogDto logDO) {
        logDao.save(logDO);
    }

    @Override
    public int remove(Long id) {
        int count = logDao.remove(id);
        return count;
    }

    @Override
    public int batchRemove(Long[] ids) {
        return logDao.batchRemove(ids);
    }

    @Override
    public void cleanData() {
        logDao.cleanData();
    }
}
