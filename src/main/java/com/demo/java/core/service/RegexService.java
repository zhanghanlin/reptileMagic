package com.demo.java.core.service;

import com.demo.java.core.dao.RegexMapper;
import com.demo.java.core.entity.Regex;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RegexService {

    @Resource
    RegexMapper regexMapper;

    public Regex get(String id) {
        return regexMapper.get(id);
    }

    public int insert(Regex regex) throws Exception {
        regex.setId(UUID.randomUUID().toString().replace("-", ""));
        return regexMapper.insert(regex);
    }

    public int update(Regex regex) {
        regex.setUpdateTime(new Date());
        return regexMapper.update(regex);
    }

    public List<Regex> list() {
        return regexMapper.selectAll();
    }

    public int delete(String id) {
        return regexMapper.delete(id);
    }
}
