package com.fantasy.practice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiaji on 2017/5/4.
 */
@Controller
public class SpringParamController {
    @RequestMapping("/testParam")
    @ResponseBody
    public void testParamConvert(Integer num, Boolean bool) {
        System.out.println(num);
        System.out.println(bool);
    }
}
