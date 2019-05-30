package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

import retrofit2.http.Field;

/**
 * Created by Gifford on 2017/11/7.
 */

public class LoginContract {
	public interface IView extends IBaseLoadingDialogView {
		void loginSuccess();
		void varifyCodeSuccess();
		void showCountdown();
		//去上传营业执照的页面
		void goUploadLicenseActivity();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		/*** 获取验证码*/
		void getCode(String mobile);
		/*** 登陆*/
		void login( String mobile, String verifyCode);
	}
}
