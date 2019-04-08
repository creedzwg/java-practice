package com.zwg.javabase.classloader;

/**
 * @Author: 张文刚
 * @Date: 2019/02/20  22:25
 * @Version: V1.0
 * @Description:
 */
public class Singleton {
    //
    private static Singleton singleton = new Singleton();
    private static int x = 0;
    private static int y = 0;

    private Singleton() {
        x++;
        y++;
    }

    public static Singleton getSingleton() {
        return singleton;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
