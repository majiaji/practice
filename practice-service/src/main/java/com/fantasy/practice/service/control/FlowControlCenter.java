package com.fantasy.practice.service.control;

import com.fantasy.practice.service.redis.RedisCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * Created by jiaji on 2017/6/22.
 */
@Service
public class FlowControlCenter {
    private static final Logger logger = LoggerFactory.getLogger(FlowControlCenter.class);

    @Resource
    private RedisCenter redisCenter;

    public boolean tryAcquire(String apiName) {
        Jedis jedis = redisCenter.getInstance();
        if (jedis == null) return false;
        Long count = jedis.incr(apiName);
        //init
        if (count == 1) {
            logger.error("apiName:{}第一次调用，设置过期时间2min", apiName);
            jedis.expireAt(apiName, System.currentTimeMillis() + 120 * 1000);
        }
        logger.error("apiName:{}第{}次调用", apiName, count);
        return count <= 1000;
    }
}
