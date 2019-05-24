package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2017/11/7.
 */

public class LoginContract {
	public interface IView extends IBaseLoadingDialogView {
		void loginSuccess();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {

		void login(String mUserName, String mPassword);
	}
}
