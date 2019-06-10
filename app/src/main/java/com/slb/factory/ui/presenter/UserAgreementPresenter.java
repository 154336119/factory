package com.slb.factory.ui.presenter;

import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.ConfigEntity;
import com.slb.factory.ui.contract.MineContract;
import com.slb.factory.ui.contract.UserAgreementContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;

/**
 * Created by Administrator on 2018/4/10.
 */

public class UserAgreementPresenter extends AbstractBasePresenter<UserAgreementContract.IView>
    implements UserAgreementContract.IPresenter<UserAgreementContract.IView>{
    @Override
    public void getConfig() {
        RetrofitSerciveFactory.provideComService().getConfig(null)
                .lift(new BindPrssenterOpterator<HttpMjResult<ConfigEntity>>(this))
                .compose(RxUtil.<HttpMjResult<ConfigEntity>>applySchedulersForRetrofit())
                .map(new HttpMjEntityFun<ConfigEntity>())
                .subscribe(new BaseSubscriber<ConfigEntity>(this.mView) {
                    @Override
                    public void onNext(ConfigEntity entity) {
                        super.onNext(entity);
                        mView.getConfigSuccess(entity);
                    }
                });
    }
}
