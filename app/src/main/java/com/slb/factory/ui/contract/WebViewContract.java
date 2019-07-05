package com.slb.factory.ui.contract;

import com.slb.factory.http.bean.PayEntity;
import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2018/1/5.
 */

public class WebViewContract {
	public interface IView extends IBaseLoadingDialogView {
		void getPayParamSuccess(PayEntity entity);
		void toPaySuccessActivity();
		void toPayFaildActivity();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void getPayState(int payType,String orderCode);
		void getPayParam(int payType,String orderCode);
	}
}
