package com.zwg.javabase.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 张文刚
 * @Date: 2019/02/21  11:16
 * @Version: V1.0
 * @Description:
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                SleepUtils.second(100L);
            }
        }, "time-waiting-thread").start();
        new Thread(() -> {
            while (true) {
                synchronized (ThreadState.class) {
                    try {
                        ThreadState.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SleepUtils.second(100L);
            }
        }, "waiting-thread").start();

        new Thread(() -> {
            while (true) {
                synchronized (ThreadState.class) {
                    SleepUtils.second(1000L);
                }

            }
        }, "blocked-A-thread").start();
        SleepUtils.second(2L);
        new Thread(() -> {
            while (true) {
                synchronized (ThreadState.class) {
                    SleepUtils.second(1000L);
                }

            }
        }, "blocked-B-thread").start();
    }


    public static class SleepUtils {
        public static void second(long second) {
            try {
                TimeUnit.SECONDS.sleep(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
