package com.demo.java.common;

/**
 * 全局常量配置
 */
public interface Config {

    /**
     * String Empty Val
     */
    String STRING_EMPTY = "";

    /**
     * 默认编码
     */
    String DEFAULT_ENCODE = "UTF-8";

    /**
     * WebMagic存入Page对象的Key
     */
    String MAGIC_PAGE_KEY = "Object";

    /**
     * 定时任务将数据PUT至JobDataMap的Key
     */
    String JOB_DATA_KEY = "scheduleJob";
}
