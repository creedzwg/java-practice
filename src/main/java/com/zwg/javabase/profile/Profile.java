package com.zwg.javabase.profile;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 张文刚
 * @Date: 2019/02/21  21:23
 * @Version: V1.0
 * @Description:
 */
public class Profile {

    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<Long>() {
        //当首次调用ThreadLocal的get方法时,如果之前没有调用set(),将会自动调用
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void main(String[] args) {
        Long aLong = THREAD_LOCAL.get();
        System.out.println(aLong);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long time = getEnd(THREAD_LOCAL);
        System.out.println(time);
    }

    private static Long getEnd(ThreadLocal<Long> threadLocal) {
        return System.currentTimeMillis() - threadLocal.get();
    }


}
