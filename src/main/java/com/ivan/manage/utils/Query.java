package com.ivan.manage.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    // 起始游标
    private int offset;
    // 每页条数
    private int limit;
    // 当前页数
    private int curPageNum;
    // 总条数
    private int totalRows;
    // 总页数
    private int totalPage;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        this.offset = Integer.parseInt(params.get("offset").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", offset);
        this.put("page", offset / limit + 1);
        this.put("limit", limit);
    }

    public Query(Map<String, Object> params, int curPageNum, int totalRows) {
        this.putAll(params);
        this.limit = 10; //默认每页10条数
        this.curPageNum = curPageNum;
        this.totalRows = totalRows;

        // 分页参数
        this.offset = (curPageNum - 1) * limit;
        this.put("offset", offset);
        this.put("page", curPageNum);
        this.put("limit", limit);
    }

    public Query(Map<String, Object> params, int limit, int curPageNum, int totalRows) {
        this.putAll(params);
        this.limit = limit; //默认每页条数
        this.curPageNum = curPageNum;
        this.totalRows = totalRows;

        // 分页参数
        this.offset = (curPageNum - 1) * limit;
        this.put("offset", offset);
        this.put("page", curPageNum);
        this.put("limit", limit);
    }

    @Override
    public Query put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.put("offset", offset);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurPageNum() {
        return curPageNum;
    }

    public void setCurPageNum(int curPageNum) {
        this.curPageNum = curPageNum;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPage() {
        totalPage = (totalRows / limit);
        if ((totalRows % limit) != 0) {
            totalPage += 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取分页信息
     *
     * @return
     */
    public Map<String, Object> getPageInfo() {
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("limit", limit);
        pageInfo.put("curPageNum", curPageNum);
        pageInfo.put("totalRows", totalRows);
        pageInfo.put("totalPage", getTotalPage());
        return pageInfo;
    }
}
