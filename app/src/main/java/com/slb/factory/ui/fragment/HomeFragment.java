package com.slb.factory.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.slb.factory.R;
import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.Goods;
import com.slb.factory.http.bean.Seckill;
import com.slb.factory.ui.adapter.HomeBrandsListAdapter;
import com.slb.factory.ui.adapter.HomeGoodsListAdapter;
import com.slb.factory.ui.adapter.HomeLimitListAdapter;
import com.slb.factory.ui.contract.HomeContract;
import com.slb.factory.ui.presenter.HomePresenter;
import com.slb.factory.util.LocalImageLoader;
import com.slb.factory.weight.refresh.CustomRefreshFooter;
import com.slb.factory.weight.refresh.CustomRefreshHeader;
import com.slb.frame.ui.fragment.BaseMvpFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment
        extends BaseMvpFragment<HomeContract.IView, HomeContract.IPresenter>
        implements HomeContract.IView {
    Unbinder unbinder;
    @BindView(R.id.HomeBanner)
    Banner HomeBanner;
    @BindView(R.id.RvHotSellingBrand)
    RecyclerView RvHotSellingBrand;
    @BindView(R.id.RvLimitedTime)
    RecyclerView RvLimitedTime;
    @BindView(R.id.RvHotGoods)
    RecyclerView RvHotGoods;
    @BindView(R.id.smartRefreshLayout_home)
    SmartRefreshLayout smartRefreshLayout;

    HomeBrandsListAdapter mHomeBrandsListAdapter;
    HomeGoodsListAdapter mHomeGoodsListAdapter;
    HomeLimitListAdapter mHomeLimitListAdapter;
    List<Brand> mBrandsList = new ArrayList<>();
    List<Seckill> mSeckillsList = new ArrayList<>();
    List<Goods> mGoodsList = new ArrayList<>();


    @Override
    protected boolean hasToolbar() {
        return false;
    }

    public static HomeFragment newInstance() {
        HomeFragment instance = new HomeFragment();
        return instance;
    }

    @Override
    public HomeContract.IPresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        //banner
        HomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new LocalImageLoader())
//                .setImages(presenter.getBannerImages())
                .setBannerAnimation(Transformer.Default)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
        //页面刷新框架
        smartRefreshLayout.setRefreshHeader(new CustomRefreshHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new CustomRefreshFooter(getActivity(), "加载中…"));
        smartRefreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        smartRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.onLoadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.onRefresh();
            }
        });

        //brands 品牌
        RvHotSellingBrand.setLayoutManager(new GridLayoutManager(_mActivity,3));
        mHomeBrandsListAdapter = new HomeBrandsListAdapter(mBrandsList ,getActivity());
        RvHotSellingBrand.setAdapter(mHomeBrandsListAdapter);

        //秒杀
        RvLimitedTime.setLayoutManager(new LinearLayoutManager(_mActivity));
        mHomeLimitListAdapter = new HomeLimitListAdapter(mSeckillsList ,getActivity());
        RvLimitedTime.setAdapter(mHomeLimitListAdapter);

        //商品
        RvHotGoods.setLayoutManager(new LinearLayoutManager(_mActivity));
        mHomeGoodsListAdapter = new HomeGoodsListAdapter(mGoodsList ,getActivity());
        RvHotGoods.setAdapter(mHomeGoodsListAdapter);

        mPresenter.onRefresh();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }

    @Override
    public void setHotBrandListData(List<Brand> entity) {
        mBrandsList = entity;
        mHomeBrandsListAdapter.setNewData(mBrandsList);
    }

    @Override
    public void setSeckillListData(List<Seckill> entity) {
//        mSeckillsList = entity;
        //测试
        mSeckillsList.addAll(entity);
        mSeckillsList.addAll(entity);
        mHomeLimitListAdapter.setNewData(mSeckillsList);
    }

    @Override
    public void addGoodsListData(List<Goods> entity) {
        mHomeGoodsListAdapter.addData(mGoodsList);
    }

    @Override
    public void finishRefresh(boolean success) {
        smartRefreshLayout.finishRefresh(success);
    }

    @Override
    public void finishLoadmore(boolean success) {
        smartRefreshLayout.resetNoMoreData();
    }

    @Override
    public void setRefreshFooter(RefreshFooter footer) {
        smartRefreshLayout.setRefreshFooter(footer);
    }

    @Override
    public void resetNoMoreData() {
        smartRefreshLayout.resetNoMoreData();
    }

    @Override
    public void finishLoadmoreWithNoMoreData() {
        smartRefreshLayout.finishLoadmoreWithNoMoreData();
    }

    @Override
    public void setHotGoodListData(List<Goods> entity) {
        mHomeGoodsListAdapter.setNewData(entity);
    }
}
