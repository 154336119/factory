package com.slb.factory.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;
import com.slb.factory.Base;
import com.slb.factory.MyConstants;
import com.slb.factory.R;
import com.slb.factory.ui.contract.LoginContract;
import com.slb.factory.ui.presenter.LoginPresenter;
import com.slb.factory.weight.CountTimerButton;
import com.slb.factory.weight.ShareDialog;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.frame.utils.ActivityUtil;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.slb.factory.ui.activity.SuccessActivity.TYPE_100;

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

    private UMShareAPI mShareAPI ;
    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtil.setLightMode(this);
//        StatusBarUtil.setDarkMode(this);
        mShareAPI = UMShareAPI.get(this);
        UMShareConfig config = new UMShareConfig();
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        mShareAPI.setShareConfig(config);
        if (Base.getUserEntity() != null &&  !TextUtils.isEmpty(Base.getUserEntity().getToken()) && Base.getUserEntity().getState() == 2) {
            ActivityUtil.next(this, MainActivity.class, null, true);
        } else if(Base.getUserEntity() != null && !TextUtils.isEmpty(Base.getUserEntity().getToken())){
            mPresenter.getUserInfo(Base.getUserEntity().getToken());
        }

//        else if (Base.getUserEntity() != null && Base.getUserEntity().getState() == 1) {
//            goUploadLicenseActivity();
//        }else if (Base.getUserEntity() != null && Base.getUserEntity().getState() == 0) {
//            Bundle bundle = new Bundle();
//            bundle.putInt(MyConstants.TYPE,TYPE_100);
//            ActivityUtil.next(this,SuccessActivity.class,bundle,true);
//        }
    }

    @Override
    public void loginSuccess() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setAlias("xikeqiche", Base.getUserEntity().getToken(), new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                Logger.d(b + s);
            }
        });
        ActivityUtil.next(this,MainActivity.class,null,true);
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
                mPresenter.getCode(edtMobile.getText().toString());
                break;
            case R.id.btnLogin:
                mPresenter.login(edtMobile.getText().toString(),edtVCode.getText().toString());
                break;
            case R.id.TvWxLogin:
                mPresenter.thirdLogin(SHARE_MEDIA.WEIXIN,mShareAPI,LoginActivity.this);
//                ActivityUtil.next(this,UploadLicenseActivity.class);
//                showShareDialog();
                break;
        }
    }


    @Override
    public void varifyCodeSuccess() {

    }

    @Override
    public void showCountdown() {
        BtnGetCode.startCountTimer();
    }

    @Override
    public void goUploadLicenseActivity(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        ActivityUtil.next(this,UploadLicenseActivity.class,bundle,false);
    }
}
