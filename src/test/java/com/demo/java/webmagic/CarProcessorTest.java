package com.demo.java.webmagic;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.BaseTest;
import com.demo.java.core.entity.Car;
import com.demo.java.core.entity.Regex;
import com.demo.java.core.service.RegexService;
import com.demo.java.webMagic.processor.CarProcessor;
import org.junit.Test;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

import static com.demo.java.common.Config.MAGIC_PAGE_KEY;

public class CarProcessorTest extends BaseTest {

    @Resource
    RegexService regexService;

    @Test
    public void runCar() {
        Regex regex = regexService.get("b788726b855b4b7ba3a0b972315adc15");
        if (regex == null) return;
        CarProcessor carProcessor = new CarProcessor(regex);
        Spider spider = Spider.create(carProcessor);
        spider.addPipeline(new ConsolePipeline());
        spider.addUrl(regex.getUrl());
        spider.thread(regex.getThread());
        spider.run();
    }


    class ConsolePipeline implements Pipeline {

        @Override
        public void process(ResultItems resultItems, Task task) {
            Car car = resultItems.get(MAGIC_PAGE_KEY);
            System.out.println(JSONObject.toJSONString(car));
        }
    }
}