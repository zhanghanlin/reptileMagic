package com.demo.java.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.core.entity.Car;
import com.demo.java.core.entity.Regex;
import com.demo.java.core.service.RegexService;
import com.demo.java.common.utils.ReflectUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("regex")
public class RegexController {

    @Resource
    RegexService regexService;

    /**
     * 抓取规则配置页面
     * @param id
     * @return
     */
    @RequestMapping("/input/{id}")
    public ModelAndView input(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("regexInput");
        Regex regex = new Regex();
        JSONObject data = new JSONObject();
        if (!id.equals("0")) {
            regex = regexService.get(id);
            data = regex.getJSONData();
        }
        Map<String, String> map = ReflectUtils.getFieldMap(Car.class);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            JSONObject object = data.getJSONObject(entry.getKey());
            if (object == null) object = new JSONObject();
            object.put("name", entry.getValue());
            data.put(entry.getKey(), object);
        }
        modelAndView.addObject("regex", regex);
        modelAndView.addObject("data", data);
        return modelAndView;
    }

    /**
     * 删除一条抓取规则
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        try {
            regexService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/regex/list";
    }

    /**
     * 保存一条抓取规则
     * @param regex
     * @return
     */
    @RequestMapping("/save")
    public String save(Regex regex) {
        try {
            regex.setData(StringEscapeUtils.unescapeHtml4(regex.getData()));
            if (StringUtils.isBlank(regex.getId())) {
                regexService.insert(regex);
            } else {
                regexService.update(regex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/regex/list";
    }

    /**
     * 抓取规则列表页
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        List<Regex> list = regexService.list();
        ModelAndView modelAndView = new ModelAndView("regexList");
        modelAndView.addObject("list", list);
        return modelAndView;
    }
}
