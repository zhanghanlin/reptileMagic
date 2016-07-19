package com.demo.java.common.annotation;

import java.lang.annotation.*;

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
