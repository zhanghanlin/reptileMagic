package com.demo.java.common.utils;

import com.demo.java.common.annotation.Ignore;
import com.demo.java.common.annotation.Mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 反射工具类
 */
public class ReflectUtils {

    /**
     * 获取Bean字段列表
     *
     * @param clz
     * @return
     */
    private static List<Field> fields(Class clz) {
        List<Field> list = new ArrayList<>();
        list.addAll(Arrays.asList(clz.getDeclaredFields()));
        if (clz.getGenericSuperclass() != null) {
            list.addAll(Arrays.asList(clz.getSuperclass().getDeclaredFields()));
        }
        return list;
    }

    /**
     * 获取字段名和MappingName的Map
     *
     * @param clz
     * @return
     */
    public static Map<String, String> getFieldMap(Class clz) {
        Map<String, String> map = new HashMap();
        for (Field field : fields(clz)) {
            //过滤静态字段
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (field.isAnnotationPresent(Ignore.class)) {
                continue;
            }
            String mapVal = field.getName();
            if (field.isAnnotationPresent(Mapping.class)) {
                mapVal = field.getAnnotation(Mapping.class).name();
            }
            map.put(field.getName(), mapVal);
        }
        return map;
    }

    /**
     * 获取Class下的所有方法
     *
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
