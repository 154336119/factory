package com.slb.factory.ui.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.jaeger.library.StatusBarUtil;
import com.slb.factory.ui.activity.old.MailboxVerificationActivity;
import com.slb.factory.ui.activity.old.RegisterActivity;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ActivityUtil;
import com.slb.factory.Base;
import com.slb.factory.MainActivity;
import com.slb.factory.R;
import com.slb.factory.http.bean.UserEntity;
import com.slb.factory.ui.contract.LoginContract;
import com.slb.factory.ui.presenter.LoginPresenter;
import com.slb.factory.util.SharedPreferencesUtils;
import com.slb.factory.util.config.BizcContant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.slb.factory.util.config.BizcContant.SP_IS_FIRST;
import static com.slb.factory.util.config.BizcContant.SP_USER;

public class LoginActivity extends BaseMvpActivity<LoginContract.IView, LoginContract.IPresenter>
        implements LoginContract.IView{
    private AlertDialog.Builder builder;
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtPwd)
    EditText edtPwd;
    @BindView(R.id.tvwForgotPwd)
    TextView tvwForgotPwd;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    View topBg;
    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    public LoginContract.IPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
//        topBg = findViewById(R.id.IvLoginBg);
//        StatusBarUtil.setTranslucentForImageView(this,110,topBg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        Boolean isfirst = (Boolean) SharedPreferencesUtils.getParam(Base.getContext(), BizcContant.SP_IS_FIRST, true);
        if(isfirst){
            showWarning_1();
        }
        String userJsonStr = (String) SharedPreferencesUtils.getParam(Base.getContext(), BizcContant.SP_USER,  "");
        if(!TextUtils.isDigitsOnly(userJsonStr)){
            UserEntity entity =JSONObject.parseObject(userJsonStr,UserEntity.class);
            Base.setUserEntity(entity);
            ActivityUtil.next(this, MainActivity.class);
            finish();
        }
    }

    @OnClick({R.id.tvRegister, R.id.tvwForgotPwd, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRegister:
                ActivityUtil.next(this, RegisterActivity.class);
                break;
            case R.id.tvwForgotPwd:
                ActivityUtil.next(this, MailboxVerificationActivity.class);
                break;
            case R.id.btnLogin:
                mPresenter.login(edtMobile.getText().toString(), edtPwd.getText().toString());
                break;
        }
    }

    @Override
    public void loginSuccess() {
        ActivityUtil.next(this, MainActivity.class);
        String userJsonStr = JSONObject.toJSONString(Base.getUserEntity());//将java对象转换为json对象
        SharedPreferencesUtils.setParam(this, SP_USER,userJsonStr);
        finish();
    }
    /**
     * dialog
     */
    private void showWarning_1() {
        builder = new AlertDialog.Builder(this)
                .setMessage(R.string.Warning_1).setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        SharedPreferencesUtils.setParam(LoginActivity.this, SP_IS_FIRST,false);
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Do Not Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        System.exit(0);
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }
}
