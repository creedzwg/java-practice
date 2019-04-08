package com.zwg.javabase.atom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 张文刚
 * @Date: 2019/02/15  14:48
 * @Version: V1.0
 * @Description:
 */
public class AutoIntegerTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(56);
        System.out.println(atomicInteger.get());
        atomicInteger.compareAndSet(57, 78);
        System.out.println(atomicInteger.get());
    }
}
