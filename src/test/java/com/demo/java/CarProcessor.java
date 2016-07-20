package com.demo.java;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import static com.demo.java.common.Config.DEFAULT_ENCODE;

public class CarProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        page.putField("name",
                page.getHtml().
                        xpath("//p[@class='loan-price-main-num']/outerHtml()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public CarProcessor() {
        this.site = Site.me();
        this.site.setCharset(DEFAULT_ENCODE); //编码
        this.site.setRetryTimes(3);   //重试次数
        this.site.setCycleRetryTimes(3);  //循环重试次数,机制会将下载失败的url重新放入队列尾部重试，直到达到重试次数，以保证不因为某些网络原因漏抓页面
        this.site.setSleepTime(100); //抓取间隔
    }

    public static Spider getSpider() {
        CarProcessor carProcessor = new CarProcessor();
        Spider spider = Spider.create(carProcessor);
        spider.addPipeline(new ConsolePipeline());
        spider.addUrl("http://m.58.com/bj/ershouche/25765414784046x.shtml");
        spider.thread(5);
        return spider;
    }

    public static void main(String[] args) {
        getSpider().start();
    }
}
