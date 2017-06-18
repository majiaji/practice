package com.fantasy.practice.web;

/**
 * Created by jiaji on 2017/6/5.
 */
public class LoginContext {
    private static ThreadLocal<Haha> context = new ThreadLocal<>();

    public static ThreadLocal<Haha> getContext() {
        return context;
    }

    public static void setContext(ThreadLocal<Haha> context) {
        LoginContext.context = context;
    }

    class Haha {
        String a;
        Integer b;
    }
}
