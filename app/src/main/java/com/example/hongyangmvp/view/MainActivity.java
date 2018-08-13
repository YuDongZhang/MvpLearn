package com.example.hongyangmvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hongyangmvp.R;
import com.example.hongyangmvp.bean.User;
import com.example.hongyangmvp.biz.OnLoginListener;
import com.example.hongyangmvp.presenter.UserLoginPresenter;
import com.example.hongyangmvp.view.IUserLoginView;

/**
 * MVC中是允许Model和View进行交互的，而MVP中很明显，Model与View之间的交互由Presenter完成。
 * 还有一点就是Presenter与View之间的交互是通过接口的（代码中会体现)。
 *
 * 这个类里面就没有model什么事, 就是通过presenter 来进行交互 , 主要事实现了IuserVIew接口
 */

public class MainActivity extends AppCompatActivity implements IUserLoginView {

    private EditText mEtUsername, mEtPassword;
    private Button mBtnLogin, mBtnClear;
    private ProgressBar mPbLoading;
    //构造方法中传入this, 其实构造方法是public UserLoginPresenter(IUserLoginView userLoginView)
    //由于 mainactivity实现了IUserLoginView 可以作为子类进行传入,而在 UserLoginPresenter 中用 IUserLoginView
    //来做操作, 这样可以达到操作父类来控制子类的目的,
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mEtUsername = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_password);

        mBtnClear = (Button) findViewById(R.id.id_btn_clear);
        mBtnLogin = (Button) findViewById(R.id.id_btn_login);

        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserLoginPresenter.login();
                //这个地方这个会执行到,通过参数中 new IUserBiz 实现他的两个方法,
                //public void login(String username, String password, OnLoginListener loginListener);
                //掉到子类中的 UserBiz 中的方法,
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEtUsername.setText("");
    }

    @Override
    public void clearPassword() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }
}
