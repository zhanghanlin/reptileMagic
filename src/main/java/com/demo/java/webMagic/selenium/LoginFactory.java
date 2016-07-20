package com.demo.java.webMagic.selenium;

import com.demo.java.common.vo.LoginModel;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Jdk14Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Set;
import java.util.logging.Level;

/**
 * 使用selenium模拟登陆
 */
public class LoginFactory {

    static {
        ((Jdk14Logger) LogFactory.getLog("com.gargoylesoftware.htmlunit")).getLogger().setLevel(Level.OFF);
    }

    public static Set<Cookie> login(LoginModel model) {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        driver.get(model.getUrl());
        WebElement loginTab = driver.findElement(By.id(model.getClickTab()));
        if (loginTab != null) {
            loginTab.click();
        }
        WebElement formEle = driver.findElement(By.id(model.getForm()));
        WebElement usernameEle = driver.findElement(By.id(model.getUserNameInput()));
        WebElement passwordEle = driver.findElement(By.id(model.getPasswordInput()));
//        driver.get("https://passport.58.com/login");
//        WebElement loginTab = driver.findElement(By.id("login_tab_orig"));
//        loginTab.click();
//        WebElement formEle = driver.findElement(By.id("submitForm_new"));
//        WebElement usernameEle = driver.findElement(By.id("username_new"));
//        WebElement passwordEle = driver.findElement(By.id("password_new"));
        usernameEle.sendKeys(model.getUserName());
        passwordEle.sendKeys(model.getPassword());
        formEle.submit();
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.close();
        return cookies;
    }
}
