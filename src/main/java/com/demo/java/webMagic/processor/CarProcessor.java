package com.demo.java.webMagic.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.java.common.utils.PatternUtils;
import com.demo.java.code.entity.Car;
import com.demo.java.code.entity.Regex;
import com.demo.java.webMagic.pipeline.CarPipeline;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

import static com.demo.java.common.utils.Config.*;

public class CarProcessor implements PageProcessor {

    private Site site;
    private Regex regex;

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(regex.getDetailRegex()).match()
                && regex.getIsData() == 1) {
            if (StringUtils.isNoneBlank(regex.getIgnoreKey())) {
                //如果存在则不抓取该页面
                if (page.getHtml().$(regex.getIgnoreKey()).all().size() > 0) {
                    return;
                }
            }
            JSONObject object = new JSONObject();
            //处理自定义字段信息
            for (Map.Entry<String, Object> entry : regex.getJSONData().entrySet()) {
                JSONObject jsonVal = (JSONObject) entry.getValue();
                //字段CSS选择器
                String dom = jsonVal.getString("dom");
                String val = page.getHtml().$(dom, "text").toString();
                //如果Val为空则跳过该字段
                if (StringUtils.isBlank(val)) val = STRING_EMPTY;
                //字段类型,用于判断是否需要特殊处理
                String type = jsonVal.getString("type");
                //默认按照String处理
                val = parseType(val, type);
                //特殊处理器
                String handle = jsonVal.getString("handle");
                //需要处理的值,如替换则该字段为需要替换的值
                String handle_p = jsonVal.getString("handle_p");
                val = handle(val, handle, handle_p);
                object.put(entry.getKey(), val.trim());
            }
            //将JSON数据转化为Bean对象
            Car car = JSON.toJavaObject(object, Car.class);
            if (car != null && StringUtils.isNoneBlank(car.getCarName())) {
                String url = page.getUrl().toString();
                //使用URL中最后一段数字作为ID
                car.setId(regex.getTaskKey() + "_" + PatternUtils.matchInteger(url, 1));
                car.setUrl(url);
                car.setSource(regex.getTaskKey());
                page.putField(MAGIC_PAGE_KEY, car);
            }
        } else {
            page.setSkip(true);
            page.addTargetRequests(page.getHtml().links().regex(regex.getListRegex()).all());
            page.addTargetRequests(page.getHtml().links().regex(regex.getDetailRegex()).all());
        }
    }

    /**
     * 根据Type处理Val
     *
     * @param val
     * @param type
     * @return
     */
    String parseType(String val, String type) {
        //判断是否是数字类型
        if (StringUtils.isBlank(type)) {
            return val;
        }
        if (type.equals("Integer")) {
            val = PatternUtils.matchNum(val);
        }
        return val;
    }

    /**
     * 处理器
     *
     * @param val
     * @param handle
     * @param handle_p
     * @return
     */
    String handle(String val, String handle, String handle_p) {
        //判断处理器是否为空
        if (StringUtils.isNoneBlank(handle)) {
            if (handle.equals("replace")) {
                String[] hps = handle_p.split("\\|");
                for (String h : hps) {
                    val = val.replace(h, "");
                }
            }
        }
        return val;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public CarProcessor(Regex regex) {
        this.regex = regex;
        this.site = Site.me();
        this.site.setCharset(DEFAULT_ENCODE); //编码
        this.site.setRetryTimes(regex.getRetryTime());   //重试次数
        this.site.setCycleRetryTimes(regex.getCycleRetryTime());  //循环重试次数,机制会将下载失败的url重新放入队列尾部重试，直到达到重试次数，以保证不因为某些网络原因漏抓页面
        this.site.setSleepTime(regex.getSleepTime()); //抓取间隔
    }

    public static Spider getSpider(Regex regex) {
        CarProcessor carProcessor = new CarProcessor(regex);
        Spider spider = Spider.create(carProcessor);
        spider.addPipeline(new CarPipeline());
        spider.addUrl(regex.getUrl());
        spider.thread(regex.getThread());
        return spider;
    }
}
