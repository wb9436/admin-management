package com.ivan.manage.system.service;

import com.ivan.manage.system.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IFileService {
    List<FileDto> list(Map<String, Object> params);

    int count(Map<String, Object> params);

    int save(FileDto fileDto, MultipartFile file);

    int remove(int id);
}
