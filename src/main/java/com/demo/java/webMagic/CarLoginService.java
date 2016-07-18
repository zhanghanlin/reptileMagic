package com.demo.java.webMagic;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Jdk14Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Set;
import java.util.logging.Level;

public class CarLoginService {

    static {
        ((Jdk14Logger) LogFactory.getLog("com.gargoylesoftware.htmlunit")).getLogger().setLevel(Level.OFF);
    }

    public static Set<Cookie> login(String userName, String password) {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        driver.get("https://passport.58.com/login");
        WebElement loginTab = driver.findElement(By.id("login_tab_orig"));
        loginTab.click();
        WebElement formEle = driver.findElement(By.id("submitForm_new"));
        WebElement usernameEle = driver.findElement(By.id("username_new"));
        WebElement passwordEle = driver.findElement(By.id("password_new"));
        usernameEle.sendKeys(userName);
        passwordEle.sendKeys(password);
        formEle.submit();
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.close();
        return cookies;
    }

    public static void main(String[] args) {
        Set<Cookie> set = login("username", "password");
        System.out.println(set);
    }
}
