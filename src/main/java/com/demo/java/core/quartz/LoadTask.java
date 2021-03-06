package com.demo.java.core.quartz;

import com.demo.java.common.utils.SpringContextUtil;
import com.demo.java.core.entity.Task;
import com.demo.java.core.service.TaskService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 项目启动加载已存在的定时任务
 */
public class LoadTask {

    final static Logger LOG = LoggerFactory.getLogger(LoadTask.class);

    TaskService taskService = SpringContextUtil.getBean("taskService");

    public void initTask() throws SchedulerException {
        List<Task> list = taskService.selectAll();
        if (list == null || list.isEmpty()) return;
        LOG.info("LoadTask initTask Task Size : {}", list.size());
        ScheduleFactory scheduleFactory = ScheduleFactory.getInstance();
        for (Task task : list) {
            LOG.info("LoadTask initTask createJob {}", task.getTriggerKey());
            scheduleFactory.createJob(task);
        }
    }
}
