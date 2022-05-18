package com.ivan.manage.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 游戏地区
 */
public enum UserArea {


    ;

    private String areaId;
    private String areaName;

    UserArea(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取地区名称
     *
     * @param areaId 当前地区ID
     * @return
     */
    public static String getAreaName(String areaId) {
        String name = "";
        for (UserArea userArea : UserArea.values()) {
            if (userArea.getAreaId().equals(areaId)) {
                name = userArea.getAreaName();
                break;
            }
        }
        return name;
    }

    /**
     * 获取地区
     *
     * @param areaId 当前地区ID
     * @return
     */
    public static UserArea getArea(String areaId) {
        for (UserArea userArea : UserArea.values()) {
            if (userArea.getAreaId().equals(areaId)) {
                return userArea;
            }
        }
        return null;
    }

    /**
     * 地区列表
     *
     * @param areaId         地区ID(如果为空则返回所有地区)
     * @param hasEmptyOption 是否有空白选项(针对不筛选地区)
     * @return
     */
    public static JSONArray getAreaList(String areaId, boolean hasEmptyOption) {
        JSONArray array = new JSONArray();
        if (!StringUtils.isTrimEmpty(areaId)) {
            String[] areaIdStr = areaId.split(",");
            for (int index = 0; index < areaIdStr.length; index++) {
                UserArea area = getArea(areaIdStr[index]);
                if (area != null) {
                    JSONObject obj = new JSONObject();
                    obj.put("areaId", area.getAreaId());
                    obj.put("areaName", area.getAreaName());
                    array.add(obj);
                }
            }
        } else {
            if (hasEmptyOption) {
                JSONObject obj = new JSONObject();
                obj.put("areaId", "");
                obj.put("areaName", "请选择地区");
                array.add(obj);
            }
            for (UserArea userArea : UserArea.values()) {
                JSONObject obj = new JSONObject();
                obj.put("areaId", userArea.getAreaId());
                obj.put("areaName", userArea.getAreaName());
                array.add(obj);
            }
        }
        return array;
    }

    /**
     * 地区列表
     *
     * @param areaId 地区ID(如果为空则返回所有地区)
     * @return
     */
    public static JSONArray getAreaList(String areaId) {
        JSONArray array = new JSONArray();
        if (!StringUtils.isTrimEmpty(areaId)) {
            String[] areaIdStr = areaId.split(",");
            for (int index = 0; index < areaIdStr.length; index++) {
                UserArea area = getArea(areaIdStr[index]);
                if (area != null) {
                    JSONObject obj = new JSONObject();
                    obj.put("areaId", area.getAreaId());
                    obj.put("areaName", area.getAreaName());
                    array.add(obj);
                }
            }
        } else {
            for (UserArea userArea : UserArea.values()) {
                JSONObject obj = new JSONObject();
                obj.put("areaId", userArea.getAreaId());
                obj.put("areaName", userArea.getAreaName());
                array.add(obj);
            }
        }
        return array;
    }

}
