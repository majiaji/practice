package com.fantasy.practice.service.guava;

import com.google.common.util.concurrent.RateLimiter;

import javax.annotation.PostConstruct;

/**
 * Created by jiaji on 16/12/5.
 */
public class RateLimiterComponment {
    private RateLimiter rateLimiter;

    @PostConstruct
    void init() {
        rateLimiter = RateLimiter.create(0.1);
        rateLimiter.acquire();
    }
}
