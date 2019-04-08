package com.zwg.javabase.threadpool;

/**
 * @Author: 张文刚
 * @Date: 2019/02/27  19:58
 * @Version: V1.0
 * @Description:
 */
//内部任务线程,该线程对应着内部的线程数量,内部线程会从使用到RunnableQueue,然后不断的从queue中取出某个runnable,并运行其run方法
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {

    }
}
