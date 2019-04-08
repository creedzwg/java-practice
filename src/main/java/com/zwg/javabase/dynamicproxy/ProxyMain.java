package com.zwg.javabase.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author: 张文刚
 * @Date: 2019/02/14  20:40
 * @Version: V1.0
 * @Description:
 */
public class ProxyMain {

    public static void main(String[] args) {
        MyUser myUser = new MyUser();
        MapperProxy mapperProxy = new MapperProxy(myUser);
        User o = (User) Proxy.newProxyInstance(myUser.getClass().getClassLoader(), new Class[]{User.class}, mapperProxy);
        o.play("asassas");

    }
}
