package com.example.hongyangmvp.biz;

import com.example.hongyangmvp.bean.User;

/**
 * model
 * 至于业务类，我们抽取了一个接口，一个实现类这也很常见~~login方法，一般肯定是连接服务器的，
 * 是个耗时操作，所以我们开辟了子线程，Thread.sleep(2000)模拟了耗时，由于是耗时操作，所以我们通过一个回调接口
 * 来通知登录的状态。其实这里还是比较好写的，因为和传统写法没区别。
 */
public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}
