package com.zwg.javabase.threadpool;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

/**
 * @Author: 张文刚
 * @Date: 2019/03/20  11:31
 * @Version: V1.0
 * @Description:
 */
public class ExecutoresDemo implements Runnable {

    public static void main(String[] args) {
        /**
         * These pools will typically improve the performance 这个线程池将会很适合处理大量的短期的异步任务
         *    of programs that execute many short-lived asynchronous tasks
         */
//        ThreadPoolExecutor executorService =(ThreadPoolExecutor) Executors.newCachedThreadPool();
//        System.out.println( executorService.getCorePoolSize());
//        System.out.println( executorService.getActiveCount());
//        System.out.println(executorService.getPoolSize());
//        System.out.println(executorService.getQueue().size());
//        IntStream.range(0,20).boxed().forEach(integer -> {
//            executorService.execute(()->{
//                try {
//                    System.out.println(Thread.currentThread().getName()+"---start---");
//                    TimeUnit.SECONDS.sleep(2);
//                    System.out.println(Thread.currentThread().getName()+"over");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        });
        ExecutoresDemo executoresDemo = new ExecutoresDemo();
//        executoresDemo.testThreadPoolExecutor();
        try {
            executoresDemo.testCode();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    public void testCode() throws UnsupportedEncodingException {
        String s="asasasas";
        String encode = URLEncoder.encode(s, "UTF-8");
        System.out.println(encode);
        String decode = URLDecoder.decode(encode, "utf-8");
        System.out.println(decode);

    }


    public void testThreadPoolExecutor(){
              //corePoolSize 当线程数小于该值时,对于新任务会优先创建线程



          //corePoolSize>=1,且blockQueue的size<=
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 30, TimeUnit.SECONDS, new SynchronousQueue<>());
        //并行提交20个任务
        IntStream.range(0,30).boxed().forEach(integer -> {
            threadPoolExecutor.execute(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"---start---此时corePoolSize");

                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(Thread.currentThread().getName()+"over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });




    }



    public void run() {

    }


}
