package com.demo.java.core.controller;

import com.demo.java.common.vo.TablePage;
import com.demo.java.core.entity.Car;
import com.demo.java.core.service.CarService;
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

    /**
     * 车源列表页
     *
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("carList");
    }

    /**
     * 车源列表页使用
     * 分页获取数据接口
     *
     * @param start  开始位置
     * @param length 每页数据量
     * @param draw
     * @return
     */
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

    /**
     * 清空抓取的数据
     *
     * @return
     */
    @RequestMapping("/api/delete")
    @ResponseBody
    public String deleteAll() {
        carService.delete();
        return "OK";
    }
}
