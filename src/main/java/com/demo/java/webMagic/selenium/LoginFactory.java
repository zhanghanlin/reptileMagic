package com.demo.java.webMagic.selenium;

import com.demo.java.webMagic.model.LoginModel;
import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isNoneBlank(model.getClickTab())) {
            WebElement loginTab = driver.findElement(By.id(model.getClickTab()));
            loginTab.click();
        }
        WebElement formEle = driver.findElement(By.id(model.getForm()));
        WebElement usernameEle = driver.findElement(By.name(model.getUserNameInput()));
        WebElement passwordEle = driver.findElement(By.name(model.getPasswordInput()));
        usernameEle.sendKeys(model.getUserName());
        passwordEle.sendKeys(model.getPassword());
        formEle.submit();
        Set<Cookie> cookies = driver.manage().getCookies();
        driver.close();
        return cookies;
    }
}
