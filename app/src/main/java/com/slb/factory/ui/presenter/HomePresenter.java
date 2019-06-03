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

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomePresenter extends AbstractBaseFragmentPresenter<HomeContract.IView>
    implements HomeContract.IPresenter<HomeContract.IView>{
    //每页的大小
    private static final int PAGE_SIZE = 10;
    //当前是第几页
    private int mCurrentPage = 0;
    //是否没有更多数据了
    private boolean isNoMoreData = false;

    @Override
    public void start() {

    }

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
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.finishLoadmore(false);
                    }

                    @Override
                    public void onNext(HttpMjListDataResutl<Goods> entity) {
                        super.onNext(entity);
                        if (entity.getList() == null || entity.getList().size() == 0) {
                            //没有更多数据了
                            isNoMoreData = true;
                            mView.setRefreshFooter(new CustomRefreshFooter(Base.getContext(), "没有更多啦"));
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
        mCurrentPage = 0;
        isNoMoreData = false;
        mView.setRefreshFooter(new CustomRefreshFooter(Base.getContext(), "加载中…"));
        //重置没有更多数据状态
        mView.resetNoMoreData();
        final HomeMergeEntity homeMergeEntity=new HomeMergeEntity();
        Observable<HttpMjResult<List<Brand>>> brandList = RetrofitSerciveFactory.provideComService().getHotBrandList(Base.getUserEntity().getToken());
        Observable<HttpMjResult<List<Seckill>>> seckillList = RetrofitSerciveFactory.provideComService().getLimited(Base.getUserEntity().getToken());
        Observable<HttpMjListResult<Goods>> goodsList = RetrofitSerciveFactory.provideComService().getHotGoods(PAGE_SIZE, mCurrentPage);

         Observable.zip(brandList, seckillList, goodsList, new Func3<HttpMjResult<List<Brand>>, HttpMjResult<List<Seckill>>, HttpMjListResult<Goods>, HomeMergeEntity>() {
            @Override
            public HomeMergeEntity call(HttpMjResult<List<Brand>> brandListHttpResult, HttpMjResult<List<Seckill>> seckillListHttpResult, HttpMjListResult<Goods> goodsListHttpResult) {
                if (brandListHttpResult.getCode() != null && brandListHttpResult.getCode() != 200) {
                    throw new ResultException(brandListHttpResult.getCode(), brandListHttpResult.getMsg());
                }
                if (seckillListHttpResult.getCode() != null && seckillListHttpResult.getCode() != 200) {
                    throw new ResultException(seckillListHttpResult.getCode(), seckillListHttpResult.getMsg());
                }
                if (goodsListHttpResult.getCode() != null && goodsListHttpResult.getCode() != 200) {
                    throw new ResultException(goodsListHttpResult.getCode(), goodsListHttpResult.getMsg());
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
        }).lift(new BindFragmentPrssenterOpterator<>(this))
                .compose(RxUtil.applySchedulersForRetrofit())
                .subscribe(new BaseSubscriber<Object>(mView) {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

//
//        //合并接口
//        Observable.zip(brandList, seckillList, goodsList, new Func3<HttpMjResult<List<Brand>>, HttpMjResult<List<Seckill>>, HttpMjListResult<Goods>, HomeMergeEntity>() {
//            @Override
//            public HomeMergeEntity call(HttpMjResult<List<Brand>> listHttpMjResult, HttpMjResult<List<Seckill>> listHttpMjResult2, HttpMjListResult<Goods> httpMjListResultHttpMjResult) {
//                isNull(listHttpMjResult);
//                isNull(listHttpMjResult2);
//                if (httpMjListResultHttpMjResult.getCode() != null && httpMjListResultHttpMjResult.getCode() != 200) {
//                    throw new ResultException(httpMjListResultHttpMjResult.getCode(), httpMjListResultHttpMjResult.getMsg());
//                }
//
//                HomeMergeEntity entity = new HomeMergeEntity();
//                //牌子
//                if (listHttpMjResult.getData() != null) {
//                    entity.setmBrandList(listHttpMjResult.getData());
//                } else {
//                    entity.setmBrandList(new ArrayList<Brand>());
//                }
//                //秒杀
//                if (listHttpMjResult2.getData() != null) {
//                    entity.setmSeckillList(listHttpMjResult2.getData());
//                } else {
//                    entity.setmSeckillList(new ArrayList<Seckill>());
//                }
//                //商品
//                if (httpMjListResultHttpMjResult.getData().getList() != null) {
//                    entity.setmGoodsList(httpMjListResultHttpMjResult.getData().getList());
//                } else {
//                    entity.setmGoodsList(new ArrayList<Goods>());
//                }
//                return entity;
//            }
//        }).lift(new BindFragmentPrssenterOpterator<>(this))
//                .compose(RxUtil.applySchedulersForRetrofit())
//                .subscribe(new BaseSubscriber<HomeMergeEntity>(mView){});
    }

    //热门品牌
    public void getHotBrandList() {
        RetrofitSerciveFactory.provideComService().getHotBrandList(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<List<Brand>>>(this))
                .compose(RxUtil.<HttpMjResult< List<Brand>>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< List<Brand>>())
                .subscribe(new BaseSubscriber<List<Brand>>(this.mView) {
                    @Override
                    public void onNext(List<Brand> entity) {
                        super.onNext(entity);
                        mView.setHotBrandListData(entity);
                    }
                });
    }

    //限时秒杀
    public void getLimited() {
        RetrofitSerciveFactory.provideComService().getLimited(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<List<Seckill>>>(this))
                .compose(RxUtil.<HttpMjResult< List<Seckill>>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< List<Seckill>>())
                .subscribe(new BaseSubscriber<List<Seckill>>(this.mView) {
                    @Override
                    public void onNext(List<Seckill> entity) {
                        super.onNext(entity);
                        mView.setSeckillListData(entity);
                    }
                });
    }


    protected void isNull(HttpMjResult httpResult){
        if (httpResult.getCode()!=null && httpResult.getCode()!=200){
            throw new ResultException(httpResult.getCode(), httpResult.getMsg());
        }
    }
}
