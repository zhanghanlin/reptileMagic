package com.demo.java.webMagic;

import org.openqa.selenium.Cookie;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Set;

/**
 * 模拟登陆Cookie
 */
public class CarLoginProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
        List<String> list = page.getHtml().xpath("//p[@class='my-item-item-content-left-title']").all();
        for (String s : list) {
            System.out.println(s);
        }
    }


    @Override
    public Site getSite() {
        return site;
    }

    public CarLoginProcessor() {
        System.out.println("CarProcessor(Regex regex) init");
        this.site = Site.me()
                .setRetryTimes(3)   //重试次数
                .setCycleRetryTimes(3)  //循环重试次数,机制会将下载失败的url重新放入队列尾部重试，直到达到重试次数，以保证不因为某些网络原因漏抓页面
                .setSleepTime(100); //抓取间隔
        site.setDomain(".58.com");
        Set<Cookie> cookies = CarLoginService.login("username", "password");
        for (Cookie cookie : cookies) {
            site.addCookie(cookie.getDomain(), cookie.getName(), cookie.getValue());
        }
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new CarLoginProcessor());
        spider.addPipeline(new ConsolePipeline());
        spider.addUrl("http://my.58.com/infoall?sys=my");
        spider.thread(5);
        spider.start();
    }
}
