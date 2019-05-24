package com.slb.factory.ui.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.factory.Base;
import com.slb.factory.http.bean.UserEntity;
import com.slb.factory.http.callback.DialogCallback;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.http.model.LzyResponse;
import com.slb.factory.http.service.ComServiceUrl;
import com.slb.factory.ui.contract.LoginContract;


/**
 * Created by Gifford on 2017/11/7.
 */

public class LoginPresenter extends AbstractBasePresenter<LoginContract.IView>
		implements LoginContract.IPresenter<LoginContract.IView>{
	String mToken = null;
	public void getUserInfo(String id) {
		OkGo.<LzyResponse<UserEntity>>get(DnsFactory.getInstance().getDns().getCommonBaseUrl()+ ComServiceUrl.getUserInfo+"/"+id)//
				.tag(this)//
				.headers("Authorization","Bearer "+mToken)
				.execute(new DialogCallback<LzyResponse<UserEntity>>(this.mView) {
					@Override
					public void onSuccess(Response<LzyResponse<UserEntity>> response) {
//						Logger.d("response:"+response.body().data);
						Base.setUserEntity(response.body().data);
						Base.getUserEntity().setToken(mToken);

						UserEntity userEntity = Base.getUserEntity();
						userEntity.setToken(mToken);
						Base.setUserEntity(userEntity);

						Logger.d(Base.getUserEntity().getEmail());
						mView.loginSuccess();
					}
				});


	}
	@Override
	public void login(String mUserName, String mPassword) {
		if(TextUtils.isEmpty(mUserName)){
			mView.showMsg("请输入邮箱");
			return;
		}if(TextUtils.isEmpty(mPassword)){
			mView.showMsg("请输入密码");
			return;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", mUserName);
		jsonObject.put("password", mPassword);
		OkGo.<String>post(DnsFactory.getInstance().getDns().getCommonBaseUrl()+ ComServiceUrl.login)//
				.tag(this)//
				.upJson(jsonObject.toString())//
				.execute(new StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						//注意这里已经是在主线程了
						JSONObject jsonObject1 = JSONObject.parseObject(response.body());
						int status = jsonObject1.getInteger("status");
						if(status!=1){
							mView.showMsg(jsonObject1.getString("message"));
							mView.loadingDialogDismiss();
							return;
						}

						JSONObject jb = jsonObject1.getJSONObject("result");
						String token = jb.getString("token");

						JSONObject jb1 = jb.getJSONArray("authorities").getJSONObject(0);
						String info = jb1.getString("authority");
						String userId = JSONObject.parseObject(info).getString("id");

						Logger.d("token:"+token);
						Logger.d("userId:"+userId);
						mToken = token;
						getUserInfo(userId);
						mView.loadingDialogDismiss();
					}

					@Override
					public void onError(Response<String> response) {
						super.onError(response);
						mView.loadingDialogDismiss();
					}
					@Override
					public void onStart(Request<String, ? extends Request> request) {
						super.onStart(request);
							mView.showLoadingDialog("loading");
					}
				});
	}
}
