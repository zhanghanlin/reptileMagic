package com.demo.java.core.entity;

import com.demo.java.core.base.BaseEntity;
import com.demo.java.common.annotation.Ignore;
import com.demo.java.common.annotation.Mapping;

public class Car extends BaseEntity {

    private static final long serialVersionUID = 7084703935188104849L;

    @Mapping(name = "名称")
    private String carName;
    @Mapping(name = "价格")
    private String price;
    @Mapping(name = "上牌时间")
    private String onTime;
    @Mapping(name = "里程数")
    private String mileage;
    @Mapping(name = "变速箱类型")
    private String speedCase;
    @Mapping(name = "年检到期")
    private String inspectExpire;
    @Mapping(name = "保险到期")
    private String safeExpire;
    @Mapping(name = "事故")
    private String accident;
    @Mapping(name = "联系人")
    private String userName;
    @Mapping(name = "电话")
    private String phone;
    /**
     * 原始URL
     */
    @Ignore
    private String url;
    @Mapping(name = "地址")
    private String address;

    /**
     * 来源标识,默认使用抓取任务中唯一任务标识
     */
    @Ignore
    private String source;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime == null ? null : onTime.trim();
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage == null ? null : mileage.trim();
    }

    public String getSpeedCase() {
        return speedCase;
    }

    public void setSpeedCase(String speedCase) {
        this.speedCase = speedCase == null ? null : speedCase.trim();
    }

    public String getInspectExpire() {
        return inspectExpire;
    }

    public void setInspectExpire(String inspectExpire) {
        this.inspectExpire = inspectExpire == null ? null : inspectExpire.trim();
    }

    public String getSafeExpire() {
        return safeExpire;
    }

    public void setSafeExpire(String safeExpire) {
        this.safeExpire = safeExpire == null ? null : safeExpire.trim();
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident == null ? null : accident.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}