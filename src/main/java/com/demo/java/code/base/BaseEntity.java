package com.demo.java.code.base;

import com.demo.java.common.annotation.Ignore;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable{

    private static final long serialVersionUID = 164941249672040634L;

    @Ignore
    private String id;

    @Ignore
    private Date createTime;

    @Ignore
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
