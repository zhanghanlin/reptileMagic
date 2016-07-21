package com.demo.java.core.controller;

import com.demo.java.core.quartz.FetcherQuartz;
import com.demo.java.core.quartz.ScheduleFactory;
import com.demo.java.common.utils.ReflectUtils;
import com.demo.java.core.entity.Task;
import com.demo.java.core.service.TaskService;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("task")
public class TaskController {

    ScheduleFactory scheduleFactory = ScheduleFactory.getInstance();

    @Resource
    TaskService jobService;

    /**
     * 编辑定时任务页面
     *
     * @param regexId
     * @return
     */
    @RequestMapping("input/{regexId}")
    public ModelAndView input(@PathVariable String regexId) {
        ModelAndView modelAndView = new ModelAndView("taskInput");
        modelAndView.addObject("regexId", regexId);
        modelAndView.addObject("methods", ReflectUtils.getMethod(FetcherQuartz.class));
        return modelAndView;
    }

    /**
     * 保存一条定时配置
     *
     * @param task
     * @return
     */
    @RequestMapping("save")
    public String save(Task task) {
        try {
            scheduleFactory.addJob(task);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "redirect:/task/list";
    }

    /**
     * 定时配置列表页
     *
     * @return
     */
    @RequestMapping("list")
    public ModelAndView list() {
        List<Task> list = new ArrayList<>();
        try {
            list = scheduleFactory.getAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return new ModelAndView("taskList", "list", list);
    }

    /**
     * 暂停定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping("pause/{id}")
    @ResponseBody
    public String pauseJob(@PathVariable String id) {
        try {
            Task job = jobService.get(id);
            scheduleFactory.pauseJob(job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    /**
     * 恢复暂停的定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping("resume/{id}")
    @ResponseBody
    public String resumeJob(@PathVariable String id) {
        try {
            Task job = jobService.get(id);
            scheduleFactory.resumeJob(job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    /**
     * 删除一个定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    @ResponseBody
    public String deleteJob(@PathVariable String id) {
        try {
            Task job = jobService.get(id);
            scheduleFactory.deleteJob(job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "OK";
    }

    /**
     * 立即运行定时任务
     *
     * @param id
     * @return
     */
    @RequestMapping("run/{id}")
    @ResponseBody
    public String runJob(@PathVariable String id) {
        try {
            Task job = jobService.get(id);
            scheduleFactory.runAJobNow(job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
