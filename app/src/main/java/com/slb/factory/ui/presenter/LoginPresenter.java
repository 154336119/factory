package com.slb.factory.ui.presenter;

import com.slb.factory.Base;
import com.slb.factory.http.RetrofitSerciveFactory;
import com.slb.factory.http.bean.UserEntity;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.rxjava.BaseSubscriber;
import com.slb.frame.http2.rxjava.BindPrssenterOpterator;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.factory.ui.contract.LoginContract;
import com.slb.frame.utils.rx.RxUtil;


/**
 * Created by Gifford on 2017/11/7.
 */

public class LoginPresenter extends AbstractBasePresenter<LoginContract.IView>
		implements LoginContract.IPresenter<LoginContract.IView>{
	@Override
	public void getCode(String mobile) {
		RetrofitSerciveFactory.provideComService().sendMsgCode(mobile)
				.lift(new BindPrssenterOpterator<HttpMjResult< Object>>(this))
				.compose(RxUtil.<HttpMjResult< Object>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun< Object>())
				.subscribe(new BaseSubscriber<Object>(this.mView) {
					@Override
					public void onNext(Object entity) {
						super.onNext(entity);
						mView.showMsg("验证码发送成功");
						mView.showCountdown();
						mView.varifyCodeSuccess();
					}
				});
	}

	@Override
	public void login(String mobile, String verifyCode) {
		RetrofitSerciveFactory.provideComService().login(mobile,verifyCode,1)
				.lift(new BindPrssenterOpterator<HttpMjResult<UserEntity>>(this))
				.compose(RxUtil.<HttpMjResult<UserEntity>>applySchedulersForRetrofit())
				.map(new HttpMjEntityFun<UserEntity>())
				.subscribe(new BaseSubscriber<UserEntity>(this.mView) {
					@Override
					public void onNext(UserEntity entity) {
						super.onNext(entity);
						//账号状态state：0等待上传执照、1已上传执照等待审核、2已审核通过
						if(entity.getState() == 0 ){
							mView.goUploadLicenseActivity();
						}else if(entity.getState() == 1 ){
							mView.showMsg("已上传执照等待审核");
						}else if(entity.getState() == 2 ){
							mView.showMsg("登陆成功");
							mView.loginSuccess();
						}
						Base.setUserEntity(entity);
					}
				});

	}
}
