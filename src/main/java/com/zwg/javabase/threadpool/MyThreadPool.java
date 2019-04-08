package com.zwg.javabase.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: 张文刚
 * @Date: 2019/02/15  15:49
 * @Version: V1.0
 * @Description:
 */
public class MyThreadPool {


    private final static int DEFAULT_SIZE = 10;

    private final int initSize;

    private static LinkedList<Runnable> THREAD_QUEUE = new LinkedList<>();

    private final static int MAX_THREAD_QUEUE = 30;

    private static ThreadGroup threadGroup = new ThreadGroup("Thread Pool work Thread");

    private DenyPolicy denyPolicy = new DenyPolicy();


    private List<WorkThread> workThreads = new ArrayList<>(10);
    private volatile int seq = 1;


    private class DenyPolicy {
        void reject(LinkedList<Runnable> THREAD_QUEUE, Runnable runnable) {
//            throw new RuntimeException("已超出最大容量"+THREAD_QUEUE.size());
            System.out.println("已超出最大容量");
        }
    }

    public MyThreadPool() {
        this(DEFAULT_SIZE);
    }

    public MyThreadPool(int initSize) {
        this.initSize = initSize;
        init();
    }

    private void init() {
        for (int i = 0; i < initSize; i++) {
            WorkThread workThread = new WorkThread(threadGroup, "thread-" + (seq++));
            System.out.println("start" + (seq - 1));
            workThread.start();
            workThreads.add(workThread);
        }
    }

    public void submit(Runnable runnable) {
        synchronized (THREAD_QUEUE) {
            if (THREAD_QUEUE.size() <= MAX_THREAD_QUEUE) {
                THREAD_QUEUE.addLast(runnable);
                THREAD_QUEUE.notifyAll();
            } else {
                denyPolicy.reject(THREAD_QUEUE, runnable);
            }
        }
    }


    enum WorkThreadStatus {
        FREE, RUNNING, BLOCKED, DEAD
    }

    private class WorkThread extends Thread {
        //初始为Free
        private WorkThreadStatus workThreadStatus = WorkThreadStatus.FREE;

        public WorkThread(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        @Override
        public void run() {
            OUTER:
            while (workThreadStatus != WorkThreadStatus.DEAD) {
                Runnable runnable;
                synchronized (THREAD_QUEUE) {

                    while (THREAD_QUEUE.isEmpty()) {
                        try {
                            THREAD_QUEUE.wait();
                            workThreadStatus = WorkThreadStatus.BLOCKED;
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    //获取任务执行
                    runnable = THREAD_QUEUE.removeFirst();
                }
                //在锁外进行执行
                if (runnable != null) {
                    workThreadStatus = WorkThreadStatus.RUNNING;
                    runnable.run();
                    workThreadStatus = WorkThreadStatus.FREE;
                }
            }

        }
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();

        IntStream.rangeClosed(0, 40).forEach(seq -> {
            myThreadPool.submit(() -> {
                //System.out.println(Thread.currentThread().getName()+"start"+seq);
                try {
                    System.out.println("任务" + seq + "正在执行");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "stop" + seq);
            });
        });

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.rangeClosed(0, 10).forEach(seq -> {
            myThreadPool.submit(() -> {
                //System.out.println(Thread.currentThread().getName()+"start"+seq);
                try {
                    System.out.println("再次提交任务" + seq + "正在执行");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "stop" + seq);
            });
        });


    }


}
