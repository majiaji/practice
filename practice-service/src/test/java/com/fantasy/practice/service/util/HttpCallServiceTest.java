package com.fantasy.practice.service.util;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Created by jiaji on 2017/6/21.
 */
public class HttpCallServiceTest {
    @Test
    public void test4() {

        HttpCallService service = new HttpCallService();
        service.init();
        HttpCallService.CallParam param = new HttpCallService.CallParam();
        Map<String, String> body = Maps.newHashMap();
        body.put("code", "111");
        param.setBodyParam(body);
        service.doCall("http://localhost:8080/testParam", param, "post");
    }
}