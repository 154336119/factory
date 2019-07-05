package com.slb.factory.ui.presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.alibaba.fastjson.JSONObject;
import com.hwangjr.rxbus.RxBus;
import com.slb.factory.Base;
import com.slb.factory.event.OrderRefreshEvent;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.ConfigEntity;
import com.slb.factory.http.bean.UpdateEntity;
import com.slb.factory.ui.contract.MainContract;
import com.slb.factory.ui.contract.WxPayContract;
import com.slb.factory.util.SharedPreferencesUtils;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindFragmentPrssenterOpterator;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.frame.utils.rx.RxUtil;

import static com.slb.factory.util.config.BizcContant.SP_CONFIG;
import static com.slb.factory.util.config.BizcContant.SP_USER;


/**
 * Created by Gifford on 2017/11/7.
 */

public class MainPresenter extends AbstractBasePresenter<MainContract.IView>
		implements MainContract.IPresenter<MainContract.IView>{
	@Override
	public void getUpdateInfo() {
		RetrofitSerciveFactory.provideComService().getUpdateInfo(1)
				.lift(new BindPrssenterOpterator<HttpMjResult<UpdateEntity>>(this))
				.compose(RxUtil.<HttpMjResult<UpdateEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<UpdateEntity>())
				.subscribe(new BaseSubscriber<UpdateEntity>(this.mView) {
					@Override
					public void onNext(UpdateEntity entity) {
						if (entity.getVersion_num() > Base.getVersionCode(Base.getContext())){
							mView.tipUpdate(entity);
						}
					}

					@Override
					public void onStart() {
					}
				});
	}

	@Override
	public void getConfig() {
		RetrofitSerciveFactory.provideComService().getConfig(null)
				.lift(new BindPrssenterOpterator<HttpMjResult<ConfigEntity>>(this))
				.compose(RxUtil.<HttpMjResult<ConfigEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<ConfigEntity>())
				.subscribe(new BaseSubscriber<ConfigEntity>(this.mView) {
					@Override
					public void onNext(ConfigEntity entity) {
						String configJsonStr = JSONObject.toJSONString(entity);//将java对象转换为json对象
						SharedPreferencesUtils.setParam(Base.getContext(), SP_CONFIG,configJsonStr);
					}
					@Override
					public void onStart() {
					}
				});
	}
}
