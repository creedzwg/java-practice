package com.zwg.javabase.entity;

/**
 * @Author: 张文刚
 * @Date: 2019/02/19  21:17
 * @Version: V1.0
 * @Description:
 */
public class User2 {

    private int id;
    private PeopleVo people;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PeopleVo getPeople() {
        return people;
    }

    public void setPeople(PeopleVo people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "User2{" +
                "id=" + id +
                ", people=" + people +
                '}';
    }
}
