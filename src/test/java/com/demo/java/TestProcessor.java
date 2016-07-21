package com.demo.java;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import static com.demo.java.common.Config.DEFAULT_ENCODE;

/**
 * 爬虫
 */
public class TestProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        System.out.println("test");
    }


    @Override
    public Site getSite() {
        return site;
    }

    public TestProcessor() {
        this.site = Site.me();
        this.site.setCharset(DEFAULT_ENCODE); //编码
        this.site.setRetryTimes(3);   //重试次数
        this.site.setCycleRetryTimes(3);  //循环重试次数,机制会将下载失败的url重新放入队列尾部重试，直到达到重试次数，以保证不因为某些网络原因漏抓页面
        this.site.setSleepTime(100); //抓取间隔
    }

    public static void main(String[] args) {
        TestProcessor testProcessor = new TestProcessor();
        Spider spider = Spider.create(testProcessor);
        spider.addPipeline(new ConsolePipeline());
        spider.addUrl("http://bj.ganji.com/ershouche/26740499732025x.htm");
        spider.thread(5);
        spider.run();
    }
}
