package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;
import com.slb.frame.ui.view.IBaseLoadingView;

/**
 * Created by Gifford on 2018/1/5.
 */

public class MineContract {
	public interface IView extends IBaseLoadingDialogView {
		void getOrderNumSuccess(Integer num);
	}
	public interface IPresenter<T> extends IBaseFragmentPresenter<T> {
		void getOurderNum();
	}
}
