package com.slb.factory.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.slb.factory.R;
import com.slb.factory.ui.contract.LoginContract;
import com.slb.factory.ui.presenter.LoginPresenter;
import com.slb.factory.weight.CountTimerButton;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.statusbarutil.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginContract.IView, LoginContract.IPresenter>
        implements LoginContract.IView {
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtVCode)
    EditText edtVCode;
    @BindView(R.id.BtnGetCode)
    CountTimerButton BtnGetCode;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.TvWxLogin)
    TextView TvWxLogin;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
//        StatusBarUtil.setTranslucentForImageView(this, 110, findViewById(R.id.btnLogin));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public LoginContract.IPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.BtnGetCode, R.id.btnLogin, R.id.TvWxLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BtnGetCode:
                break;
            case R.id.btnLogin:
                break;
            case R.id.TvWxLogin:
                break;
        }
    }

    @Override
    public void setStateBar() {
        //        设置字体颜色为黑色
        StatusBarUtil.setImmersiveStatusBar(this,true);
//        设置状态栏透明
//        StatusBarUtil.setTranslucentStatus(this);
////        设置状态栏的颜色
            StatusBarUtil.setStatusBarColor(this, Color.TRANSPARENT);
    }
}
