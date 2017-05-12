package com.fantasy.practice.service;

import org.junit.Test;

/**
 * Created by jiaji on 2017/3/27.
 */
public class threadLocalTest {


    @Test
    public void test() {
        memoryLeakExample();
        Thread curThread = Thread.currentThread();
        System.out.println();
    }

    private void memoryLeakExample() {
        ThreadLocal<String> t = new ThreadLocal<>();
        t.set("hahahahaha");
    }
}
