package com.test.pojo;

//@Data //自动生成get和set方法  toString方法
//@NoArgsConstructor  //自动生成无参构造函数
//@AllArgsConstructor //自动生成带所有参数的构造函数
public class User {
    private String id;
    private String name;
    private int age;

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
