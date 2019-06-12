package com.slb.factory.ui.presenter;

import com.hwangjr.rxbus.RxBus;
import com.slb.factory.Base;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.UserEntity;
import com.slb.factory.ui.contract.MineContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.utils.rx.RxUtil;

/**
 * Created by Administrator on 2018/4/10.
 */

public class MinePresenter extends AbstractBaseFragmentPresenter<MineContract.IView>
    implements MineContract.IPresenter<MineContract.IView>{
    @Override
    public void getOurderNum() {
        RetrofitSerciveFactory.provideComService().getOrderNum(Base.getUserEntity().getToken())
                .lift(new BindFragmentPrssenterOpterator<HttpMjResult<Integer>>(this))
                .compose(RxUtil.<HttpMjResult<Integer>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun<Integer>())
                .subscribe(new BaseSubscriber<Integer>(mView) {
                    @Override
                    public void onNext(Integer o) {
                        super.onNext(o);
                        mView.getOrderNumSuccess(o);
                    }

                    @Override
                    public void onStart() {
                    }
                });
    }
}
