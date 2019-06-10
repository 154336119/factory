package com.slb.factory.ui.contract;

import com.slb.factory.http.bean.PayTypeEntity;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

import java.io.File;

/**
 * Created by Gifford on 2018/6/6.
 */

public class ChoisePayTypeContract {
	public interface IView extends IBaseLoadingDialogView {
		void getPayTypeConfigSuccess(PayTypeEntity entity);
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void getPayTypeConfig();
	}
}
