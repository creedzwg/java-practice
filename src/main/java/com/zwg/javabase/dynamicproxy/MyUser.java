package com.zwg.javabase.dynamicproxy;

/**
 * @Author: 张文刚
 * @Date: 2019/02/14  20:40
 * @Version: V1.0
 * @Description:
 */
public class MyUser implements User {
    @Override
    public void play(String s) {
        System.out.println("我 play" + s);
    }
}
