package com.demo.java.core.quartz;

import com.demo.java.core.entity.Task;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static com.demo.java.common.Config.JOB_DATA_KEY;

/**
 * 执行计划任务
 */
public class QuartzJobFactory implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Task task = (Task) context.getMergedJobDataMap().get(JOB_DATA_KEY);
        TaskUtils.invokeMethod(task);
    }
}
