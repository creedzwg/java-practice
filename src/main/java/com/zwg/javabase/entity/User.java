package com.zwg.javabase.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 张文刚
 * @Date: 2019/02/05  22:25
 * @Version: V1.0
 * @Description:
 */
@Component
public class User {
    private int id;
    private People people;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
