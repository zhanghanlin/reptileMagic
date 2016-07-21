package com.demo.java.core.base;

import com.demo.java.common.annotation.Ignore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 164941249672040634L;

    /**
     * 主键ID
     */
    @Ignore
    private String id;

    /**
     * 创建时间
     */
    @Ignore
    private Date createTime;

    /**
     * 更新时间
     */
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
