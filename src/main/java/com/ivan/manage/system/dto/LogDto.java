package com.ivan.manage.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ivan.manage.utils.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class LogDto extends DTO {
    private static final long serialVersionUID = -1915987405639463530L;

    private Long id;
    private Long userId;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

}
