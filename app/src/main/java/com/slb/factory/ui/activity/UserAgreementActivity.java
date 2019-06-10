package com.slb.factory.ui.activity;

import android.webkit.WebView;
import android.widget.Button;
import android.widget.RadioGroup;

import com.slb.factory.R;
import com.slb.factory.http.bean.ConfigEntity;
import com.slb.factory.ui.contract.MainContract;
import com.slb.factory.ui.contract.UserAgreementContract;
import com.slb.factory.ui.presenter.UserAgreementPresenter;
import com.slb.frame.ui.activity.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAgreementActivity extends BaseMvpActivity<UserAgreementContract.IView, UserAgreementContract.IPresenter> implements UserAgreementContract.IView {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mPresenter.getConfig();
    }

    @Override
    protected String setToolbarTitle() {
        return "用户协议";
    }


    @Override
    public void getConfigSuccess(ConfigEntity entity) {
        webView.loadDataWithBaseURL(null,entity.getXieyi(), "text/html", "utf-8", null);
    }

    @Override
    public UserAgreementContract.IPresenter initPresenter() {
        return new UserAgreementPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_agreement;
    }
}
