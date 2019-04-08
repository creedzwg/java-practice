package com.zwg.javabase.collection;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: 张文刚
 * @Date: 2019/02/23  17:19
 * @Version: V1.0
 * @Description:
 */
public class Test1 {


    @Test
    public void test1() {
        LinkedList<Object> objects = new LinkedList<>();
        objects.addFirst(null);
        objects.addFirst(null);
        objects.addLast(121);
        System.out.println(objects.indexOf(121));
        System.out.println(objects.poll());
        System.out.println(objects.poll());
        System.out.println(3 & 9);


    }
}
