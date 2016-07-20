package com.demo.java.code.dao;

import com.demo.java.code.entity.Regex;

import java.util.List;

public interface RegexMapper {

    int delete(String id);

    int insert(Regex record);

    Regex get(String id);

    int update(Regex record);

    List<Regex> selectAll();
}