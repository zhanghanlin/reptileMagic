package com.demo.java.webMagic.model;

import com.demo.java.common.annotation.Mapping;

public class LoginModel {

    @Mapping(name = "登陆URL")
    private String url;
    private String domain;
    @Mapping(name = "点击元素")
    private String clickTab;
    @Mapping(name = "Form表单")
    private String form;
    @Mapping(name = "用户名输入框")
    private String userNameInput;
    @Mapping(name = "密码输入框")
    private String passwordInput;
    @Mapping(name = "用户名")
    private String userName;
    @Mapping(name = "密码")
    private String password;
    private String dataUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getClickTab() {
        return clickTab;
    }

    public void setClickTab(String clickTab) {
        this.clickTab = clickTab;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getUserNameInput() {
        return userNameInput;
    }

    public void setUserNameInput(String userNameInput) {
        this.userNameInput = userNameInput;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
