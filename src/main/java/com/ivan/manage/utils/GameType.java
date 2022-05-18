package com.ivan.manage.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 游戏类型(及游戏设置)
 */
public enum GameType {


    ;

    private String gameType; //游戏类型
    private String gameName; //游戏名称
    private String alias; //游戏别名
    private int playType; //游戏玩法: 0=组局;1=金币
    private int minUserNum;  //最少玩家数
    private int maxUserNum;  //最大玩家数（默认）
    private String sys;  //游戏模块ID
    private String module;  //解散房间所在模块
    private String adminDirect;  //管理员解散CMD
    private String ownerDirect;  //房主解散CMD
    private boolean isCipher;  //是否加密
    private String aesKey;  //加密KEY

    GameType(String gameType, String gameName, String alias, int playType, int minUserNum, int maxUserNum,
             String sys, String module, String adminDirect, String ownerDirect, boolean isCipher, String aesKey) {
        this.gameType = gameType;
        this.gameName = gameName;
        this.alias = alias;
        this.playType = playType;
        this.minUserNum = minUserNum;
        this.maxUserNum = maxUserNum;
        this.sys = sys;
        this.module = module;
        this.adminDirect = adminDirect;
        this.ownerDirect = ownerDirect;
        this.isCipher = isCipher;
        this.aesKey = aesKey;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getAlias() {
        return alias;
    }

    public int getPlayType() {
        return playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

    public int getMinUserNum() {
        return minUserNum;
    }

    public void setMinUserNum(int minUserNum) {
        this.minUserNum = minUserNum;
    }

    public int getMaxUserNum() {
        return maxUserNum;
    }

    public void setMaxUserNum(int maxUserNum) {
        this.maxUserNum = maxUserNum;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAdminDirect() {
        return adminDirect;
    }

    public void setAdminDirect(String adminDirect) {
        this.adminDirect = adminDirect;
    }

    public String getOwnerDirect() {
        return ownerDirect;
    }

    public void setOwnerDirect(String ownerDirect) {
        this.ownerDirect = ownerDirect;
    }

    public boolean isCipher() {
        return isCipher;
    }

    public void setCipher(boolean cipher) {
        isCipher = cipher;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    /**
     * 获取游戏名称
     *
     * @param gameType 当前游戏编号
     * @return
     */
    public static String getGameName(String gameType) {
        String name = "";
        for (GameType game : GameType.values()) {
            if (game.getGameType().equals(gameType)) {
                name = game.getGameName();
                break;
            }
        }
        return name;
    }

    /**
     * 获取游戏名称(别名)
     *
     * @param gameType 当前游戏编号
     * @return
     */
    public static String getGameAliasName(String gameType) {
        String name = "";
        for (GameType game : GameType.values()) {
            if (game.getGameType().equals(gameType)) {
                name = game.getAlias();
                break;
            }
        }
        return name;
    }

    /**
     * 获取游戏
     *
     * @param gameType 当前游戏编号
     * @return
     */
    public static GameType getGame(String gameType) {
        for (GameType game : GameType.values()) {
            if (game.getGameType().equals(gameType)) {
                return game;
            }
        }
        return null;
    }

    /**
     * 游戏列表
     *
     * @return
     */
    public static JSONArray getGameList(String gameTypes) {
        JSONArray array = new JSONArray();
        if (!StringUtils.isTrimEmpty(gameTypes)) {
            String[] gameTypeStr = gameTypes.split(",");
            for (int index = 0; index < gameTypeStr.length; index++) {
                GameType game = getGame(gameTypeStr[index]);
                if (game != null) {
                    JSONObject obj = new JSONObject();
                    obj.put("gameType", game.getGameType());
                    obj.put("gameName", game.getAlias());
                    obj.put("appid", game.getSys());
                    obj.put("minUserNum", game.getMinUserNum());
                    obj.put("maxUserNum", game.getMaxUserNum());
                    array.add(obj);
                }
            }
        } else {
            for (GameType game : GameType.values()) {
                JSONObject obj = new JSONObject();
                obj.put("gameType", game.getGameType());
                obj.put("gameName", game.getAlias());
                obj.put("appid", game.getSys());
                obj.put("minUserNum", game.getMinUserNum());
                obj.put("maxUserNum", game.getMaxUserNum());
                array.add(obj);
            }
        }
        return array;
    }

    /**
     * 大厅
     *
     * @return
     */
    public static JSONObject getGameHall() {
        JSONObject obj = new JSONObject();
        obj.put("gameType", "yunnan_hall");
        obj.put("gameName", "游戏大厅");
        obj.put("appid", "999");
        obj.put("areaId", "000");
        obj.put("aesKey", "12345678901234567890123456789012");
        return obj;
    }

    /**
     * 大厅_ID
     *
     * @return
     */
    public static String getHallType() {
        return getGameHall().getString("gameType");
    }

    /**
     * 亲友圈
     *
     * @return
     */
    public static JSONObject getGameClub() {
        JSONObject obj = new JSONObject();
        obj.put("gameType", "yunnan_club");
        obj.put("gameName", "亲友圈");
        obj.put("appid", "200");
        obj.put("areaId", "000");
        obj.put("aesKey", "12345678901234567890123456789012");
        return obj;
    }

    /**
     * 亲友圈_ID
     *
     * @return
     */
    public static String getClubType() {
        return getGameClub().getString("gameType");
    }

    /**
     * sdk
     *
     * @return
     */
    public static JSONObject getGameSdk() {
        JSONObject obj = new JSONObject();
        obj.put("gameType", "yunnan_sdk");
        obj.put("gameName", "游戏SDK");
        obj.put("areaId", "000");
        return obj;
    }

    /**
     * SDK_ID
     *
     * @return
     */
    public static String getSDKType() {
        return getGameSdk().getString("gameType");
    }

    /**
     * 游戏及大厅列表
     *
     * @return
     */
    public static JSONArray getGameAndHallList(String gameTypes) {
        JSONArray array = new JSONArray();
        array.add(getGameHall());
        array.add(getGameClub());
        array.addAll(getGameList(gameTypes));
        return array;
    }

    /**
     * 校验游戏人数
     *
     * @param userNum
     * @return
     */
    public int checkUserNum(int userNum) {
        if (userNum > maxUserNum || userNum < minUserNum) {
            return maxUserNum;
        }
        return userNum;
    }

    /**
     * 所有服务列表
     *
     * @return
     */
    public static JSONArray getAllServerList(String gameTypes) {
        JSONArray array = new JSONArray();
        array.add(getGameSdk());
        array.add(getGameHall());
        array.add(getGameClub());
        array.addAll(getGameList(gameTypes));
        return array;
    }
}
