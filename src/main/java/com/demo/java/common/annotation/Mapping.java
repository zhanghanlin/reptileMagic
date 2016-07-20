package com.demo.java.common.annotation;

import java.lang.annotation.*;

/**
 * 添加规则页面使用
 * 对应字段名称与显示给用户的映射关系
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mapping {

    /**
     * 字段映射说明
     *
     * @return
     */
    String name() default "fieldName";
}
