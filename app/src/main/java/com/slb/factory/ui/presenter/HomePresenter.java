package com.slb.factory.ui.presenter;

import com.orhanobut.logger.Logger;
import com.slb.factory.Base;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.Goods;
import com.slb.factory.http.bean.HomeMergeEntity;
import com.slb.factory.http.bean.Seckill;
import com.slb.factory.ui.contract.HomeContract;
import com.slb.factory.weight.refresh.CustomRefreshFooter;
import com.slb.frame.http2.exception.ResultException;
import com.slb.frame.http2.retrofit.HttpMjListDataResutl;
import com.slb.frame.http2.retrofit.HttpMjListResult;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.http2.rxjava.HttpMjListEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.utils.rx.RxUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.functions.Func4;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomePresenter extends AbstractBaseFragmentPresenter<HomeContract.IView>
    implements HomeContract.IPresenter<HomeContract.IView>{
    //每页的大小
    private static final int PAGE_SIZE = 10;
    //当前是第几页
    private int mCurrentPage = 1;
    //是否没有更多数据了
    private boolean isNoMoreData = false;


    @Override
    public void onLoadMore() {
        if(isNoMoreData){
            return;
        }
        mCurrentPage++;
        RetrofitSerciveFactory.provideComService().getHotGoods(PAGE_SIZE,mCurrentPage)
                .lift(new BindFragmentPrssenterOpterator< HttpMjListResult<Goods>>(this))
                .compose(RxUtil.< HttpMjListResult<Goods>>applySchedulersForRetrofit())
                .map(new HttpMjListEntityFun<Goods>())
                .subscribe(new BaseSubscriber<HttpMjListDataResutl<Goods>>(this.mView) {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.finishLoadmore(false);
                    }

                    @Override
                    public void onNext(HttpMjListDataResutl<Goods> entity) {
                        if (entity.getList() == null || entity.getList().size() == 0) {
                            //没有更多数据了
                            isNoMoreData = true;
//                            mView.setRefreshFooter(new CustomRefreshFooter(Base.getContext(), "没有更多啦"));
                            mView.finishLoadmoreWithNoMoreData();
                            return;
                        }
                        mView.addGoodsListData(entity.getList());
                        mView.finishLoadmore(true);
                    }
                });
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        isNoMoreData = false;
//        mView.setRefreshFooter(new CustomRefreshFooter(Base.getContext(), "加载中…"));
        //重置没有更多数据状态
        mView.resetNoMoreData();
        final HomeMergeEntity homeMergeEntity=new HomeMergeEntity();
        Observable<HttpMjResult<List<String>>> bannerList = RetrofitSerciveFactory.provideComService().getBannerList(null);
        Observable<HttpMjResult<List<Brand>>> brandList = RetrofitSerciveFactory.provideComService().getHotBrandList(null);
        Observable<HttpMjResult<List<Seckill>>> seckillList = RetrofitSerciveFactory.provideComService().getLimited(null);
        Observable<HttpMjListResult<Goods>> goodsList = RetrofitSerciveFactory.provideComService().getHotGoods(PAGE_SIZE, mCurrentPage);
        Observable.zip(bannerList, brandList, seckillList, goodsList, new Func4<HttpMjResult<List<String>>, HttpMjResult<List<Brand>>, HttpMjResult<List<Seckill>>, HttpMjListResult<Goods>, HomeMergeEntity>() {
            @Override
            public HomeMergeEntity call(HttpMjResult<List<String>> bannerListHttpMjResult, HttpMjResult<List<Brand>> brandListHttpResult, HttpMjResult<List<Seckill>> seckillListHttpResult, HttpMjListResult<Goods> goodsListHttpResult) {
                if (bannerListHttpMjResult.getCode() != null && bannerListHttpMjResult.getCode() != 200) {
                    throw new ResultException(bannerListHttpMjResult.getCode(), bannerListHttpMjResult.getMsg());
                }
                if (brandListHttpResult.getCode() != null && brandListHttpResult.getCode() != 200) {
                    throw new ResultException(brandListHttpResult.getCode(), brandListHttpResult.getMsg());
                }
                if (seckillListHttpResult.getCode() != null && seckillListHttpResult.getCode() != 200) {
                    throw new ResultException(seckillListHttpResult.getCode(), seckillListHttpResult.getMsg());
                }
                if (goodsListHttpResult.getCode() != null && goodsListHttpResult.getCode() != 200) {
                    throw new ResultException(goodsListHttpResult.getCode(), goodsListHttpResult.getMsg());
                }
                //banner
                if (bannerListHttpMjResult.getData() != null) {
                    homeMergeEntity.setmBannerList(bannerListHttpMjResult.getData());
                } else {
                    homeMergeEntity.setmBannerList(new ArrayList<String>());
                }
                //牌子
                if (brandListHttpResult.getData() != null) {
                    homeMergeEntity.setmBrandList(brandListHttpResult.getData());
                } else {
                    homeMergeEntity.setmBrandList(new ArrayList<Brand>());
                }
                //秒杀
                if (seckillListHttpResult.getData() != null) {
                    homeMergeEntity.setmSeckillList(seckillListHttpResult.getData());
                } else {
                    homeMergeEntity.setmSeckillList(new ArrayList<Seckill>());
                }
                //商品
                if (goodsListHttpResult.getData().getList() != null) {
                    homeMergeEntity.setmGoodsList(goodsListHttpResult.getData().getList());
                } else {
                    homeMergeEntity.setmGoodsList(new ArrayList<Goods>());
                }
                return homeMergeEntity;
            }
        }).lift(new BindFragmentPrssenterOpterator<HomeMergeEntity>(this))
                 .compose(RxUtil.<HomeMergeEntity>applySchedulersForRetrofit())
                 .subscribe(new BaseSubscriber<HomeMergeEntity>(mView) {
                     @Override
                     public void onNext(HomeMergeEntity homeMergeEntity) {
                         mView.setHotBrandListData(homeMergeEntity.getmBrandList());
                         mView.setSeckillListData(homeMergeEntity.getmSeckillList());
                         mView.setHotGoodListData(homeMergeEntity.getmGoodsList());
                         mView.setBannerListData(homeMergeEntity.getmBannerList());
                         mView.finishRefresh(true);
                     }

                     @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.finishRefresh(false);
                    }
                });

    }

}
