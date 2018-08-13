package com.example.hongyangmvp.presenter;

import android.os.Handler;

import com.example.hongyangmvp.bean.User;
import com.example.hongyangmvp.biz.IUserBiz;
import com.example.hongyangmvp.biz.OnLoginListener;
import com.example.hongyangmvp.biz.UserBiz;
import com.example.hongyangmvp.view.IUserLoginView;

/**
 * Presenter是用作Model和View之间交互的桥梁，那么应该有什么方法呢？
 * 其实也是主要看该功能有什么操作，比如本例，两个操作:login和clear。
 * presenter中写的方法事操作 view 接口的 ,
 */
public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        //通过new OnLoginListener 对全面测试操作,
        //userLoginView.toMainActivity(user); userLoginView.hideLoading();  在这地方调用的
        //直接调用实现接口子类中的方法 , 也就是mainactivty中的方法. 这个写法 还是调用model层的东西 OnLoginListener

        //在这里执行 IuserBiz中的方法, 实际是执行他的子类的方法,
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }


}