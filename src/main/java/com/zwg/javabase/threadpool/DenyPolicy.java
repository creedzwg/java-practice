package com.zwg.javabase.threadpool;

/**
 * @Author: 张文刚
 * @Date: 2019/02/27  19:50
 * @Version: V1.0
 * @Description:
 */

//拒绝策略,当线程池中的线程已达最大线程且任务队列的limit已满,此时执行拒绝策略
public interface DenyPolicy {


    void reject(Runnable runnable, ThreadPool threadPool);


    class DiscardDenyPolicy implements DenyPolicy {


        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            //do nothing
        }
    }
}
