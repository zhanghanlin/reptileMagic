package com.demo.java.core.entity;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.core.base.BaseEntity;

/**
 * 抓取任务规则Bean
 */
public class Regex extends BaseEntity {

    private static final long serialVersionUID = 4251669716871559150L;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 注入种子地址
     */
    private String url;

    /**
     * 列表地址正则
     */
    private String listRegex;

    /**
     * 详情地址正则
     */
    private String detailRegex;

    /**
     * 线程数量
     */
    private Integer thread;

    /**
     * 抓取时如果页面存在该选择器的元素则忽略该页面
     */
    private String ignoreKey;

    /**
     * 自定义字段JSON格式
     */
    private String data;

    /**
     * 任务Key唯一
     */
    private String taskKey;

    /**
     * 是否需要设置Data数据
     */
    private Integer isData;

    /**
     * 重试次数
     */
    private Integer retryTime;

    /**
     * 循环重试次数
     * 该机制会将下载失败的url重新放入队列尾部重试直到达到重试次数
     * 以保证不因为某些网络原因漏抓页面
     */
    private Integer cycleRetryTime;

    /**
     * 抓取间隔
     */
    private Integer sleepTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getListRegex() {
        return listRegex;
    }

    public void setListRegex(String listRegex) {
        this.listRegex = listRegex == null ? null : listRegex.trim();
    }

    public String getDetailRegex() {
        return detailRegex;
    }

    public void setDetailRegex(String detailRegex) {
        this.detailRegex = detailRegex == null ? null : detailRegex.trim();
    }

    public Integer getThread() {
        return thread;
    }

    public void setThread(Integer thread) {
        this.thread = thread;
    }

    public String getIgnoreKey() {
        return ignoreKey;
    }

    public void setIgnoreKey(String ignoreKey) {
        this.ignoreKey = ignoreKey == null ? null : ignoreKey.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey == null ? null : taskKey.trim();
    }

    public Integer getIsData() {
        return isData;
    }

    public void setIsData(Integer isData) {
        this.isData = isData;
    }

    public Integer getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Integer retryTime) {
        this.retryTime = retryTime;
    }

    public Integer getCycleRetryTime() {
        return cycleRetryTime;
    }

    public void setCycleRetryTime(Integer cycleRetryTime) {
        this.cycleRetryTime = cycleRetryTime;
    }

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public JSONObject getJSONData() {
        return JSONObject.parseObject(data);
    }
}