package com.slb.factory.ui.presenter;

import com.hwangjr.rxbus.RxBus;
import com.slb.factory.event.OrderRefreshEvent;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.ui.contract.OrderAllContract;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.retrofit.HttpResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpEntityFun;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.utils.rx.RxUtil;

/**
 * Created by Gifford on 2017/12/2.
 */

public class OrderAllPresenter
		extends AbstractBaseFragmentPresenter<OrderAllContract.IView>
		implements OrderAllContract.IPresenter<OrderAllContract.IView>{
	@Override
	public void orderFinish(String orderID) {
		RetrofitSerciveFactory.provideComService().orderFinish(orderID)
				.lift(new BindFragmentPrssenterOpterator<HttpMjResult<Object>>(this))
				.compose(RxUtil.<HttpMjResult<Object>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<Object>())
				.subscribe(new BaseSubscriber<Object>(this.mView) {
					@Override
					public void onNext(Object entity) {
						super.onNext(entity);
						RxBus.get().post(new OrderRefreshEvent());
					}
				});
	}
}
