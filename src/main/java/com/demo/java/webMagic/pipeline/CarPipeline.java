package com.demo.java.webMagic.pipeline;

import com.demo.java.common.utils.SpringContextUtil;
import com.demo.java.core.entity.Car;
import com.demo.java.core.service.CarService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static com.demo.java.common.Config.MAGIC_PAGE_KEY;

/**
 * 抽取结束后,进行处理的部分
 * 它主要用于对抽取结果的保存
 */
public class CarPipeline implements Pipeline {

    CarService carService = SpringContextUtil.getBean("carService");

    @Override
    public void process(ResultItems resultItems, Task task) {
        Car car = resultItems.get(MAGIC_PAGE_KEY);
        if (car != null)
            carService.saveOrUpdate(car);
    }
}
