package com.slb.factory.ui.contract;

import com.slb.factory.http.bean.ConfigEntity;
import com.slb.factory.http.bean.PayTypeEntity;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2018/6/6.
 */

public class UserAgreementContract {
	public interface IView extends IBaseLoadingDialogView {
		void getConfigSuccess(ConfigEntity entity);
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void getConfig();
	}
}
