package com.zwg.javabase.threadcp;

/**
 * @Author: 张文刚
 * @Date: 2019/02/21  19:56
 * @Version: V1.0
 * @Description: 任务类
 */

public class Task {

    private int age;

    void push(int age) {
        this.age = age;
    }

    int getAge() {
        return this.age;
    }
}
