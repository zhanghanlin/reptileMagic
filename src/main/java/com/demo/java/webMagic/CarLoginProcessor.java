package com.demo.java.webMagic;

import com.demo.java.model.LoginModel;
import org.openqa.selenium.Cookie;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Set;

import static com.demo.java.common.utils.Config.DEFAULT_ENCODE;

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

    public CarLoginProcessor(LoginModel model) {
        System.out.println("CarProcessor(Regex regex) init");
        this.site = Site.me();
        this.site.setCharset(DEFAULT_ENCODE); //编码
        this.site.setRetryTimes(3);   //重试次数
        this.site.setCycleRetryTimes(3);  //循环重试次数,机制会将下载失败的url重新放入队列尾部重试，直到达到重试次数，以保证不因为某些网络原因漏抓页面
        this.site.setSleepTime(100); //抓取间隔
        site.setDomain(model.getDomain());
        Set<Cookie> cookies = LoginFactory.login(model);
        for (Cookie cookie : cookies) {
            site.addCookie(cookie.getDomain(), cookie.getName(), cookie.getValue());
        }
    }

    public static Spider getSpider(String url, int thread, LoginModel model) {
        CarLoginProcessor carLoginProcessor = new CarLoginProcessor(model);
        Spider spider = Spider.create(carLoginProcessor);
        spider.addPipeline(new CarPipeline());
        spider.addUrl(url);
        spider.thread(thread);
        return spider;
    }

    public static void main(String[] args) {
        LoginModel model = new LoginModel();
        model.setUrl("https://passport.58.com/login");
        model.setDomain(".58.com");
        model.setClickTab("login_tab_orig");
        model.setForm("submitForm_new");
        model.setUserNameInput("username_new");
        model.setPasswordInput("password_new");
        model.setUserName("username");
        model.setPassword("password");
        Spider spider = Spider.create(new CarLoginProcessor(model));
        spider.addPipeline(new ConsolePipeline());
        spider.addUrl("http://my.58.com/infoall?sys=my");
        spider.thread(5);
        spider.start();
    }
}
