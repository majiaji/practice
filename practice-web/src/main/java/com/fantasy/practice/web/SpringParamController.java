package com.fantasy.practice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by jiaji on 2017/5/4.
 */
@Controller
public class SpringParamController {
    private static final Logger logger = LoggerFactory.getLogger(SpringParamController.class);

    @RequestMapping("/testParam")
    @ResponseBody
    public Map testParamConvert(HttpServletRequest request) {
        logger.error("in servlet !!!");

        return request.getParameterMap();
    }
}
