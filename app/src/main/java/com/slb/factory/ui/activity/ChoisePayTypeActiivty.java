package com.slb.factory.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.slb.factory.R;
import com.slb.factory.ui.adapter.PayTypePageAdapter;
import com.slb.factory.ui.fragment.PublicPayFragment;
import com.slb.factory.ui.fragment.WxPayFragment;
import com.slb.frame.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoisePayTypeActiivty extends BaseActivity {
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
        mAdapter = new PayTypePageAdapter(getSupportFragmentManager());
        mAdapter.addTab(WxPayFragment.newInstance(), "微信支付");
        mAdapter.addTab(WxPayFragment.newInstance(), "支付宝支付");
        mAdapter.addTab(PublicPayFragment.newInstance(), "对公转账");
        mViewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(mViewPager);
        setUnderLineVisible(View.GONE);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_choise_pay_type;
    }
}
