package com.zwg.javabase.redis.jedis;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 张文刚
 * @Date: 2019/03/08  11:47
 * @Version: V1.0
 * @Description:
 */
public class RedisKey {
    /**
     * 员工id
     */
    public static final String ORGANIZATION_EMP_USERID = "ORGANIZATION_EMP_USERID";


    /**
     * 业务机构编码
     */
    public static final String ORGANIZATION_BUSI_CODE = "ORGANIZATION_BUSI_CODE";


    @Test
    public  void  test1() throws InterruptedException {

        try (Jedis jedis = JedisFactory.getJedis()) {
            //jedis.

            Transaction multi = jedis.multi();
            for (int i = 0; i < 100; i++) {
                multi.hset("zwg", i + "", i + "");

            }
            multi.exec();

        }
    }
        @Test
        public  void  testPipleline() throws InterruptedException {

            try(Jedis jedis = JedisFactory.getJedis()) {
                //jedis.

                long start1 = System.currentTimeMillis();
                for (int i = 0; i < 100000; i++) {
                    jedis.hset("zwgno",i+"",i+"");
                }
                long end1 = System.currentTimeMillis();
                System.out.println((end1-start1)/1000);

                long start = System.currentTimeMillis();
                Pipeline pipelined = jedis.pipelined();
                for (int i = 0; i < 100000; i++) {
                    pipelined.hset("zwgpipleline",i+"",i+"");

                }
                pipelined.sync();
                long end = System.currentTimeMillis();
                System.out.println((end-start)/1000);
            }

        //jedis.E



    }


    //加锁
    public static boolean  setLock(String uuid){
        try (Jedis jedis = JedisFactory.getJedis()) {

            String set = jedis.set("redislock", uuid, "NX", "EX", 30L);
            System.out.println(set);
            if("ok".equalsIgnoreCase(set)){
                System.out.println("加锁成功");
                return true;
            }else {
                System.out.println("加锁失败");
                return false;
            }

        }

    }
    private static final Long RELEASE_SUCCESS = 1L;
    //释放锁
    public static boolean  relaseLock(String uuid){

        try (Jedis jedis = JedisFactory.getJedis()) {
            String lua="if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1])  else return 0 end";
            Object eval = jedis.eval(lua, Collections.singletonList("redislock"),Collections.singletonList(uuid));
            System.out.println(eval);
            if(RELEASE_SUCCESS.equals(eval)){
                System.out.println("释放锁成功");
                return true;
            }else {
                System.out.println("释放锁失败");
                return false;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
//        String uuid = UUID.randomUUID().toString();
//        setLock(uuid);
//        TimeUnit.SECONDS.sleep(5);
//        relaseLock(uuid);

        test();
    }

    private static void test() {
         Long expireTime=100000L;
        String busiCode = ORGANIZATION_EMP_USERID;
        String userId = ORGANIZATION_BUSI_CODE;
        String key = busiCode + ":" + userId + ":" + "hahah";
        String luascript=" if redis.call('get',KEYS[1])==ARGV[1] then return ARGV[1] else return  redis.call('set',KEYS[1],KEYS[2],KEYS[3],KEYS[4],KEYS[5]) end";
        try (Jedis jedis = JedisFactory.getJedis()) {
            Object eval = jedis.eval(luascript, Arrays.asList(key, userId, "XX", "PX", String.valueOf(expireTime)), Collections.singletonList(userId));
            System.out.println(eval);
            System.out.println(luascript);
        }
    }


}
