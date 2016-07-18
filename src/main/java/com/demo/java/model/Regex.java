package com.demo.java.model;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Date;

/**
 * 抓取任务规则Bean
 */
public class Regex {
    private String id;
    private String name;    //任务名称
    private String url;    //注入种子地址
    private String listRegex;   //列表地址正则
    private String detailRegex;   //详情地址正则
    private int thread; //线程数量
    private String ignoreKey;   //抓取时如果页面存在该选择器的元素则忽略该页面
    private String data;    //自定义字段JSON格式
    private String taskKey; //任务Key唯一
    private int retryTime;  //重试次数
    private int cycleRetryTime; //循环重试次数,该机制会将下载失败的url重新放入队列尾部重试，直到达到重试次数，以保证不因为某些网络原因漏抓页面
    private int sleepTime;  //抓取间隔
    private int isData; //是否需要设置Data数据
    private Date createTime;
    private Date updateTime;

    /**
     * 返回JSON格式data数据
     *
     * @return
     */
    public JSONObject getJSONData() {
        return JSONObject.parseObject(StringEscapeUtils.unescapeHtml4(data));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getListRegex() {
        return listRegex;
    }

    public void setListRegex(String listRegex) {
        this.listRegex = listRegex;
    }

    public String getDetailRegex() {
        return detailRegex;
    }

    public void setDetailRegex(String detailRegex) {
        this.detailRegex = detailRegex;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public String getIgnoreKey() {
        return ignoreKey;
    }

    public void setIgnoreKey(String ignoreKey) {
        this.ignoreKey = ignoreKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public int getCycleRetryTime() {
        return cycleRetryTime;
    }

    public void setCycleRetryTime(int cycleRetryTime) {
        this.cycleRetryTime = cycleRetryTime;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getIsData() {
        return isData;
    }

    public void setIsData(int isData) {
        this.isData = isData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
