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

    private static final Long TOP = 10L;

    @Resource
    private RedisCenter redisCenter;

    public Boolean tryAcquire(String apiName) {
        Jedis jedis = redisCenter.getInstance();
        if (jedis == null) {
            logger.error("没有redis实例资源");
            return false;
        } else if (jedis.get(apiName) != null && Long.valueOf(jedis.get(apiName)) > TOP) {
            logger.error("apiName:{} redis中的值:{} 流控生效ing", apiName, Long.valueOf(jedis.get(apiName)));
            jedis.close();
            return false;
        }

        Long count = jedis.incr(apiName);
        //init
        if (count == 1) {
//            logger.error("apiName:{}第一次尝试，设置过期时间20s", apiName);
            jedis.pexpire(apiName, 1000L);
        }
        logger.error("apiName:{}  count:{}", apiName, count);
        jedis.close();
        return count <= TOP;
    }
}
