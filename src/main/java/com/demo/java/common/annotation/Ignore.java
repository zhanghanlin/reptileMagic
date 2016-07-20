package com.demo.java.common.annotation;

import java.lang.annotation.*;

/**
 * 添加规则页面使用
 * 用于过滤不需要用户设置的字段
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ignore {
}
