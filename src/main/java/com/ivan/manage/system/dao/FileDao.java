package com.ivan.manage.system.dao;

import com.ivan.manage.system.dto.FileDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FileDao {

    FileDto get(@Param("id") int id);

    List<FileDto> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(FileDto fileDto);

    int remove(@Param("id") int id);
}
