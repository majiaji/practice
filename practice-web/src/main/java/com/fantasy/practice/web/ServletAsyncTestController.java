package com.fantasy.practice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by jiaji on 16/12/23.
 */
@Controller
public class ServletAsyncTestController {
    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public void asyncAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        final PrintWriter writer = response.getWriter();
        writer.println("first message....\r\n");
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        final AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.setTimeout(100000); //测试设置超时时间
        asyncContext.start(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                writer.println("hi.....\r\n");
            }
            asyncContext.complete();
        });
    }
}
