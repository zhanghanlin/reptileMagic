package com.demo.java.dao;

import com.demo.java.model.Regex;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 抓取规则Dao
 */
@Repository
public class RegexDao extends AbstractDao<Regex> {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public Regex get(String id) {
        String sql = "SELECT * FROM REGEX WHERE ID = ?";
        List<Regex> list = jdbcTemplate.query(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(Regex.class));
        if (list == null || list.isEmpty())
            return null;
        return list.get(0);
    }

    @Override
    public int save(Regex regex) {
        String sql = "INSERT INTO REGEX (id, name, url, list_regex," +
                "detail_regex, thread, ignore_key, data, task_key, is_data,retry_time,cycle_retry_time,sleep_time)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{regex.getId(),
                regex.getName(),
                regex.getUrl(),
                regex.getListRegex(),
                regex.getDetailRegex(),
                regex.getThread(),
                regex.getIgnoreKey(),
                regex.getData(),
                regex.getTaskKey(),
                regex.getIsData(),
                regex.getRetryTime(),
                regex.getCycleRetryTime(),
                regex.getSleepTime()
        });
    }

    @Override
    public int update(Regex regex) {
        String sql = "UPDATE REGEX " +
                "SET name       = ?, url = ?, list_regex = ?," +
                "  detail_regex = ?, thread = ?, ignore_key = ?, data = ?," +
                "  task_key     = ?, is_data = ?, update_time = NOW(), " +
                "  retry_time   = ?, cycle_retry_time = ?, sleep_time = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[]{
                regex.getName(),
                regex.getUrl(),
                regex.getListRegex(),
                regex.getDetailRegex(),
                regex.getThread(),
                regex.getIgnoreKey(),
                regex.getData(),
                regex.getTaskKey(),
                regex.getIsData(),
                regex.getRetryTime(),
                regex.getCycleRetryTime(),
                regex.getSleepTime(),
                regex.getId()
        });
    }

    @Override
    public List<Regex> list() {
        String sql = "SELECT * FROM REGEX";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Regex.class));
    }

    public int delete(String id) {
        String sql = "DELETE FROM REGEX WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }
}