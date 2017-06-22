package com.fantasy.practice.web;

import com.fantasy.practice.service.control.FlowControlCenter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jiaji on 2017/6/18.
 */
@Controller
public class FlowTestController {
    @Resource
    private FlowControlCenter flowControlCenter;


    private ExecutorService pool = Executors.newFixedThreadPool(10);

    @RequestMapping("/start")
    @ResponseBody
    public String start(String apiName) {
        while (!pool.isShutdown()) {
            pool.submit((Runnable) () -> flowControlCenter.tryAcquire(apiName));
        }
        return "started!";
    }

    @RequestMapping("/stop")
    @ResponseBody
    public String stop() {
        pool.shutdown();
        return "stoped!";
    }
}
