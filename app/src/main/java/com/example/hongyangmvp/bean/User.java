package com.example.hongyangmvp.bean;
/**
 * model
 *
 * 首先实体类User不用考虑这个肯定有，其次从效果图可以看到至少有一个业务方法login()，这两点没什么难度，
 * 我们首先完成：
 */
public class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}