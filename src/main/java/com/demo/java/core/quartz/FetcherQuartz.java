package com.demo.java.core.quartz;

import com.demo.java.common.utils.SpringContextUtil;
import com.demo.java.core.entity.Regex;
import com.demo.java.core.service.RegexService;
import com.demo.java.webMagic.processor.CarProcessor;
import us.codecraft.webmagic.Spider;

/**
 * 动态定时抓取
 */
public class FetcherQuartz {

    RegexService regexService = SpringContextUtil.getBean("regexService");

    /**
     * 根据配置的规则ID进行抓取
     *
     * @param id
     */
    public void fetcher(String id) {
        try {
            Regex regex = regexService.get(id);
            Spider spider = CarProcessor.getSpider(regex);
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
