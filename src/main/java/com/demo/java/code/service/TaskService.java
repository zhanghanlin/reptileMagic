package com.demo.java.code.service;

import com.demo.java.code.dao.TaskMapper;
import com.demo.java.code.entity.Task;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TaskService {

    @Resource
    TaskMapper taskMapper;

    public Task get(String id) {
        return taskMapper.get(id);
    }

    public int insert(Task task) {
        task.setId(UUID.randomUUID().toString().replace("-", ""));
        return taskMapper.insert(task);
    }

    public List<Task> selectAll() {
        return taskMapper.selectAll();
    }

    public int delete(String id) {
        return taskMapper.delete(id);
    }

    public Map<String, Task> map() {
        Map<String, Task> map = new HashedMap();
        for (Task task : selectAll()) {
            map.put(task.getTriggerKey(), task);
        }
        return map;
    }
}
