package com.ivan.manage.system.dto;

import com.ivan.manage.utils.DTO;

import java.util.Date;

public class UserDto extends DTO {
    private static final long serialVersionUID = 4961614309496307645L;

    private Integer userId;
    private String username;// 用户名
    private String name;//名称
    private String password;// 密码
    private Integer status;// 状态 0:禁用，1:正常
    private String authIp;//授权IP
    private Date createTime;// 创建时间
    private Date modifiedTime;// 修改时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthIp() {
        return authIp;
    }

    public void setAuthIp(String authIp) {
        this.authIp = authIp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

//    private String areaName; //区域名称
//
//    public String getAreaName() {
//        if (!StringUtils.isBlank(areaId)) {
//            String[] areaIdStr = areaId.split(",");
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < areaIdStr.length; i++) {
//                sb.append(UserArea.getAreaName(areaIdStr[i])).append("\n");
//            }
//            areaName = sb.toString();
//        } else {
//            areaName = "全部地区";
//        }
//        return areaName;
//    }
//
//    private String gameName; //游戏
//
//    public String getGameName() {
//        if (!StringUtils.isTrimEmpty(gameTypes)) {
//            String[] gameTypeStr = gameTypes.split(",");
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < gameTypeStr.length; i++) {
//                GameType gameType = GameType.getGame(gameTypeStr[i]);
//                if (gameType != null) {
//                    sb.append(gameType.getAlias()).append("\n");
//                }
//            }
//            gameName = sb.toString();
//        } else {
//            gameName = "全部游戏";
//        }
//        return gameName;
//    }
}
