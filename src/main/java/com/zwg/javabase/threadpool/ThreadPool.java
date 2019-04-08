package com.zwg.javabase.threadpool;

/**
 * @Author: 张文刚
 * @Date: 2019/02/27  17:35
 * @Version: V1.0
 * @Description:
 */
public interface ThreadPool {
    //提交任务到线程池
    void execute(Runnable runnable);

    //关闭线程池
    void shutdown();

    //获取线程池初始线程数
    int getInitSize();

    //获取线程池最大线程数
    int getMaxSize();

    //获取线程池核心线程
    int getCoreSize();

    //是否已经关闭
    Boolean isShutdown();


}
