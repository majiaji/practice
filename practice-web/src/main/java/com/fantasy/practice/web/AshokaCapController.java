package com.fantasy.practice.web;

import com.fantasy.practice.service.ashoka.AshokaCapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by jiaji on 2017/7/24.
 */
@Controller
public class AshokaCapController {
    private static final Logger logger = LoggerFactory.getLogger(AshokaCapController.class);

    @Resource
    private AshokaCapService ashokaCapService;

    @RequestMapping("/doTask")
    @ResponseBody

    public Boolean doTask() {
        try {
            ashokaCapService.doCap();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
