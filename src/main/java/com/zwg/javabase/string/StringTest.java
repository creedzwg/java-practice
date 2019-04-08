package com.zwg.javabase.string;

/**
 * @Author: 张文刚
 * @Date: 2019/02/20  19:00
 * @Version: V1.0
 * @Description:
 */
public class StringTest {

    public static void main(String[] args) {
        String aaa = new String("aaa");
        chanageString(aaa);
        System.out.println(aaa);
    }

    private static void chanageString(String aaa) {
        aaa = new String("as");
        System.out.println(aaa);
    }
}
