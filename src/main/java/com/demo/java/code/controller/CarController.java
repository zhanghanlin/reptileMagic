package com.demo.java.code.controller;

import com.demo.java.common.vo.TablePage;
import com.demo.java.code.entity.Car;
import com.demo.java.code.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("car")
public class CarController {

    final static Logger LOG = LoggerFactory.getLogger(CarController.class);

    @Resource
    CarService carService;

    @RequestMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("carList");
    }

    @RequestMapping("/api/list")
    @ResponseBody
    public TablePage<Car> apiList(@RequestParam(defaultValue = "1", required = false) int start,
                                  @RequestParam(defaultValue = "10", required = false) int length,
                                  @RequestParam(defaultValue = "1", required = false) int draw) {
        try {
            TablePage<Car> page = carService.selectByPage(start, length);
            page.setDraw(draw);
            return page;
        } catch (Exception e) {
            LOG.error("search error : {}", e.getMessage(), e);
            return new TablePage<>(draw, 0, 0, null);
        }
    }
}
