package com.slb.factory.ui.presenter;

import android.annotation.TargetApi;
import android.os.Build;

import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.PayEntity;
import com.slb.factory.http.bean.PayTypeEntity;
import com.slb.factory.ui.contract.ChoisePayTypeContract;
import com.slb.factory.ui.contract.WebViewContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;

import java.util.concurrent.TimeUnit;

import rx.Subscription;

/**
 * 刁剑
 * Created on 2017/1/3.
 * 注释:
 */

public class WebViewPresenter extends AbstractBasePresenter<WebViewContract.IView> implements WebViewContract.IPresenter<WebViewContract.IView> {

//    @Override
//    public void getPayState(int payType, String orderCode) {
//        Subscription subscribe = RetrofitSerciveFactory.provideComService().getPayState(payType, orderCode)
//                .lift(new BindPrssenterOpterator<HttpMjResult<String>>(this))
//                .compose(RxUtil.<HttpMjResult<String>>applySchedulersForRetrofit())
//                .map(new HttpMjEntityFun<String>())
//                .subscribe(new BaseSubscriber<String>(this.mView) {
//                    @Override
//                    public void onNext(String entity) {
//                        mView.toPaySuccessActivity();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.toPayFaildActivity();
//                    }
//                });
//    }

    @Override
    public void getPayParam(int payType, String orderCode) {
        RetrofitSerciveFactory.provideComService().getPayParams(payType,orderCode)
                .lift(new BindPrssenterOpterator<HttpMjResult<PayEntity>>(this))
                .compose(RxUtil.<HttpMjResult<PayEntity>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< PayEntity>())
                .subscribe(new BaseSubscriber<PayEntity>(this.mView) {
                    @Override
                    public void onNext(PayEntity entity) {
                        super.onNext(entity);
                        mView.getPayParamSuccess(entity);
                    }
                });
    }
}
