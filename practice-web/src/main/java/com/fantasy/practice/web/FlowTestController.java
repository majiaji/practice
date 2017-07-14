package com.fantasy.practice.web;

import com.fantasy.practice.service.control.FlowControlCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by jiaji on 2017/6/18.
 */
@Controller
public class FlowTestController {

    private static final Logger logger = LoggerFactory.getLogger(FlowTestController.class);

    @Resource
    private FlowControlCenter flowControlCenter;


    private ExecutorService pool = Executors.newFixedThreadPool(20);

    @RequestMapping("/start")
    @ResponseBody
    public Long start(@RequestParam String apiName) throws ExecutionException, InterruptedException {
        logger.error("开始！！！");
        Date now = new Date();
        Boolean running = true;
        while (running) {
            Future<Boolean> future = pool.submit(() -> {
                logger.error("线程" + Thread.currentThread().getName() + "执行中..");
                return flowControlCenter.tryAcquire(apiName);
            });
            running = future.get();
            if (running.equals(false)) {
                logger.error("流控生效ing ");
                Thread.sleep(2000L);
                running = true;
            }
        }
        return System.currentTimeMillis() - now.getTime();
    }
}
