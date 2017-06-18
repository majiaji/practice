package com.fantasy.practice.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

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

    @Test
    public void test1() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        sb.append("1").append("=").append("haha");
        System.out.println(DigestUtils.md5DigestAsHex(sb.toString().getBytes("utf-8")).toUpperCase());
    }

    @Test
    public void test2() throws IOException {
        HttpClient httpclient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet("https://www.taobao.com");
        HttpResponse response = httpclient.execute(httpGet);
        System.out.println(EntityUtils.toString(response.getEntity(), Charset.forName("utf-8")));
    }


    @Test
    public void test3(){
        System.out.println(System.getProperty("user.home"));
    }
}
