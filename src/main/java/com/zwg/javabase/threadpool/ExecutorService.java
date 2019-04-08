package com.zwg.javabase.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author: 张文刚
 * @Date: 2019/03/13  14:11
 * @Version: V1.0
 * @Description:
 */
public class ExecutorService {
    public static void main(String[] args) {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 30 * 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>(16*4),runnable -> {

            final AtomicInteger atomicInteger=new AtomicInteger(1);
            return new Thread(runnable,"我的自定义线程"+atomicInteger.incrementAndGet());
        },(r, executor) ->
            System.out.println("已被拒绝")
        );
        IntStream.range(1,60).forEach(value ->
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName()+"我是第"+value+"个任务");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"我是第"+value+"个任务,结束");
            })
        );

    }
}
