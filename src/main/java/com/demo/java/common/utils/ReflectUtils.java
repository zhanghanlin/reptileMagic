package com.demo.java.common.utils;

import com.demo.java.common.annotation.Ignore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectUtils {

    /**
     * 获取Bean字段列表
     *
     * @param clz
     * @return
     */
    private static List<Field> fields(Class clz) {
        List<Field> list = new ArrayList<Field>();
        list.addAll(Arrays.asList(clz.getDeclaredFields()));
        if (clz.getGenericSuperclass() != null) {
            list.addAll(Arrays.asList(clz.getSuperclass().getDeclaredFields()));
        }
        return list;
    }

    public static List<String> getFields(Class clz) {
        List<String> list = new ArrayList<>();
        for (Field field : fields(clz)) {
            if (field.isAnnotationPresent(Ignore.class)) {
                continue;
            }
            list.add(field.getName());
        }
        return list;
    }

    /**
     * 获取Class下的所有方法
     * @param clz
     * @return
     */
    public static List<String> getMethod(Class clz) {
        List<String> list = new ArrayList<>();
        Method[] methods = clz.getDeclaredMethods();
        for (Method m : methods) {
            list.add(m.getName());
        }
        return list;
    }
}
