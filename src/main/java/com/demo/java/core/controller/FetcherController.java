package com.demo.java.core.controller;

import com.demo.java.common.utils.ReflectUtils;
import com.demo.java.core.entity.Regex;
import com.demo.java.core.service.RegexService;
import com.demo.java.webMagic.model.LoginModel;
import com.demo.java.webMagic.processor.CarLoginProcessor;
import com.demo.java.webMagic.processor.CarProcessor;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("fetcher")
public class FetcherController {

    static Map<String, Spider> map = new HashedMap();

    @Resource
    RegexService regexService;

    /**
     * 根据Regex配置启动一个爬虫
     *
     * @param id
     * @return
     */
    @RequestMapping("/start/{id}")
    @ResponseBody
    public String fetcherStart(@PathVariable String id) {
        Regex regex = regexService.get(id);
        try {
            Spider spider = CarProcessor.getSpider(regex);
            map.put(regex.getTaskKey(), spider);
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "后台爬取中...";
    }

    /**
     * 根据Regex停止一个爬虫
     *
     * @param id
     * @return
     */
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

    @RequestMapping("/loginInfo")
    public ModelAndView fetcher58Login() {
        ModelAndView modelAndView = new ModelAndView("loginInfo");
        Map<String, String> map = ReflectUtils.getFieldMap(LoginModel.class);
        modelAndView.addObject("map", map);
        return modelAndView;
    }

    @RequestMapping("/loginInfo/save")
    public String fetcher58LoginSave(LoginModel loginModel) {
        Spider spider = CarLoginProcessor.getSpider(5, loginModel);
        spider.start();
        return "redirect:/fetcher/loginInfo";
    }
}
