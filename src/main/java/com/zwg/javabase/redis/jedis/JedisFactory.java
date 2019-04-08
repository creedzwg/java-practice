package com.zwg.javabase.redis.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: 张文刚
 * @Date: 2019/03/08  11:35
 * @Version: V1.0
 * @Description:
 */
public class JedisFactory {

   private static JedisPool jedisPool;

static {
    JedisPoolConfig config= new JedisPoolConfig();
    config.setMaxTotal(1024);
    config.setMaxIdle(200);
    config.setMaxWaitMillis(10000);
    config.setTestOnBorrow(true);
     jedisPool = new JedisPool(config,"localhost",6379,2000);

}


    public static Jedis getJedis(){
        return  jedisPool.getResource();
    }

}
