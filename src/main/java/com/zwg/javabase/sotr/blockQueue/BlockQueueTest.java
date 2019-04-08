package com.zwg.javabase.sotr.blockQueue;

import sun.awt.SunHints;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: 张文刚
 * @Date: 2019/04/01  19:45
 * @Version: V1.0
 * @Description:
 */
public class BlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Runnable> runnables = new ArrayBlockingQueue<>(10);

        new  Thread(()->{
            for (;;){
                Runnable poll = null;
                try {
                    poll = runnables.take();
                    System.out.println(Thread.currentThread().getName()+"c");
                    poll.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        },"consumer").start();

TimeUnit.SECONDS.sleep(5);

        new  Thread(()->{
            IntStream.range(1,20).forEach(value -> {
                try {
                    runnables.put(()->{
                        System.out.println(Thread.currentThread().getName()+"p"+value);
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });



        },"productor").start();

    }

}
