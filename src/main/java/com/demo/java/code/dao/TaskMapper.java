package com.demo.java.code.dao;

import com.demo.java.code.entity.Task;

import java.util.List;

public interface TaskMapper {

    int delete(String id);

    int insert(Task record);

    Task get(String id);

    int update(Task record);

    List<Task> selectAll();
}