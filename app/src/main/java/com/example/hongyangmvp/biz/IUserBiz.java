package com.example.hongyangmvp.biz;
/**
 * model
 */
public interface IUserBiz
{
    public void login(String username, String password, OnLoginListener loginListener);
}
