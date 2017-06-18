package com.fantasy.practice.service.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by jiaji on 16/12/7.
 */
public class LocalCacheComponmentTest {
    volatile int num = 0;

    @Test
    public void test() throws Exception {
        System.out.println("start...");
        LoadingCache<String, String> cache = CacheBuilder
                .newBuilder()
                .maximumSize(5)
                .refreshAfterWrite(2, TimeUnit.SECONDS).recordStats()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        //load from db
                        return "result";
                    }
                });

        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(() -> {
                while (true) {
                    try {
                        System.out.println("now num is : " + cache.get("hehe"));
                    } catch (ExecutionException e) {
                        System.out.println("药丸");
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        countDownLatch.await();
    }
}