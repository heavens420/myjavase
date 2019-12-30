package com.zlx.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Set;

public class TestPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.233.111",6379);
        System.out.println(jedis.ping());

        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        System.out.println(jedis.get("k3"));

        Set<String> sets = jedis.keys("*");
        System.out.println(sets.size());


        //事务
        Transaction transaction = jedis.multi();
        transaction.set("t1","v1");
        transaction.set("t2","v2");

        //提交事务
        transaction.exec();

        //中断事务
//        transaction.discard();

        for (String s:sets
             ) {
            System.out.print(s+":\t"+jedis.get(s)+"\t");
        }
    }
}
