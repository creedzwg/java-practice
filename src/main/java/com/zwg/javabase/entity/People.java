package com.zwg.javabase.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 张文刚
 * @Date: 2019/02/05  22:26
 * @Version: V1.0
 * @Description:
 */
@Component
public class People {

    private int id;


    public People() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
