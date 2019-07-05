package com.slb.factory.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.slb.factory.R;
import com.slb.factory.ui.adapter.PayTypePageAdapter;
import com.slb.factory.ui.fragment.OrderAllStatusFragment;
import com.slb.factory.ui.fragment.PublicPayFragment;
import com.slb.factory.ui.fragment.WxPayFragment;
import com.slb.frame.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActiivty extends BaseActivity {
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private PayTypePageAdapter mAdapter;
    private int pos = 0;
    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        pos =  getIntent().getIntExtra("POS",0);
    }

    @Override
    protected String setToolbarTitle() {
        return "全部订单";
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mAdapter = new PayTypePageAdapter(getSupportFragmentManager());
        //状态：0已下单、1待发货、3待收货、4已完成、5已取消
        mAdapter.addTab(OrderAllStatusFragment.newInstance(0), "待支付");
        mAdapter.addTab(OrderAllStatusFragment.newInstance(1), "待发货");
        mAdapter.addTab(OrderAllStatusFragment.newInstance(3), "待收货");
		mAdapter.addTab(OrderAllStatusFragment.newInstance(4), "已完成");
        mAdapter.addTab(OrderAllStatusFragment.newInstance(5), "已取消");
        mViewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(mViewPager);
        setUnderLineVisible(View.GONE);

        slidingTabLayout.setCurrentTab(pos);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }
}
