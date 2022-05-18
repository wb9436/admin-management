package com.ivan.manage.system.dto;

import com.ivan.manage.utils.DTO;
import lombok.Data;

import java.util.Date;

@Data
public class FileDto extends DTO {
    private static final long serialVersionUID = 6521259121462110570L;

    private int id;
    private String title;
    private String url;//文件地址
    private Date createTime; //上传时间

    private String downloadUrl; //文件下载链接

    public String getDownloadUrl() {
//        if (!StringUtils.isEmpty(url)) {
//            downloadUrl = ApplicationConfig.getImageVisitPath() + url;
//        } else {
//            downloadUrl = "";
//        }
        return downloadUrl;
    }

}
