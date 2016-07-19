package com.demo.java.dao;

import com.demo.java.model.Task;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TaskDao extends AbstractDao<Task> {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public Task get(String id) {
        String sql = "SELECT * FROM TASK WHERE ID = ?";
        List<Task> list = jdbcTemplate.query(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Task.class));
        if (list == null || list.isEmpty())
            return null;
        return list.get(0);
    }

    @Override
    public int save(Task task) {
        String insert_sql = "INSERT INTO TASK(id,name,task_group,status," +
                "cron_expression,description,bean_class,method_name,method_param)" +
                " values(?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(insert_sql, new Object[]{
                task.getId(),
                task.getName(),
                task.getTaskGroup(),
                task.getStatus(),
                task.getCronExpression(),
                task.getDescription(),
                task.getBeanClass(),
                task.getMethodName(),
                task.getMethodParam()
        });
    }

    @Override
    public List<Task> list() {
        String sql = "SELECT * FROM TASK";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Task.class));
    }

    @Override
    int update(Task task) {
        return 0;
    }

    public int delete(String id) {
        String sql = "DELETE FROM TASK WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }
}
