package com.ivan.manage.configuration.util;

import com.ivan.manage.utils.DateUtils;
import com.ivan.manage.utils.RandStrUtils;
import com.ivan.manage.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class FileUploadUtils {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 文件上传
     *
     * @param uploadPath    上传地址
     * @param multipartFile
     * @param fileUrl
     */
    protected void doFileUpload(String uploadPath, MultipartFile multipartFile, String fileUrl) {
        try {
            File destFile = new File(uploadPath, fileUrl);
            if (!destFile.getParentFile().exists()) { // 判断父目录是否存在,如果父目录不存在，则创建目录
                destFile.getParentFile().mkdir();
            }
            multipartFile.transferTo(destFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件copy
     *
     * @param uploadPath 上传地址
     * @param file
     * @param filrUrl
     */
    protected void doFileCopy(String uploadPath, File file, String filrUrl) {
        try {
            File destFile = new File(uploadPath, filrUrl);
            if (!destFile.isDirectory()) {
                destFile.createNewFile();
            }
            FileUtils.copyFile(file, destFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param uploadPath 上传地址
     * @param oldFileUrl
     */
    public void doDeleteOldFile(String uploadPath, String oldFileUrl) {
        try {
            if (!StringUtils.isEmpty(oldFileUrl)) {
                File file = new File(uploadPath, oldFileUrl);
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片
     *
     * @param uploadPath 上传地址
     * @param oldFileUrl 旧图片地址
     * @param urlPrefix  上传目录前缀
     * @param file
     * @return
     */
    public String uploadFile(String uploadPath, String oldFileUrl, String urlPrefix,
                             MultipartFile file) {
        if (file == null) {
            return null;
        }
        try {
            String fileName = file.getOriginalFilename();
            if (!StringUtils.isEmpty(fileName)) {
                String url = urlPrefix + "/" + DateUtils.getDateSimpleFormat(new Date()) + "/";
                url += fileName;

                doFileUpload(uploadPath, file, url);
                doDeleteOldFile(uploadPath, oldFileUrl);
                return url;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传图片
     *
     * @param uploadPath 上传地址
     * @param oldFileUrl 旧图片地址
     * @param urlPrefix  上传目录前缀
     * @param imageId    图片编号
     * @param iconFile
     * @return
     */
    public String uploadImage(String uploadPath, String oldFileUrl, String urlPrefix, String imageId,
                              MultipartFile iconFile) {
        if (iconFile == null) {
            return null;
        }
        try {
            String fileName = iconFile.getOriginalFilename();
            if (!StringUtils.isEmpty(fileName)) {
                String url = urlPrefix + "/" + imageId + "_" + RandStrUtils.randNumeric(6);
                if (fileName.contains(".")) {
                    url += fileName.substring(fileName.lastIndexOf("."));
                } else {
                    url += ".png";
                }
                doFileUpload(uploadPath, iconFile, url);
                doDeleteOldFile(uploadPath, oldFileUrl);
                return url;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 上传图片
     *
     * @param uploadPath 上传地址
     * @param oldFileUrl 旧图片地址
     * @param urlPrefix  上传目录前缀
     * @param imageId    图片编号
     * @param iconFile
     * @param type       图片类型
     * @return
     */
    public String uploadImage(String uploadPath, String oldFileUrl, String urlPrefix, String imageId,
                              MultipartFile iconFile, String type) {
        if (iconFile == null) {
            return null;
        }
        try {
            String fileName = iconFile.getOriginalFilename();
            if (!StringUtils.isEmpty(fileName)) {
                String url = urlPrefix + "/" + imageId + "_" + RandStrUtils.randNumeric(6);
                if (fileName.contains(".")) {
                    url += fileName.substring(fileName.lastIndexOf("."));
                } else {
                    if (!StringUtils.isTrimEmpty(type)) {
                        url += "." + type;
                    } else {
                        url += ".png";
                    }
                }
                doFileUpload(uploadPath, iconFile, url);
                doDeleteOldFile(uploadPath, oldFileUrl);
                return url;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
