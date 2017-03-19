package com.fantasy.practice.service.guava;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by jiaji on 16/12/18.
 */
public class ArrayBlockingQueueTest {
    @Test
    public void test() {
//        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100, true);
//        ConcurrentLinkedQueue queue1 = new ConcurrentLinkedQueue();

//        String str = "[{\"id\":266,\"visible\":1,\"sort_index\":12}]";


        String str1 = "[{\\\"id\\\":266,\\\"visible\\\":1,\\\"sort_index\\\":12}]";
        System.out.println(JSON.parseObject(null));
    }
}
