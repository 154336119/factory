package com.slb.factory.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.slb.factory.R;
import com.slb.factory.http.bean.PayTypeEntity;
import com.slb.factory.ui.adapter.PayTypePageAdapter;
import com.slb.factory.ui.contract.ChoisePayTypeContract;
import com.slb.factory.ui.contract.LoginContract;
import com.slb.factory.ui.fragment.PublicPayFragment;
import com.slb.factory.ui.fragment.WxPayFragment;
import com.slb.factory.ui.presenter.ChoisePayTypePresenter;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.frame.ui.activity.BaseMvpActivity;
import com.umeng.commonsdk.debug.E;

import butterknife.BindView;
import butterknife.ButterKnife;
public class ChoisePayTypeActiivty extends BaseMvpActivity<ChoisePayTypeContract.IView, ChoisePayTypeContract.IPresenter>
        implements ChoisePayTypeContract.IView {
    public static final int TYPE_WX = 1;
    public static final int TYPE_ZFB = 2;
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private PayTypePageAdapter mAdapter;
    @Override
    protected String setToolbarTitle() {
        return "选择支付方式";
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mPresenter.getPayTypeConfig();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_choise_pay_type;
    }

    @Override
    public ChoisePayTypeContract.IPresenter initPresenter() {
        return new ChoisePayTypePresenter();
    }

    @Override
    public void getPayTypeConfigSuccess(PayTypeEntity entity) {
        mAdapter = new PayTypePageAdapter(getSupportFragmentManager());
        mAdapter.addTab(WxPayFragment.newInstance(entity,TYPE_WX), "微信支付");
        mAdapter.addTab(WxPayFragment.newInstance(entity,TYPE_ZFB), "支付宝支付");
        mAdapter.addTab(PublicPayFragment.newInstance(entity), "对公转账");
        mViewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(mViewPager);
        setUnderLineVisible(View.GONE);
    }
}
