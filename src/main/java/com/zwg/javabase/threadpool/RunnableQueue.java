package com.zwg.javabase.threadpool;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: 张文刚
 * @Date: 2019/02/27  18:55
 * @Version: V1.0
 * @Description:
 */

//任务队列,主要用于缓存提交到线程池中的任务,供工作线程进行调度
public interface RunnableQueue {
    //有新任务来时会offer到任务队列
    void offer(Runnable runnable);

    //工作线程通过take方法获取runnable
    Runnable take();

    //任务数量
    int size();
}
