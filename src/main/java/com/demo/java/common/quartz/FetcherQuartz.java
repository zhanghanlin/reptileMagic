package com.demo.java.common.quartz;

import com.demo.java.common.utils.SpringContextUtil;
import com.demo.java.model.Regex;
import com.demo.java.service.RegexService;
import com.demo.java.webMagic.CarProcessor;
import us.codecraft.webmagic.Spider;

/**
 * 动态定时抓取
 */
public class FetcherQuartz {

    RegexService regexService = SpringContextUtil.getBean("regexService", RegexService.class);

    /**
     * 根据配置的规则ID进行抓取
     *
     * @param id
     */
    public void fetcher(String id) {
        try {
            Regex regex = regexService.get(id);
            Spider spider = CarProcessor.getSpider(regex);
//            SpiderMonitor.instance().register(spider);
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
