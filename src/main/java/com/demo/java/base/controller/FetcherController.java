package com.demo.java.base.controller;

import com.demo.java.base.entity.Regex;
import com.demo.java.base.service.RegexService;
import com.demo.java.webMagic.processor.CarProcessor;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("fetcher")
public class FetcherController {

    static Map<String, Spider> map = new HashedMap();

    @Resource
    RegexService regexService;

    @RequestMapping("/start/{id}")
    @ResponseBody
    public void fetcherStart(@PathVariable String id) {
        Regex regex = regexService.get(id);
        try {
            Spider spider = CarProcessor.getSpider(regex);
            map.put(regex.getTaskKey(), spider);
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/stop/{id}")
    @ResponseBody
    public String fetcherStop(@PathVariable String id) {
        Regex regex = regexService.get(id);
        Spider spider = map.get(regex.getTaskKey());
        if (spider == null) {
            return "没有运行的爬取任务";
        }
        spider.stop();
        map.remove(regex.getTaskKey());
        return "停止爬取";
    }
}
