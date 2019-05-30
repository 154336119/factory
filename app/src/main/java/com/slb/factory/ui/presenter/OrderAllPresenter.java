package com.slb.factory.ui.presenter;

import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.ui.contract.OrderAllContract;
import com.slb.frame.http2.retrofit.HttpResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.utils.rx.RxUtil;

/**
 * Created by Gifford on 2017/12/2.
 */

public class OrderAllPresenter
		extends AbstractBaseFragmentPresenter<OrderAllContract.IView>
		implements OrderAllContract.IPresenter<OrderAllContract.IView>{

}
