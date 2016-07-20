package com.demo.java.core.dao;

import com.demo.java.core.entity.Regex;

import java.util.List;

public interface RegexMapper {

    int delete(String id);

    int insert(Regex record);

    Regex get(String id);

    int update(Regex record);

    List<Regex> selectAll();
}