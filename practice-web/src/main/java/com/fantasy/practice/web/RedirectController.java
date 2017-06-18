package com.fantasy.practice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jiaji on 2017/5/31.
 */
@Controller
public class RedirectController {
    @RequestMapping("/request/test")
    @ResponseBody
    String test(HttpServletRequest request, HttpServletResponse response) {
        if (WebUtils.getCookie(request, "coo") == null) {
            response.addCookie(new Cookie("coo", "haha"));
            try {
                response.sendRedirect("/request/test");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return WebUtils.getCookie(request, "coo").getDomain();
        }
        return "?";
    }
}
