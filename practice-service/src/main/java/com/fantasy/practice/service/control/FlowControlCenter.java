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

    private static final Long TOP = 10000L;

    @Resource
    private RedisCenter redisCenter;

    public Boolean tryAcquire(String apiName) {
        Jedis jedis = redisCenter.getInstance();
        if (jedis == null || (jedis.get(apiName) != null && Long.valueOf(jedis.get(apiName)) > TOP)) {
            return false;
        }
        Long count = jedis.incr(apiName);
        //init
        if (count == 1) {
            logger.error("apiName:{}第一次尝试，设置过期时间20s", apiName);
            jedis.expireAt(apiName, System.currentTimeMillis() / 1000 + 20);
        }
        logger.error("apiName:{}第{}次尝试", apiName, count);
        jedis.close();
        return count <= TOP;
    }
}
