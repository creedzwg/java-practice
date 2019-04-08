package com.zwg.javabase.threadpool;

/**
 * @Author: 张文刚
 * @Date: 2019/02/27  19:31
 * @Version: V1.0
 * @Description:
 */
//线程工厂提供了创建线程的方法,通过线程工厂可以创建各种优先级,各种ThreadGroup,自定义名称的线程
@FunctionalInterface
public interface ThreadFactory {


    Thread createThread(Runnable runnable);
}
