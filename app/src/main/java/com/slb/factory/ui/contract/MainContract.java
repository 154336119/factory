package com.slb.factory.ui.contract;

import com.slb.factory.http.bean.UpdateEntity;
import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2017/11/29.
 */

public class MainContract {
	public interface IView extends IBaseLoadingDialogView {
		void tipUpdate(UpdateEntity entity);
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void getUpdateInfo();
	}
}
