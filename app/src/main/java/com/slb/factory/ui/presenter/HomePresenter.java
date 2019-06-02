package com.slb.factory.ui.presenter;

import com.slb.factory.Base;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.Goods;
import com.slb.factory.http.bean.Seckill;
import com.slb.factory.ui.contract.HomeContract;
import com.slb.frame.http2.retrofit.HttpMjListResult;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.utils.rx.RxUtil;

import java.util.List;

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

    //热门品牌
    @Override
    public void getHotBrandList() {
        RetrofitSerciveFactory.provideComService().getHotBrandList(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<List<Brand>>>(this))
                .compose(RxUtil.<HttpMjResult< List<Brand>>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< List<Brand>>())
                .subscribe(new BaseSubscriber<List<Brand>>(this.mView) {
                    @Override
                    public void onNext(List<Brand> entity) {
                        super.onNext(entity);
                        mView.showMsg("验证码发送成功");
                    }
                });
    }

    //限时秒杀
    @Override
    public void getLimited() {
        RetrofitSerciveFactory.provideComService().getLimited(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<List<Seckill>>>(this))
                .compose(RxUtil.<HttpMjResult< List<Seckill>>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< List<Seckill>>())
                .subscribe(new BaseSubscriber<List<Seckill>>(this.mView) {
                    @Override
                    public void onNext(List<Seckill> entity) {
                        super.onNext(entity);
                        mView.showMsg("验证码发送成功");
                    }
                });
    }


    //热门商品
    @Override
    public void getHotGoods() {
        RetrofitSerciveFactory.provideComService().getHotGoods(PAGE_SIZE,mCurrentPage)
                .lift(new BindFragmentPrssenterOpterator< HttpMjResult<HttpMjListResult<Goods>>>(this))
                .compose(RxUtil.< HttpMjResult<HttpMjListResult<Goods>>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< HttpMjListResult<Goods>>())
                .subscribe(new BaseSubscriber<HttpMjListResult<Goods>>(this.mView) {
                    @Override
                    public void onNext(HttpMjListResult<Goods> entity) {
                        super.onNext(entity);
                    }
                });
    }
}
