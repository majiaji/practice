package com.fantasy.practice.web;

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
    @RequestMapping("/testParam")
    @ResponseBody
    public Map testParamConvert(HttpServletRequest request) {
        return request.getParameterMap();
    }
}
