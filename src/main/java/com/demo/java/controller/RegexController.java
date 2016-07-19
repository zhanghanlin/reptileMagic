package com.demo.java.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.common.utils.ReflectUtils;
import com.demo.java.model.Car;
import com.demo.java.model.Regex;
import com.demo.java.service.RegexService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("regex")
public class RegexController {

    @Resource
    RegexService regexService;

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

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        try {
            regexService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/regex/list";
    }

    @RequestMapping("/save")
    public String save(Regex regex) {
        try {
            regex.setData(StringEscapeUtils.unescapeHtml4(regex.getData()));
            if (StringUtils.isBlank(regex.getId())) {
                regex.setId(UUID.randomUUID().toString().replace("-", ""));
                regexService.save(regex);
            } else {
                regexService.update(regex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/regex/list";
    }

    @RequestMapping("/list")
    public ModelAndView list() {
        List<Regex> list = regexService.list();
        ModelAndView modelAndView = new ModelAndView("regexList");
        modelAndView.addObject("list", list);
        return modelAndView;
    }
}
