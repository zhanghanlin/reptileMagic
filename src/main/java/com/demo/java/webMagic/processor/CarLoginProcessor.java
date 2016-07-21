package com.demo.java.webMagic.processor;

import com.demo.java.common.vo.LoginModel;
import com.demo.java.webMagic.pipeline.CarPipeline;
import com.demo.java.webMagic.selenium.LoginFactory;
import org.openqa.selenium.Cookie;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Set;

import static com.demo.java.common.Config.DEFAULT_ENCODE;

/**
 * 抓取登陆后的数据
 */
public class CarLoginProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
//        List<String> list = page.getHtml().xpath("//p[@class='my-item-item-content-left-title']").all();
        List<String> list = page.getHtml().xpath("//div[@id='object_list']/span[@class='post_title_in_list']/text").all();
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
        LoginModel model58 = new LoginModel();
        model58.setUrl("https://passport.58.com/login");
        model58.setDomain(".58.com");
        model58.setClickTab("login_tab_orig");
        model58.setForm("submitForm_new");
        model58.setUserNameInput("username_new");
        model58.setPasswordInput("password_new");
        model58.setUserName("username");
        model58.setPassword("password");


        LoginModel modelGJ = new LoginModel();
        modelGJ.setUrl("https://passport.ganji.com/login.php");
        modelGJ.setDomain(".ganji.com");
        modelGJ.setForm("loginform");
        modelGJ.setUserNameInput("login_username");
        modelGJ.setPasswordInput("login_password");
        modelGJ.setUserName("username");
        modelGJ.setPassword("password");

        Spider spider = CarLoginProcessor.getSpider("http://www.ganji.com/vip/my_post_list.php?source=archived_post", 5, modelGJ);
        spider.start();
    }
}
