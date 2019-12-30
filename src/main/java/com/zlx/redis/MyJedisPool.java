package com.zlx.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyJedisPool {
    private MyJedisPool(){}

    private static volatile JedisPool jedisPool = null;

    public static JedisPool getInstance(){
        if (jedisPool == null){
            synchronized (MyJedisPool.class){
                if (jedisPool == null){
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(100);
                    jedisPoolConfig.setMaxIdle(30);
                    jedisPoolConfig.setMaxWaitMillis(100*1000);
                    jedisPoolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(jedisPoolConfig,"192.168.233.111",6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis){
        if (jedis != null){

        }
    }
}
