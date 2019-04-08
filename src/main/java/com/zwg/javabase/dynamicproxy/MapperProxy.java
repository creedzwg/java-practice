package com.zwg.javabase.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: 张文刚
 * @Date: 2019/02/14  20:37
 * @Version: V1.0
 * @Description:
 */
public class MapperProxy implements InvocationHandler {


    private Object user;

    public MapperProxy(User user) {
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("212121212");
        return method.invoke(user, args);
    }
}
