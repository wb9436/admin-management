package com.ivan.manage.system.service.impl;

import com.ivan.manage.configuration.config.BaseConfig;
import com.ivan.manage.configuration.util.FileUploadUtils;
import com.ivan.manage.system.dao.FileDao;
import com.ivan.manage.system.dto.FileDto;
import com.ivan.manage.system.service.IFileService;
import com.ivan.manage.utils.BService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl extends BService implements IFileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public List<FileDto> list(Map<String, Object> params) {
        return fileDao.list(params);
    }

    @Override
    public int count(Map<String, Object> params) {
        return fileDao.count(params);
    }

    @Override
    public int save(FileDto fileDto, MultipartFile file) {
        if (file == null) {
            return 0;
        }
        String url = new FileUploadUtils().uploadFile(BaseConfig.getImageUploadDir(), null, "/file", file);
        if (StringUtils.isNotBlank(url)) {
            fileDto.setCreateTime(new Date());
            fileDto.setUrl(url);
            return fileDao.save(fileDto);
        }
        return 0;
    }

    @Override
    public int remove(int id) {
        FileDto fileDto = fileDao.get(id);
        if (fileDto != null) {
            new FileUploadUtils().doDeleteOldFile(BaseConfig.getImageUploadDir(), fileDto.getUrl());
            return fileDao.remove(id);
        }
        return 0;
    }
}
