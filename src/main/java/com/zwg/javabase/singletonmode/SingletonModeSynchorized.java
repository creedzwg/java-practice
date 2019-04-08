package com.zwg.javabase.singletonmode;

import org.springframework.validation.annotation.Validated;

/**
 * @Author: 张文刚
 * @Date: 2019/02/23  21:15
 * @Version: V1.0
 * @Description:
 */
public class SingletonModeSynchorized {

    private static volatile SingletonModeSynchorized singletonModeSynchorized;

    private SingletonModeSynchorized() {

    }


    public static SingletonModeSynchorized getInstance() {
        synchronized (SingletonModeSynchorized.class) {
            if (singletonModeSynchorized == null) {
                singletonModeSynchorized = new SingletonModeSynchorized();
            }
        }
        return singletonModeSynchorized;
    }

}
