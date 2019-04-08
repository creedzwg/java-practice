package com.zwg.javabase.threadcp;

import com.sun.org.apache.bcel.internal.generic.NEW;
import jdk.nashorn.internal.ir.WhileNode;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 张文刚
 * @Date: 2019/02/21  19:58
 * @Version: V1.0
 * @Description:
 */
public class CPTest {

    private static volatile LinkedList<Task> tasks = new LinkedList<>();

    private static final int MAX_SIZE = 20;


    public static void main(String[] args) {
//        new Thread(new product(), "product").start();
//        try {
//            TimeUnit.SECONDS.sleep(6);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new cunsumer(), "cunsumer").start();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));
        int activeCount = threadPoolExecutor.getActiveCount();
        System.out.println(activeCount);


    }

    //
    public static class product implements Runnable {
        private int count = 1;

        @Override
        public void run() {
            while (true) {
                synchronized (tasks) {
                    if (tasks.size() >= MAX_SIZE) {
                        try {
                            tasks.wait();
                            System.out.println("我被4343了");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Task task = new Task();
                    task.push(count++);
                    tasks.addLast(task);
                    System.out.println(Thread.currentThread().getName() + "提交第" + (count - 1) + "个任务");
                    tasks.notify();
                    if (count > 40) {
                        break;
                    }

                }
            }
        }

    }

    public static class cunsumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (tasks) {
                    if (tasks.size() < 1) {
                        try {
                            System.out.println("没人了");
                            tasks.wait();
                            System.out.println("我被晃醒了");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Task task = tasks.pollFirst();
                    System.out.println(Thread.currentThread().getName() + "获取" + task.getAge() + "第个任务");
                    tasks.notify();
                }
            }

        }
    }

}



