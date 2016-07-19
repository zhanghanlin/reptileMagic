package com.demo.java.dao;

import java.util.List;

public abstract class AbstractDao<T> {

    /**
     * 根据Id获取对象
     *
     * @param id
     * @return
     */
    abstract T get(String id);

    /**
     * 保存对象
     *
     * @param t
     * @return
     */
    abstract int save(T t);

    /**
     * 更新对象
     *
     * @param t
     * @return
     */
    abstract int update(T t);

    /**
     * 获取所有对象
     *
     * @return
     */
    abstract List<T> list();
}
