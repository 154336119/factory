package com.slb.factory.ui.presenter;

import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.PayTypeEntity;
import com.slb.factory.ui.contract.ChoisePayTypeContract;
import com.slb.factory.ui.contract.MsgListContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;

/**
 * 刁剑
 * Created on 2017/1/3.
 * 注释:
 */

public class ChoisePayTypePresenter extends AbstractBasePresenter<ChoisePayTypeContract.IView> implements ChoisePayTypeContract.IPresenter<ChoisePayTypeContract.IView> {

    @Override
    public void getPayTypeConfig() {
        RetrofitSerciveFactory.provideComService().getPayTypeConfig(null)
                .lift(new BindPrssenterOpterator<HttpMjResult<PayTypeEntity>>(this))
                .compose(RxUtil.<HttpMjResult< PayTypeEntity>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun< PayTypeEntity>())
                .subscribe(new BaseSubscriber<PayTypeEntity>(this.mView) {
                    @Override
                    public void onNext(PayTypeEntity entity) {
                        super.onNext(entity);
                        mView.getPayTypeConfigSuccess(entity);
                    }
                });
    }
}
